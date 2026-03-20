package br.com.SimuladorDeGlicosimetro.Exceptions;

public class ServiceException extends Exception {
	
	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(String msg, Throwable causa) {
		super(msg, causa);
	}
	
}
