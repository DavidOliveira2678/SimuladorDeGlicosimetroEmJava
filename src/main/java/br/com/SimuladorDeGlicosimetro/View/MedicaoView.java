package br.com.SimuladorDeGlicosimetro.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import br.com.SimuladorDeGlicosimetro.Controllers.MedicaoController;
import br.com.SimuladorDeGlicosimetro.Data.MedicaoDAO;
import br.com.SimuladorDeGlicosimetro.Entities.Medicao;
import br.com.SimuladorDeGlicosimetro.Exceptions.ControllerException;
import br.com.SimuladorDeGlicosimetro.Services.MedicaoService;
import br.com.SimuladorDeGlicosimetro.Utils.Constantes;
import br.com.SimuladorDeGlicosimetro.Utils.Estado;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularLinkedList;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularList;

public class MedicaoView extends JPanel {
	private static final long serialVersionUID = 1L;
	private static CircularList<Medicao> medicoes;

	public MedicaoView(MainView mainView) {
		
		medicoes = new CircularLinkedList<Medicao>();
		this.setSize(Constantes.LARGURA, Constantes.ALTURA);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		
		JButton btnGerarMedicao = new JButton("Gerar Medição");
		btnGerarMedicao.setBorder(new LineBorder(new Color(92, 98, 131), 2, true));
		btnGerarMedicao.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		btnGerarMedicao.setBounds(584, 148, 233, 48);
		this.add(btnGerarMedicao);
		
		
		JLabel lblValorMedicao = new JLabel("0");
		lblValorMedicao.setVisible(false);
		lblValorMedicao.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorMedicao.setFont(new Font("Consolas", Font.BOLD, 72));
		lblValorMedicao.setBounds(184, 234, 134, 88);
		this.add(lblValorMedicao);
		
		
		JLabel lblValorData = new JLabel("0000-00-00");
		lblValorData.setVisible(false);
		lblValorData.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblValorData.setBounds(141, 347, 86, 23);
		this.add(lblValorData);
		
		
		JLabel lblValorHora = new JLabel("00:00:0000");
		lblValorHora.setVisible(false);
		lblValorHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorHora.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblValorHora.setBounds(274, 347, 86, 23);
		this.add(lblValorHora);
		
		
		JLabel lblValorEstado = new JLabel("NULL");
		lblValorEstado.setVisible(false);
		lblValorEstado.setFont(new Font("Consolas", Font.BOLD, 14));
		lblValorEstado.setBounds(141, 174, 222, 23);
		this.add(lblValorEstado);
		
		
		JButton btnDeletarMedicoes = new JButton("Deletar Medições");
		btnDeletarMedicoes.setFont(new Font("Century Gothic", Font.PLAIN, 22));
		btnDeletarMedicoes.setBounds(584, 234, 233, 48);
		this.add(btnDeletarMedicoes);
		
		
    	JButton btnPDFView = new JButton("Geração de PDF");
    	btnPDFView.setFont(new Font("Century Gothic", Font.PLAIN, 22));
    	btnPDFView.setBorder(new LineBorder(new Color(92, 98, 131), 2, true));
    	btnPDFView.setBounds(584, 356, 233, 48);
    	this.add(btnPDFView);
		
		
		JButton btnCarregarMedicoes = new JButton();
		btnCarregarMedicoes.setToolTipText("Carregar medições");
		btnCarregarMedicoes.setIcon(new ImageIcon(MedicaoView.class.getResource("/Static/glicosimetroBotaoSuperiorSpr.png")));
		btnCarregarMedicoes.setBounds(231, 352, 40, 43);
		btnCarregarMedicoes.setHorizontalAlignment(SwingConstants.CENTER);
		btnCarregarMedicoes.setVerticalAlignment(SwingConstants.CENTER);
		btnCarregarMedicoes.setBorderPainted(false);
		btnCarregarMedicoes.setContentAreaFilled(false);
		btnCarregarMedicoes.setFocusPainted(false);
		btnCarregarMedicoes.setMargin(new Insets(0, 0, 0, 0));
		btnCarregarMedicoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(btnCarregarMedicoes);
		
		
		JButton btnEsquerda = new JButton();
		btnEsquerda.setIcon(new ImageIcon(MedicaoView.class.getResource("/Static/glicosimetroBotaoInferiorEsquerdoSpr.png")));
		btnEsquerda.setBounds(125, 378, 113, 90);
		btnEsquerda.setHorizontalAlignment(SwingConstants.CENTER);
		btnEsquerda.setVerticalAlignment(SwingConstants.CENTER);
		btnEsquerda.setEnabled(false);
		btnEsquerda.setBorderPainted(false);
		btnEsquerda.setContentAreaFilled(false);
		btnEsquerda.setFocusPainted(false);
		btnEsquerda.setMargin(new Insets(0, 0, 0, 0));
		btnEsquerda.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.add(btnEsquerda);
		
		
		JButton btnDireita = new JButton();
		btnDireita.setIcon(new ImageIcon(MedicaoView.class.getResource("/Static/glicosimetroBotaoInferiorDireitoSpr.png")));
		btnDireita.setBounds(264, 378, 113, 90);
		btnDireita.setHorizontalAlignment(SwingConstants.CENTER);
		btnDireita.setVerticalAlignment(SwingConstants.CENTER);
		btnDireita.setEnabled(false);
		btnDireita.setBorderPainted(false);
		btnDireita.setContentAreaFilled(false);
		btnDireita.setFocusPainted(false);
		btnDireita.setMargin(new Insets(0, 0, 0, 0));
		btnDireita.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.add(btnDireita);
		
		
		JLabel glicosimetroSpr = new JLabel("");
		glicosimetroSpr.setIcon(new ImageIcon(MedicaoView.class.getResource("/Static/glicosimetroSpr.png")));
		glicosimetroSpr.setBounds(92, 69, 318, 416);
		this.add(glicosimetroSpr);
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		btnVoltar.setBounds(10, 11, 92, 27);
		this.add(btnVoltar);
		
		
		btnVoltar.addActionListener(e -> {
			mainView.goTo("title");
			mainView.setTitle("GlucaJava");	
		});
		
		
		btnPDFView.addActionListener(e -> {
			mainView.goTo("pdf");
			mainView.setTitle("GlucaJava - Gerar PDF");		
		});
		
		
		btnGerarMedicao.addActionListener(e -> {
			
			btnVoltar.setEnabled(false);
	    	btnGerarMedicao.setEnabled(false);
	    	btnDeletarMedicoes.setEnabled(false);
	    	btnPDFView.setEnabled(false);
	    	btnCarregarMedicoes.setEnabled(false);
	    	btnCarregarMedicoes.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
			MedicaoDAO medDAO = new MedicaoDAO();
			MedicaoService medSer = new MedicaoService(medDAO);
			MedicaoController medCont = new MedicaoController(medSer);
			
			
			JOptionPane opPane = new JOptionPane(
					"Gerando medição...",
					JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.DEFAULT_OPTION,
					null,
					new Object[]{},
					null);
			
			JDialog jDialog = opPane.createDialog(this, "Gerando");
			jDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			jDialog.setModal(false);
			jDialog.setVisible(true);
			
			SwingWorker<Medicao, Void> worker = new SwingWorker<>() {
				
				Medicao med;
				
				@Override
				protected Medicao doInBackground() {
					
					try {
						
						med = medCont.realizarMedicao();
						
						return med;
						
					} catch (ControllerException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
						ex.printStackTrace();
						return null;
					}
					
				}
				
				@Override
				protected void done() {
					
					try {
						
						med = get();
						exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, med);

						jDialog.dispose();
						
						
						
					
					}catch (ExecutionException e) {
						
						jDialog.dispose();
						
						JOptionPane.showMessageDialog(null, e.getCause().getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
						
					} catch (InterruptedException  e) {
						
						jDialog.dispose();
						
						JOptionPane.showMessageDialog(null, "Erro inesperado", "Erro", JOptionPane.WARNING_MESSAGE);
						
						Thread.currentThread().interrupt();
						JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem técnica", JOptionPane.WARNING_MESSAGE);
						e.printStackTrace();
						
					} finally {
						
						btnVoltar.setEnabled(true);
						btnGerarMedicao.setEnabled(true);
						btnDeletarMedicoes.setEnabled(true);
						btnPDFView.setEnabled(true);
						btnCarregarMedicoes.setEnabled(true);
						btnCarregarMedicoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
						
						btnDireita.setEnabled(false);
						btnEsquerda.setEnabled(false);
						btnDireita.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						btnEsquerda.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						
					}
					
				}
				
			};
			
			worker.execute();
			
		});
		
		
		btnCarregarMedicoes.addActionListener(e -> {
			
			btnVoltar.setEnabled(false);
	    	btnGerarMedicao.setEnabled(false);
	    	btnDeletarMedicoes.setEnabled(false);
	    	btnPDFView.setEnabled(false);
	    	btnCarregarMedicoes.setEnabled(false);
	    	btnCarregarMedicoes.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			MedicaoDAO medDAO = new MedicaoDAO();
			MedicaoService medSer = new MedicaoService(medDAO);
			MedicaoController medCont = new MedicaoController(medSer);
			
			JOptionPane opPane = new JOptionPane(
					"Carregando medições.\nEssa ação pode durar um momento.",
					JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.DEFAULT_OPTION,
					null,
					new Object[]{},
					null);
			
			JDialog jDialog = opPane.createDialog(this, "Aguarde");
			jDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			jDialog.setModal(false);
			jDialog.setVisible(true);
			
			SwingWorker<CircularList<Medicao>, Void> worker = new SwingWorker<>() {
				
				Medicao medAtual;
				
				@Override
				protected CircularList<Medicao> doInBackground() {
					
					try {
						
						CircularList<Medicao> meds = medCont.buscarMedicoes();
						return meds;
						
					} catch (ControllerException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
						return null;
					}
					
					
				}
				
				@Override
				protected void done() {
					
					try {
						
						jDialog.dispose();
						
						medicoes = get();
						medAtual = medicoes.get(0);
						exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
						
						btnDireita.setEnabled(true);
						btnEsquerda.setEnabled(true);
						btnDireita.setCursor(new Cursor(Cursor.HAND_CURSOR));
						btnEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
						
						
					} catch (ExecutionException e) {
						
						jDialog.dispose();
						
						JOptionPane.showMessageDialog(null, e.getCause().getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
						
					} catch (InterruptedException  e) {
						
						jDialog.dispose();
						
						JOptionPane.showMessageDialog(null, "Erro inesperado", "Erro", JOptionPane.WARNING_MESSAGE);

						Thread.currentThread().interrupt();
						JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem técnica", JOptionPane.WARNING_MESSAGE);
						e.printStackTrace();
						
					} finally {
						
						btnVoltar.setEnabled(true);
						btnGerarMedicao.setEnabled(true);
						btnDeletarMedicoes.setEnabled(true);
						btnPDFView.setEnabled(true);
						btnCarregarMedicoes.setEnabled(true);
						btnCarregarMedicoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
					
					
				}
				
			};
			
			worker.execute();
		
	});
		
		
		btnDeletarMedicoes.addActionListener(e -> {
			
			int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja deletar todas as medições?", "Confirmação", JOptionPane.YES_NO_OPTION);
		    
		    if (confirmacao == JOptionPane.YES_OPTION) {
		    	
		    	btnVoltar.setEnabled(false);
		    	btnGerarMedicao.setEnabled(false);
		    	btnDeletarMedicoes.setEnabled(false);
		    	btnPDFView.setEnabled(false);
		    	btnCarregarMedicoes.setEnabled(false);
		    	btnCarregarMedicoes.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		    	
				MedicaoDAO medDAO = new MedicaoDAO();
				MedicaoService medSer = new MedicaoService(medDAO);
				MedicaoController medCont = new MedicaoController(medSer);
				
				JOptionPane opPane = new JOptionPane(
						"Apagando medições.\nEssa ação pode durar um momento.",
						JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.DEFAULT_OPTION,
						null,
						new Object[]{},
						null);
				
				JDialog jDialog = opPane.createDialog(this, "Aguarde");
				jDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				jDialog.setModal(false);
				jDialog.setVisible(true);
				
				SwingWorker<Void, Void> worker = new SwingWorker<>() {
					
					@Override
					protected Void doInBackground() throws ControllerException {
						
						medCont.apagarMedicoes();
						
						if(medicoes != null) {
							medicoes.clear();
							System.out.println("[DEBUG]: DEU CLEAR");
						} else {
							
							System.out.println("[DEBUG]: NÃO TEVE CLEAR\nPASSOU DIRETO");
							
						}
						
						return null;
						
					}
					
					@Override
					protected void done() {
						
						try {
							get();
							JOptionPane.showMessageDialog(null, "Medições apagadas.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							
						} catch(ExecutionException e) {
							
							JOptionPane.showMessageDialog(null, e.getCause().getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
							
						} catch(InterruptedException e) {
							
							JOptionPane.showMessageDialog(null, "Erro inesperado ao deletar medições.", "Erro fatal", JOptionPane.WARNING_MESSAGE);
							
							Thread.currentThread().interrupt();
							JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem ténica", JOptionPane.INFORMATION_MESSAGE);
							e.printStackTrace();
							
						} finally {
							
							jDialog.dispose();
							
							lblValorMedicao.setVisible(false);
							lblValorData.setVisible(false);
							lblValorHora.setVisible(false);
							lblValorEstado.setVisible(false);
							
							btnDireita.setEnabled(false);
							btnEsquerda.setEnabled(false);
							btnDireita.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							btnEsquerda.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
							
							btnVoltar.setEnabled(true);
							btnDeletarMedicoes.setEnabled(true);
							btnGerarMedicao.setEnabled(true);
							btnPDFView.setEnabled(true);
							btnCarregarMedicoes.setEnabled(true);
							btnCarregarMedicoes.setCursor(new Cursor(Cursor.HAND_CURSOR));							
							
						}
						
					}
					
				};
				
				
				worker.execute();
				
		    }
		});
		
		
		btnDireita.addActionListener(e -> {
			
			Medicao medAtual = medicoes.next();
			exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
				
		});
		
		
		
		btnEsquerda.addActionListener(e -> {
				
			Medicao medAtual = medicoes.previous();
			exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
				
		});
		
	}
	
	
	
	public void exibirNaTela(JLabel glicemia, JLabel data, JLabel hora, JLabel estado, Medicao med) {
		
		glicemia.setText(String.valueOf(med.getMedicao()));
		data.setText(String.valueOf(med.getDataMedicao().format(Constantes.FORMATTER_DATA)));
		hora.setText(String.valueOf(med.getHorarioMedicao().format(Constantes.FORMATTER_HORARIO)));
		estado.setText(Estado.estadoToString(med.getEstado()));
		
		glicemia.setVisible(true);
		data.setVisible(true);
		hora.setVisible(true);
		estado.setVisible(true);
		
	}
	
}
