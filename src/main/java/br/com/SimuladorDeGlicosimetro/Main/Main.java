package br.com.SimuladorDeGlicosimetro.Main;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import br.com.SimuladorDeGlicosimetro.View.MainView;

public class Main { 
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					
					MainView frame = new MainView();
					frame.setVisible(true);
					
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null, "Erro inesperado ao inicializar a aplicação", "Erro fatal", JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem técnica", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					
				}
			}
		});
		
	}
	
}