package br.com.SimuladorDeGlicosimetro.View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.SimuladorDeGlicosimetro.Utils.Constantes;
import br.com.SimuladorDeGlicosimetro.Utils.GeradorPDF;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JButton;

public class GerarPDFView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerarPDFView frame = new GerarPDFView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GerarPDFView() {
		
		URL urlImagem = GerarPDFView.class.getClassLoader().getResource("Static/GlucaJavaIcon.png");
		
		setTitle("GlucaJava - Gerar PDF");
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlImagem));		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(Constantes.FECHAR_TELA_CONFIRM);
		setSize(Constantes.LARGURA, Constantes.ALTURA);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblTituloMaior = new JLabel("Gerar PDF das");
		lblTituloMaior.setBounds(240, 26, 453, 101);
		lblTituloMaior.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 67));
		contentPane.add(lblTituloMaior);
		
		
		JLabel lblTituloMenor = new JLabel("medições registradas");
		lblTituloMenor.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 47));
		lblTituloMenor.setBounds(281, 95, 475, 80);
		contentPane.add(lblTituloMenor);
		
		
		JCheckBox chckbxHipoglicemias = new JCheckBox("Hipoglicemias");
		chckbxHipoglicemias.setToolTipText("Campo exclusivo de hipoglicemias no PDF");
		chckbxHipoglicemias.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 28));
		chckbxHipoglicemias.setBounds(238, 274, 225, 51);
		contentPane.add(chckbxHipoglicemias);
		
		
		JCheckBox chckbxHiperglicemias = new JCheckBox("Hiperglicemias");
		chckbxHiperglicemias.setToolTipText("Campo exclusivo de hiperglicemias no PDF");
		chckbxHiperglicemias.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 28));
		chckbxHiperglicemias.setBounds(479, 274, 214, 51);
		contentPane.add(chckbxHiperglicemias);
		
		
		JLabel lblCamposEspecficos = new JLabel("Campos específicos");
		lblCamposEspecficos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCamposEspecficos.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 32));
		lblCamposEspecficos.setBounds(218, 210, 475, 80);
		contentPane.add(lblCamposEspecficos);
		
		
		JButton btnGerar = new JButton("Gerar!");
		btnGerar.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 42));
		btnGerar.setToolTipText("");
		btnGerar.setBounds(330, 383, 269, 70);
		contentPane.add(btnGerar);
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		btnVoltar.setBounds(10, 11, 92, 27);
		contentPane.add(btnVoltar);
		
		
		JLabel lblObs = new JLabel("Obs.: Você está gerando um arquivo PDF com todas as medições que você simulou.");
		lblObs.setHorizontalAlignment(SwingConstants.CENTER);
		lblObs.setFont(new Font("Yu Gothic UI Semilight", Font.ITALIC, 18));
		lblObs.setBounds(131, 481, 680, 80);
		contentPane.add(lblObs);
		
		
		btnVoltar.addActionListener( e -> {
			
			MedicaoView medView = new MedicaoView();
			this.setVisible(false);
			medView.setVisible(true);
			this.dispose();
			
		});
		
		
		btnGerar.addActionListener(e -> {
			
			int confirmacao = JOptionPane.showConfirmDialog(null, "Você deseja realmente gerar um documento das medições?\n"
					+ "Esta ação levará um certo tempo.", "Confirmação", JOptionPane.YES_NO_OPTION);
			
			if(confirmacao == JOptionPane.YES_OPTION) {
				
				boolean hipos = chckbxHipoglicemias.isSelected();
				boolean hipers = chckbxHiperglicemias.isSelected();
				
				btnGerar.setEnabled(false);
				chckbxHipoglicemias.setEnabled(false);
				chckbxHiperglicemias.setEnabled(false);
				btnVoltar.setEnabled(false);
				
				JOptionPane opPane = new JOptionPane(
						"Gerando PDF, aguarde.",
						JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.DEFAULT_OPTION,
						null,
						new Object[]{},
						null);
				
				JDialog jDialog = opPane.createDialog(this, "Aguarde");
				jDialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				jDialog.setModal(false);
				jDialog.setVisible(true);
				
				SwingWorker<String, Void> worker = new SwingWorker<>() {
					
					@Override
					protected String doInBackground() throws IOException{
						
						try {
							
							String mensagem = GeradorPDF.gerarPDF(hipos, hipers);
							return mensagem;
							
						} catch(IOException e) {
							e.printStackTrace();
							throw new IOException(e);
							
						}
						
					}
					
					@Override
					public void done() {
						
						jDialog.dispose();
						
						btnGerar.setEnabled(true);
						btnVoltar.setEnabled(true);
						
						chckbxHipoglicemias.setSelected(false);
						chckbxHiperglicemias.setSelected(false);
						chckbxHipoglicemias.setEnabled(true);
						chckbxHiperglicemias.setEnabled(true);
						
						try {
							
							String caminho = get();
							JOptionPane.showMessageDialog(null, "PDF gerado com sucesso!\nArquivo disponível em:\n" + caminho, "Confirmação", JOptionPane.INFORMATION_MESSAGE);
							
						} catch (ExecutionException e) {
							JOptionPane.showMessageDialog(null, e.getCause().getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
							
						} catch (InterruptedException  e) {
							Thread.currentThread().interrupt();
							JOptionPane.showMessageDialog(null, "Erro inesperado ao gerar PDF.", "Erro", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
						
					}
					
					
				};
				
				worker.execute();
				
			}
			
		});

	}
}
