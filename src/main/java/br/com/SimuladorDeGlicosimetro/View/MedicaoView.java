package br.com.SimuladorDeGlicosimetro.View;

import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;

public class MedicaoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static List<Medicao> medicoes;
	private static int indiceMedicao;

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
		btnGerarMedicao.setBounds(313, 337, 214, 48);
		contentPane.add(btnGerarMedicao);
		
		JLabel lblValorMedicao = new JLabel("0");
		lblValorMedicao.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblValorMedicao.setBounds(211, 171, 108, 23);
		contentPane.add(lblValorMedicao);
		
		JLabel lblValorData = new JLabel("0000-00-00");
		lblValorData.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblValorData.setBounds(341, 171, 143, 23);
		contentPane.add(lblValorData);
		
		JLabel lblValorHora = new JLabel("00:00:0000");
		lblValorHora.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblValorHora.setBounds(510, 171, 178, 23);
		contentPane.add(lblValorHora);
		
		JLabel lblValorEstado = new JLabel("NULL");
		lblValorEstado.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblValorEstado.setBounds(211, 258, 382, 23);
		contentPane.add(lblValorEstado);
		
		JLabel lblNewLabel = new JLabel("Glicemia");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(211, 146, 108, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblData_1 = new JLabel("Data (ANO-MÊS-DIA)");
		lblData_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData_1.setBounds(341, 146, 143, 23);
		contentPane.add(lblData_1);
		
		JLabel lblHorario = new JLabel("Horário");
		lblHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorario.setBounds(510, 146, 108, 23);
		contentPane.add(lblHorario);
		
		JLabel lblEstadoDaGlicemia = new JLabel("Estado da Glicemia");
		lblEstadoDaGlicemia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstadoDaGlicemia.setBounds(211, 231, 180, 23);
		contentPane.add(lblEstadoDaGlicemia);
		
		JButton btnDeletarMedicoes = new JButton("Deletar Medições");
		btnDeletarMedicoes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDeletarMedicoes.setBounds(313, 403, 214, 48);
		contentPane.add(btnDeletarMedicoes);
		
		JButton btnCarregarMedicoes = new JButton("Carregar Medições");
		btnCarregarMedicoes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCarregarMedicoes.setBorder(new LineBorder(new Color(92, 98, 131), 2, true));
		btnCarregarMedicoes.setBounds(313, 468, 214, 48);
		contentPane.add(btnCarregarMedicoes);
		
		JButton btnEsquerda = new JButton("<<");
		btnEsquerda.setEnabled(false);
		btnEsquerda.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnEsquerda.setBorder(new LineBorder(new Color(92, 98, 131), 2, true));
		btnEsquerda.setBounds(237, 468, 66, 48);
		contentPane.add(btnEsquerda);
		
		JButton btnDireita = new JButton(">>");
		btnDireita.setEnabled(false);
		btnDireita.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDireita.setBorder(new LineBorder(new Color(92, 98, 131), 2, true));
		btnDireita.setBounds(537, 468, 66, 48);
		contentPane.add(btnDireita);
		
		JLabel lblValorIndice = new JLabel("0");
		lblValorIndice.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblValorIndice.setBounds(35, 63, 108, 23);
		contentPane.add(lblValorIndice);
		
		JLabel lblIndice = new JLabel("Índice");
		lblIndice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIndice.setBounds(35, 39, 108, 23);
		contentPane.add(lblIndice);
		
		btnGerarMedicao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					MedicaoDAO medDAO = new MedicaoDAO();
					MedicaoService medSer = new MedicaoService(medDAO);
					MedicaoController medCont = new MedicaoController(medSer);
					
					Medicao med = medCont.realizarMedicao();
					
					exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, med);
					
				} catch (ControllerException ex) {
					ex.printStackTrace();
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
					Medicao medAtual = medicoes.getFirst();
					indiceMedicao = 0;
					
					exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
					lblValorIndice.setText(String.valueOf(indiceMedicao));
					
					btnDireita.setEnabled(true);
					btnEsquerda.setEnabled(true);
						
				} catch (ControllerException ex) {
					ex.printStackTrace();
				}	
			}
		});
		
		btnDireita.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Medicao medAtual;
				
				try {
					
					indiceMedicao ++;
					medAtual = medicoes.get(indiceMedicao);
					
					lblValorIndice.setText(String.valueOf(indiceMedicao));
					exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
					
				} catch (IndexOutOfBoundsException ex) {
					
					medAtual = medicoes.getFirst();
					indiceMedicao = medAtual.getId() - 1;
					
					lblValorIndice.setText(String.valueOf(indiceMedicao));
					exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
					
				}
				
			}
		});
		
		btnEsquerda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Medicao medAtual;
				
				try {
					
					indiceMedicao --;
					medAtual = medicoes.get(indiceMedicao);
					
					lblValorIndice.setText(String.valueOf(indiceMedicao));
					exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
					
				} catch (IndexOutOfBoundsException ex) {
					
					medAtual = medicoes.getLast();
					indiceMedicao = medAtual.getId() - 1;
					
					lblValorIndice.setText(String.valueOf(indiceMedicao));
					exibirNaTela(lblValorMedicao, lblValorData, lblValorHora, lblValorEstado, medAtual);
					
				}
				
			}
		});
		
		btnDeletarMedicoes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					MedicaoDAO medDAO = new MedicaoDAO();
					MedicaoService medSer = new MedicaoService(medDAO);
					MedicaoController medCont = new MedicaoController(medSer);
					
					medCont.apagarMedicoes();
				
					medicoes.clear();
					indiceMedicao = 0;
					
					btnDireita.setEnabled(false);
					btnEsquerda.setEnabled(false);
					
					JOptionPane.showMessageDialog(null, "Medições apagadas com sucesso.");
					
				} catch (ControllerException ex) {
					ex.printStackTrace();
				}
			}
		});
		

	}
	
	public void exibirNaTela(JLabel glicemia, JLabel data, JLabel hora, JLabel estado, Medicao med) {
		
		glicemia.setText(String.valueOf(med.getMedicao()));
		data.setText(String.valueOf(med.getDataMedicao()));
		hora.setText(String.valueOf(med.getHorarioMedicao()));
		estado.setText(Estado.estadoToString(med.getEstado()));
		
	}
}
