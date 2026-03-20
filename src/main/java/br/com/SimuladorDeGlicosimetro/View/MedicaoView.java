package br.com.SimuladorDeGlicosimetro.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.SimuladorDeGlicosimetro.Controllers.MedicaoController;
import br.com.SimuladorDeGlicosimetro.Data.MedicaoDAO;
import br.com.SimuladorDeGlicosimetro.Entities.Medicao;
import br.com.SimuladorDeGlicosimetro.Exceptions.ControllerException;
import br.com.SimuladorDeGlicosimetro.Services.MedicaoService;
import br.com.SimuladorDeGlicosimetro.Utils.Estado;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class MedicaoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private final MedicaoDAO medDAO = new MedicaoDAO();
	private final MedicaoService medSer = new MedicaoService(medDAO);
	private final MedicaoController medCont = new MedicaoController(medSer);

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MedicaoView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGerarMedicao = new JButton("Gerar Medição");
		btnGerarMedicao.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnGerarMedicao.setBounds(202, 274, 214, 48);
		contentPane.add(btnGerarMedicao);
		
		JLabel lblMedicao = new JLabel("0");
		lblMedicao.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblMedicao.setBounds(100, 108, 108, 23);
		contentPane.add(lblMedicao);
		
		JLabel lblData = new JLabel("0000-00-00");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblData.setBounds(230, 108, 143, 23);
		contentPane.add(lblData);
		
		JLabel lblHora = new JLabel("00:00:0000");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblHora.setBounds(399, 108, 178, 23);
		contentPane.add(lblHora);
		
		JLabel lblEstado = new JLabel("NULL");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEstado.setBounds(100, 195, 382, 23);
		contentPane.add(lblEstado);
		
		JLabel lblNewLabel = new JLabel("Glicemia");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(100, 83, 108, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblData_1 = new JLabel("Data (ANO-MÊS-DIA)");
		lblData_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData_1.setBounds(230, 83, 143, 23);
		contentPane.add(lblData_1);
		
		JLabel lblHorrio = new JLabel("Horário");
		lblHorrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorrio.setBounds(399, 83, 108, 23);
		contentPane.add(lblHorrio);
		
		JLabel lblEstadoDaGlicemia = new JLabel("Estado da Glicemia");
		lblEstadoDaGlicemia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstadoDaGlicemia.setBounds(100, 168, 180, 23);
		contentPane.add(lblEstadoDaGlicemia);
		
		JButton btnDeletarMedicoeses = new JButton("Deletar Medições");
		btnDeletarMedicoeses.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDeletarMedicoeses.setBounds(202, 340, 214, 48);
		contentPane.add(btnDeletarMedicoeses);
		
		btnGerarMedicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Medicao med = medCont.realizarMedicao();
					
					lblMedicao.setText(String.valueOf(med.getMedicao()));
					lblData.setText(String.valueOf(med.getDataMedicao()));
					lblHora.setText(String.valueOf(med.getHorarioMedicao()));
					lblEstado.setText(Estado.estadoToString(med.getEstado()));
					
				} catch (ControllerException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnDeletarMedicoeses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					medCont.apagarMedicoes();
					JOptionPane.showMessageDialog(null, "Medições apagadas com sucesso.");
					
				} catch (ControllerException ex) {
					ex.printStackTrace();
				}
			}
		});

	}
	
}
