package br.com.SimuladorDeGlicosimetro.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.SimuladorDeGlicosimetro.Exceptions.DatabaseException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConectorBD {
	private static final Dotenv ENV = Dotenv.configure().directory("./").filename(".env").ignoreIfMissing().load();
	private static final String nomeBD = ENV.get("NOME_BD");
	private static final String usuarioBD = ENV.get("USUARIO_BD");
	private static final String senhaBD = ENV.get("SENHA_BD");
	private static final int localHostBD = Integer.valueOf(ENV.get("LOCALHOST_BD"));
	private static final String urlBD = "jdbc:mysql://localhost:" + localHostBD + "/" + nomeBD;
	private static Connection conector;
	
	public static Connection conectar() throws DatabaseException {
		try {
			
			if(conector == null) {
				conector = DriverManager.getConnection(urlBD, usuarioBD, senhaBD);
				return conector;
				
			} else {
				return conector;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
