package br.com.SimuladorDeGlicosimetro.Utils.ListaCircular;

public interface CircularList<T> extends MyList<T>{
	
	public int getIndexOf(T elemento);
	
	public boolean isEmpty();
	
	public T peek();
	
	public T next();
	
	public T previous();
	
	public void clear();
	
}
