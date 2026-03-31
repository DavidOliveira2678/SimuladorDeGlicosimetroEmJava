package br.com.SimuladorDeGlicosimetro.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.SimuladorDeGlicosimetro.Controllers.MedicaoController;
import br.com.SimuladorDeGlicosimetro.Data.MedicaoDAO;
import br.com.SimuladorDeGlicosimetro.Entities.Medicao;
import br.com.SimuladorDeGlicosimetro.Exceptions.ControllerException;
import br.com.SimuladorDeGlicosimetro.Services.MedicaoService;
import br.com.SimuladorDeGlicosimetro.Utils.Constantes;
import br.com.SimuladorDeGlicosimetro.Utils.Estado;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularLinkedList;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularList;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class MedicaoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static CircularList<Medicao> medicoes;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					MedicaoView frame = new MedicaoView();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MedicaoView() {
		
		medicoes = new CircularLinkedList<Medicao>();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(Constantes.FECHAR_TELA_CONFIRM);
		setResizable(false);
		setSize(Constantes.LARGURA, Constantes.ALTURA);
		setLocationRelativeTo(null);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnGerarMedicao = new JButton("Gerar Medição");
		btnGerarMedicao.setBorder(new LineBorder(new Color(92, 98, 131), 2, true));
		btnGerarMedicao.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnGerarMedicao.setBounds(584, 211, 214, 48);
		contentPane.add(btnGerarMedicao);
		
		
		JLabel lblValorMedicao = new JLabel("0");
		lblValorMedicao.setVisible(false);
		lblValorMedicao.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorMedicao.setFont(new Font("Consolas", Font.BOLD, 72));
		lblValorMedicao.setBounds(184, 234, 134, 88);
		contentPane.add(lblValorMedicao);
		
		
		JLabel lblValorData = new JLabel("0000-00-00");
		lblValorData.setVisible(false);
		lblValorData.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblValorData.setBounds(141, 347, 86, 23);
		contentPane.add(lblValorData);
		
		
		JLabel lblValorHora = new JLabel("00:00:0000");
		lblValorHora.setVisible(false);
		lblValorHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorHora.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblValorHora.setBounds(274, 347, 86, 23);
		contentPane.add(lblValorHora);
		
		
		JLabel lblValorEstado = new JLabel("NULL");
		lblValorEstado.setVisible(false);
		lblValorEstado.setFont(new Font("Consolas", Font.BOLD, 14));
		lblValorEstado.setBounds(141, 174, 222, 23);
		contentPane.add(lblValorEstado);
		
		
		JButton btnDeletarMedicoes = new JButton("Deletar Medições");
		btnDeletarMedicoes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDeletarMedicoes.setBounds(584, 297, 214, 48);
		contentPane.add(btnDeletarMedicoes);
		
		
		JButton btnCarregarMedicoes = new JButton();
		btnCarregarMedicoes.setToolTipText("Carregar medições");
		btnCarregarMedicoes.setIcon(new ImageIcon(MedicaoView.class.getResource("/br/com/SimuladorDeGlicosimetro/View/Static/glicosimetroBotaoSuperiorSpr.png")));
		btnCarregarMedicoes.setBounds(231, 352, 40, 43);
		btnCarregarMedicoes.setHorizontalAlignment(SwingConstants.CENTER);
		btnCarregarMedicoes.setVerticalAlignment(SwingConstants.CENTER);
		btnCarregarMedicoes.setBorderPainted(false);
		btnCarregarMedicoes.setContentAreaFilled(false);
		btnCarregarMedicoes.setFocusPainted(false);
		btnCarregarMedicoes.setMargin(new Insets(0, 0, 0, 0));
		btnCarregarMedicoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnCarregarMedicoes);
		
		
		JButton btnEsquerda = new JButton();
		btnEsquerda.setIcon(new ImageIcon(MedicaoView.class.getResource("/br/com/SimuladorDeGlicosimetro/View/Static/glicosimetroBotaoInferiorEsquerdoSpr.png")));
		btnEsquerda.setBounds(125, 378, 113, 90);
		btnEsquerda.setHorizontalAlignment(SwingConstants.CENTER);
		btnEsquerda.setVerticalAlignment(SwingConstants.CENTER);
		btnEsquerda.setEnabled(false);
		btnEsquerda.setBorderPainted(false);
		btnEsquerda.setContentAreaFilled(false);
		btnEsquerda.setFocusPainted(false);
		btnEsquerda.setMargin(new Insets(0, 0, 0, 0));
		btnEsquerda.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		contentPane.add(btnEsquerda);
		
		
		JButton btnDireita = new JButton();
		btnDireita.setIcon(new ImageIcon(MedicaoView.class.getResource("/br/com/SimuladorDeGlicosimetro/View/Static/glicosimetroBotaoInferiorDireitoSpr.png")));
		btnDireita.setBounds(264, 378, 113, 90);
		btnDireita.setHorizontalAlignment(SwingConstants.CENTER);
		btnDireita.setVerticalAlignment(SwingConstants.CENTER);
		btnDireita.setEnabled(false);
		btnDireita.setBorderPainted(false);
		btnDireita.setContentAreaFilled(false);
		btnDireita.setFocusPainted(false);
		btnDireita.setMargin(new Insets(0, 0, 0, 0));
		btnDireita.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		contentPane.add(btnDireita);
		
		
		JLabel lblValorIndice = new JLabel("0");
		lblValorIndice.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblValorIndice.setBounds(96, 35, 108, 23);
		contentPane.add(lblValorIndice);
		
		
		JLabel lblIndice = new JLabel("ID Glicemia");
		lblIndice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIndice.setBounds(96, 11, 108, 23);
		contentPane.add(lblIndice);
		
		
		JLabel glicosimetroSpr = new JLabel("New label");
		glicosimetroSpr.setIcon(new ImageIcon(MedicaoView.class.getResource("/br/com/SimuladorDeGlicosimetro/View/Static/glicosimetroSpr.png")));
		glicosimetroSpr.setBounds(92, 69, 318, 416);
		contentPane.add(glicosimetroSpr);
		
		
		
		btnGerarMedicao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					MedicaoDAO medDAO = new MedicaoDAO();
					MedicaoService medSer = new MedicaoService(medDAO);
					MedicaoController medCont = new MedicaoController(medSer);
					
					Medicao med = medCont.realizarMedicao();
					
					btnDireita.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					btnEsquerda.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					
					exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, med);
					
				} catch (ControllerException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		
		
		btnCarregarMedicoes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					MedicaoDAO medDAO = new MedicaoDAO();
					MedicaoService medSer = new MedicaoService(medDAO);
					MedicaoController medCont = new MedicaoController(medSer);
					
					medicoes = medCont.buscarMedicoes();
					
					Medicao medAtual = medicoes.get(0);
					
					exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
					lblValorIndice.setText(String.valueOf(medAtual.getId()));
					
					btnDireita.setEnabled(true);
					btnEsquerda.setEnabled(true);
					btnDireita.setCursor(new Cursor(Cursor.HAND_CURSOR));
					btnEsquerda.setCursor(new Cursor(Cursor.HAND_CURSOR));
						
				}catch (ControllerException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
				}	
			}
		});
		
		
		
		btnDireita.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Medicao medAtual = medicoes.next();
					
				lblValorIndice.setText(String.valueOf(medAtual.getId()));
				exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
				
			}
		});
		
		
		
		btnEsquerda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Medicao medAtual = medicoes.previous();
					
				lblValorIndice.setText(String.valueOf(medAtual.getId()));
				exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
				
			}
		});
		
		
		
		btnDeletarMedicoes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(deletarMedicoes()) {
					
					medicoes.clear();
					
					lblValorMedicao.setVisible(false);
					lblValorData.setVisible(false);
					lblValorHora.setVisible(false);
					lblValorEstado.setVisible(false);
					
					btnDireita.setEnabled(false);
					btnEsquerda.setEnabled(false);
					btnDireita.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					btnEsquerda.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					
				}
			}
		});
	}
	
	
	
	public boolean deletarMedicoes() {
		
	    int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja deletar todas as medições?", "Confirmação", JOptionPane.YES_NO_OPTION);
	    
	    if (confirmacao == JOptionPane.NO_OPTION) {
	    	
	    	return false;
	    	
	    } else {
	    	
	    	try {
	    		
				MedicaoDAO medDAO = new MedicaoDAO();
				MedicaoService medSer = new MedicaoService(medDAO);
				MedicaoController medCont = new MedicaoController(medSer);
				
				medCont.apagarMedicoes();
				
				JOptionPane.showMessageDialog(null, "Medições apagadas.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
				return true;
				
	    	} catch (ControllerException ex) {
	    		JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
	    		return false;
	    	}
	    	
	    }
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
