package br.com.SimuladorDeGlicosimetro.Utils.ListaCircular;

public interface MyList<T> extends Iterable<T>{
	
	public T get(int indice);
	
	public void add(T elemento);
	
	public void add(T elemento, int indice);
	
	public void remove(int indice);
		
	public int getSize();
	
}
