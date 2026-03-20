package br.com.SimuladorDeGlicosimetro.Exceptions;

public class DatabaseException extends Exception {
	
	public DatabaseException(String msg) {
		super(msg);
	}
	
	public DatabaseException(String msg, Throwable causa) {
		super(msg, causa);
	}
	
}
