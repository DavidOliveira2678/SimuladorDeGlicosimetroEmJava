package br.com.SimuladorDeGlicosimetro.View;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.SimuladorDeGlicosimetro.Utils.Constantes;

public class MainView extends JFrame{
	
	private JPanel container;
	private CardLayout cardLayout;
	private static final long serialVersionUID = 1L;
	
	public MainView() {
		
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		
		
		final URL urlImagem = MainView.class.getResource("/Static/GlucaJavaIcon.png");
		
		TitleMenuView title = new TitleMenuView(this);
		MedicaoView medView = new MedicaoView(this);
		GerarPDFView pdfView = new GerarPDFView(this);
		
		container.add(title, "title");
		container.add(medView, "medicao");
		container.add(pdfView, "pdf");
		
		cardLayout.show(container, "title");
		
		add(container);
		
		setTitle("GlucaJava");
		setSize(Constantes.LARGURA, Constantes.ALTURA);
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlImagem));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(Constantes.FECHAR_TELA_CONFIRM);
		setSize(Constantes.LARGURA, Constantes.ALTURA);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
	}
	
	public void goTo(String nomeTela) {
		
		cardLayout.show(container, nomeTela);
		
	}

}
