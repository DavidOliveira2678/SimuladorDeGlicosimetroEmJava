package br.com.SimuladorDeGlicosimetro.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import br.com.SimuladorDeGlicosimetro.Controllers.MedicaoController;
import br.com.SimuladorDeGlicosimetro.Data.MedicaoDAO;
import br.com.SimuladorDeGlicosimetro.Entities.Medicao;
import br.com.SimuladorDeGlicosimetro.Services.MedicaoService;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularLinkedList;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularList;
import io.github.cdimascio.dotenv.Dotenv;

public class GeradorPDF {
	
	private static final Dotenv ENV = Dotenv.load();
	private static final String CAMINHO_IMAGEM = ENV.get("CAMINHO_IMAGEM");
	
	//TODO: REMOVER O MÉTOODO MAIN (GERADO APENAS PARA TESTE)
	public static void main(String[] args) {
		
		try {
			
			gerarPDF(false, true);
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public static void gerarPDF(boolean hipo, boolean hiper) throws IOException {
		
		Logger.getLogger("org.apache.pdfbox").setLevel(Level.SEVERE);
		
		try {
			
			PDDocument doc = new PDDocument();
			PDPage page = new PDPage(PDRectangle.A4);
			doc.addPage(page);
			
			PDType1Font font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
			PDPageContentStream contStream = new PDPageContentStream(doc, page);
			PDRectangle containerPagina = page.getMediaBox();
			
			
			float margemSuperior = 50;
			float margemEsquerda = 50;
			float margemInferior = 50;
			float margemDireita = 50;
			
			float inicioX = margemEsquerda;
			float inicioYPrimeiraPagina = containerPagina.getHeight() - margemSuperior - 100;
			float inicioYOutrasPaginas = containerPagina.getHeight() - margemSuperior;
			
			Cursor cursor = new Cursor(inicioX, inicioYPrimeiraPagina);
			float leading = 15;
			
			
			gerarCabecalho(doc, page, contStream);
			
			contStream.beginText();
			
			contStream.setFont(font, 12);
			contStream.setLeading(leading);
			contStream.newLineAtOffset(cursor.x, cursor.y);
			
			
			CircularList<Medicao> medicoes = buscarTodasAsMedicoes();
			
			if(medicoes != null && !medicoes.isEmpty()) {
				
				for(Medicao med : medicoes) {
					
					contStream = verificarNovaPagina(doc, contStream, cursor, margemInferior, inicioX, inicioYOutrasPaginas, font, leading);
					
					contStream.showText("Data: " + med.getDataMedicao().format(Constantes.FORMATTER_DATA) +" | Hora: " + med.getHorarioMedicao().format(Constantes.FORMATTER_HORARIO));
					pularLinha(contStream, cursor, leading);
					contStream.showText("Valor da glicemia: " + med.getMedicao());
					pularDuasLinhas(contStream, cursor, leading);
					
				}
				
			} else {
				
				contStream.showText("Não há medições para visualizar.");
				pularLinha(contStream, cursor, leading);
				
			}

			contStream.endText();
			
			if(hipo) {
				
				gerarLinhaHorizontal(page, contStream, cursor.y);
				
				cursor.y -= 20;
				
				contStream = gerarHipoglicemias(doc, contStream, cursor, leading, font);
				
			}
			
			if(hiper) {
				
				gerarLinhaHorizontal(page, contStream, cursor.y);
				
				cursor.y -= 20;
				
				contStream = gerarHiperglicemias(doc, contStream, cursor, leading, font);
				
			}
			
			contStream.close();
			
			
			PDDocumentInformation docInfo = doc.getDocumentInformation();
			docInfo.setTitle("Medições_Glicemia " + LocalDate.now().format(Constantes.FORMATTER_DATA));
			docInfo.setAuthor("GlucaJava");
			docInfo.setCreationDate(Calendar.getInstance());
			
			doc.save("pdfBoxHelloWorld.pdf");
			doc.close();
			
			//TODO: REMOVER ESSA LINHA
			System.out.println("[DEBUG]: Documento salvo com sucesso!");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static PDPageContentStream novaPagina(PDDocument doc, Cursor cursor, float inicioX, float inicioY) throws IOException {
		
		PDPage novaPage = new PDPage(PDRectangle.A4);
		doc.addPage(novaPage);
		
		cursor.x = inicioX;
		cursor.y = inicioY;
		
		return new PDPageContentStream(doc, novaPage);
	}
	
	private static PDPageContentStream verificarNovaPagina(PDDocument doc,
														   PDPageContentStream contStream,
														   Cursor cursor,
														   float margemInferior,
														   float inicioX,
														   float inicioYOutrasPaginas,
														   PDType1Font font,
														   float leading) throws IOException{
		
	    if (cursor.y <= margemInferior) {

	        contStream.endText();
	        contStream.close();

	        contStream = novaPagina(doc, cursor, inicioX, inicioYOutrasPaginas);

	        contStream.beginText();
	        contStream.setFont(font, 12);
	        contStream.setLeading(leading);
	        contStream.newLineAtOffset(cursor.x, cursor.y);
	    }

	    return contStream;
	}
	
	private static void gerarCabecalho(PDDocument doc, PDPage page, PDPageContentStream contStream) throws IOException {
		
		PDType1Font font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
		PDImageXObject glucaJavaLogo = PDImageXObject.createFromFile(CAMINHO_IMAGEM + "\\SimuladorDeGlicosimetroEmJava\\src\\main\\resources\\Static\\GlucaJavaLogo.png", doc);
		float topo = page.getMediaBox().getHeight();
		
		contStream.drawImage(glucaJavaLogo, 50, topo - 100, 83, 80);
		
		
		contStream.beginText();
		
		contStream.setFont(font, 22);
		contStream.newLineAtOffset(140, topo - 60);
		contStream.showText("GlucaJava - Glicemias " + LocalDate.now().format(Constantes.FORMATTER_DATA));
		
		contStream.endText();
		
		float linhaY = page.getMediaBox().getHeight() - 110;
		gerarLinhaHorizontal(page, contStream, linhaY);
		
	}
	
	private static void gerarLinhaHorizontal(PDPage page, PDPageContentStream contStream, float linhaY) throws IOException {
		
		contStream.moveTo(50, linhaY);
		contStream.lineTo(page.getMediaBox().getWidth() - 50, linhaY);
		contStream.stroke();
		
	}
	
	private static void pularLinha(PDPageContentStream cs, Cursor cursor, float leading) throws IOException {
	    cs.newLine();
	    cursor.y -= leading;
	}
	
	private static void pularDuasLinhas(PDPageContentStream contStream, Cursor cursor, float leading) throws IOException{
		pularLinha(contStream, cursor, leading);
		pularLinha(contStream, cursor, leading);
	}
	
	
	private static PDPageContentStream gerarHipoglicemias(PDDocument doc, PDPageContentStream contStream, Cursor cursor, float leading, PDType1Font font) throws IOException {
		
		contStream.beginText();
		contStream.setFont(font, 18);
		contStream.setLeading(leading);
		contStream.newLineAtOffset(cursor.x, cursor.y);
		
		contStream.showText("Hipoglicemias");
		pularDuasLinhas(contStream, cursor, leading);
		
		contStream.setFont(font, 12);
		CircularList<Medicao> meds = buscarHipoglicemias();
		
		float margemSuperior = 50;
		float inicioY = PDRectangle.A4.getHeight() - margemSuperior;
		
		if(meds != null && !meds.isEmpty()) {

			for(Medicao med : meds) {
				
				contStream = verificarNovaPagina(doc, contStream, cursor, 50, 50, inicioY, font, leading);
				contStream.showText("Data: " + med.getDataMedicao().format(Constantes.FORMATTER_DATA) +" | Hora: " + med.getHorarioMedicao().format(Constantes.FORMATTER_HORARIO));
				pularLinha(contStream, cursor, leading);
				contStream.showText("Valor da glicemia: " + med.getMedicao());
				pularDuasLinhas(contStream, cursor, leading);
				
			}
			
		} else {
			
			contStream.showText("Não há hipoglicemias para vizualizar.");
			pularDuasLinhas(contStream, cursor, leading);
		}
		
		contStream.endText();
		return contStream;
		
	}
	
	private static PDPageContentStream gerarHiperglicemias(PDDocument doc, PDPageContentStream contStream, Cursor cursor, float leading, PDType1Font font) throws IOException {
		
		
		contStream.beginText();
		contStream.setFont(font, 18);
		contStream.setLeading(leading);
		contStream.newLineAtOffset(cursor.x, cursor.y);
		
		contStream.showText("Hiperglicemias");
		pularDuasLinhas(contStream, cursor, leading);
		
		contStream.setFont(font, 12);
		CircularList<Medicao> meds = buscarHiperglicemias();
		
		float margemSuperior = 50;
		float inicioY = PDRectangle.A4.getHeight() - margemSuperior;
		
		if(meds != null && !meds.isEmpty()) {
			
			for(Medicao med : meds) {
				
				contStream = verificarNovaPagina(doc, contStream, cursor, 50, 50, inicioY, font, leading);
				contStream.showText("Data: " + med.getDataMedicao().format(Constantes.FORMATTER_DATA) +" | Hora: " + med.getHorarioMedicao().format(Constantes.FORMATTER_HORARIO));
				pularLinha(contStream, cursor, leading);
				contStream.showText("Valor da glicemia: " + med.getMedicao());
				pularDuasLinhas(contStream, cursor, leading);
				
			}
			
		} else {
			
			contStream.showText("Não há hiperglicemias para visualizar.");
			pularDuasLinhas(contStream, cursor, leading);
			
		}
		
		contStream.endText();
		return contStream;
		
	}
	
	private static CircularLinkedList<Medicao> buscarTodasAsMedicoes(){
		
		try {
			
			MedicaoDAO medDAO = new MedicaoDAO();
			MedicaoService medSer = new MedicaoService(medDAO);
			MedicaoController medCont = new MedicaoController(medSer);
			
			return medCont.buscarMedicoes();			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	private static CircularLinkedList<Medicao> buscarHipoglicemias(){
	
		try {
			
			MedicaoDAO medDAO = new MedicaoDAO();
			MedicaoService medSer = new MedicaoService(medDAO);
			MedicaoController medCont = new MedicaoController(medSer);
			
			return medCont.buscarHipoglicemias();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	private static CircularLinkedList<Medicao> buscarHiperglicemias(){
		
		try {
			
			MedicaoDAO medDAO = new MedicaoDAO();
			MedicaoService medSer = new MedicaoService(medDAO);
			MedicaoController medCont = new MedicaoController(medSer);
			
			return medCont.buscarHiperglicemias();			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private static class Cursor{
		
		private float x;
		private float y;
		
		public Cursor(float x, float y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
}
