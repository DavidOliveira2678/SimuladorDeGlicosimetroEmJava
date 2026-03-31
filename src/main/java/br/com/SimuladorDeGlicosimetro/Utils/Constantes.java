package br.com.SimuladorDeGlicosimetro.Utils;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Constantes {
	
	public static final DateTimeFormatter FORMATTER_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static final DateTimeFormatter FORMATTER_HORARIO = DateTimeFormatter.ofPattern("HH:mm:ss");

	public static final int LARGURA = 940;
	
	public static final int ALTURA = 580;
	
	public static final WindowAdapter FECHAR_TELA_CONFIRM = new WindowAdapter() {
		
		@Override
		public void windowClosing(WindowEvent e ) {
			int confirmacao = JOptionPane.showInternalConfirmDialog(
					null,
					"Você está fechando o GlucaJava.\nDeseja prosseguir?",
					"Mensagem de saída",
					JOptionPane.YES_NO_OPTION
					);
			
			if (confirmacao == JOptionPane.YES_OPTION) {
				e.getWindow().dispose();
				System.exit(0);
			}
			
		}
		
	};
}
