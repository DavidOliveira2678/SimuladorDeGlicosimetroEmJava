package br.com.SimuladorDeGlicosimetro.View;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.SimuladorDeGlicosimetro.Utils.Constantes;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class TitleMenuView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public TitleMenuView(MainView mainView) {
		
		mainView.setTitle("GlucaJava");

		this.setSize(Constantes.LARGURA, Constantes.ALTURA);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		
		JLabel lblTitulo = new JLabel("GlucaJava");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Yu Gothic UI Semilight", Font.ITALIC, 92));
		lblTitulo.setBounds(216, 47, 490, 118);
		add(lblTitulo);
		
		
		JLabel lblDescricaoLinha1 = new JLabel("Um simulador de glicosímetro");
		lblDescricaoLinha1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescricaoLinha1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 32));
		lblDescricaoLinha1.setBounds(263, 193, 439, 64);
		add(lblDescricaoLinha1);
		
		
		JLabel lblDescricaoLinha2 = new JLabel("desenvolvido em Java.");
		lblDescricaoLinha2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescricaoLinha2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 32));
		lblDescricaoLinha2.setBounds(263, 228, 439, 64);
		add(lblDescricaoLinha2);
		
		
		JButton btnGlicosimetro = new JButton("Utilize o GlucaJava!");
		btnGlicosimetro.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 24));
		btnGlicosimetro.setBounds(361, 358, 246, 53);
		add(btnGlicosimetro);
		
		JLabel lblObsLinha1 = new JLabel("Projeto desenvolvido por David Willyam de Oliveira");
		lblObsLinha1.setHorizontalAlignment(SwingConstants.CENTER);
		lblObsLinha1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		lblObsLinha1.setBounds(229, 483, 518, 32);
		add(lblObsLinha1);
		
		JLabel lblObsLinha2 = new JLabel("Disponível para visualização em github.com/DavidOliveira2678/GlucaJava");
		lblObsLinha2.setHorizontalAlignment(SwingConstants.CENTER);
		lblObsLinha2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		lblObsLinha2.setBounds(229, 503, 518, 32);
		add(lblObsLinha2);
		
		
		btnGlicosimetro.addActionListener(e -> {
			mainView.goTo("medicao");
			mainView.setTitle("GlucaJava - GlucaJava");	
		});
	}
}
