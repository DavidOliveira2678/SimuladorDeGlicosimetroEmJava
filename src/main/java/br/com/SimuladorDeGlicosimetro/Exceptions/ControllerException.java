package br.com.SimuladorDeGlicosimetro.Exceptions;

public class ControllerException extends Exception {

	public ControllerException(String msg) {
		super(msg);
	}
	
	public ControllerException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
