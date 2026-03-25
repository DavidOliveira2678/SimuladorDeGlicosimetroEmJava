package br.com.SimuladorDeGlicosimetro.Utils.ListaCircular;

import java.util.Objects;
import java.util.function.Consumer;

public interface MyList<T> extends Iterable<T>{

	public T get(int indice);
	
	public void add(T elemento);
	
	public void add(T elemento, int indice);
	
	public void remove(int indice);
		
	public int getSize();
	
	public int getIndexOf(T elemento);
	
	public T next();
	
	public T previous();
	
}
