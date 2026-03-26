package br.com.SimuladorDeGlicosimetro.Utils.ListaCircular;

public interface CircularList<T> extends Iterable<T>{
	
	public T get(int indice);
	
	public void add(T elemento);
	
	public void add(T elemento, int indice);
	
	public void remove(int indice);
		
	public int getSize();
	
	public int getIndexOf(T elemento);
	
	public boolean isEmpty();
	
	public T peek();
	
	public T next();
	
	public T previous();
	
	public void clear();
	
}
