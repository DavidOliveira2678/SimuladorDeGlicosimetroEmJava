package br.com.SimuladorDeGlicosimetro.Utils.ListaCircular;

import java.util.Iterator;
import java.util.Objects;

public class CircularList<T> implements MyList<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private Node<T> current;
	private int size;

	
	@Override
	public T get(int indice) {
		
		if (indice < 0 || indice >= this.size) throw new RuntimeException("Índice " + indice + " inválido.");
		
		Node<T> nodeAtual;
		
		if (indice < this.size / 2) {
			nodeAtual = this.head;
			
			for (int i = 0; i < indice ; i++) {
				nodeAtual = nodeAtual.next;
			}
			
		} else {
			
			nodeAtual = this.tail;
			
			for (int i = this.size - 1; i > indice ; i--) {
				nodeAtual = nodeAtual.previous;
			}
			
		}
		return nodeAtual.value;			
	}
	

	@Override
	public void add(T elemento) {
		
		if(Objects.isNull(elemento)) throw new NullPointerException("Elementos nulos não são permitidos na lista.");
		
		Node<T> newNode = new Node<>(elemento);
		
		if (this.size == 0) {
			
			this.head = newNode;
			this.tail = newNode;
			
			this.head.next = this.head;
			this.head.previous = this.head;
			this.current = this.head;
			
		} else {
			
			newNode.previous = this.tail;
			newNode.next = this.head;
			
			this.tail.next = newNode;
			this.head.previous = newNode;
			
			this.tail = newNode;
			
		}
		
		this.size ++;
		
	}

	
	@Override
	public void add(T elemento, int indice) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void remove(int indice) {
		
		if (indice < 0 || indice >= this.size) throw new RuntimeException("Índice " + indice + " inválido.");
		
		if (this.size == 1) {
			
			this.head = null;
			this.tail = null;

			this.size--;
			return;
			
		} else if (indice == 0) {
			
			this.head = this.head.next;
			this.tail.next = this.head;
			this.head.previous = this.tail;

			this.size--;
			return;
			
		} else if (indice == this.size - 1) {
			
			this.tail = this.tail.previous;
			this.tail.next = this.head;
			this.head.previous = this.tail;

			this.size--;
			return;
			
		} else {
			
			Node<T> nodeAtual = this.head;
			
			for (int i = 0; i < indice ; i++) {
				nodeAtual = nodeAtual.next;
			}
			
			nodeAtual.previous.next = nodeAtual.next;
			nodeAtual.next.previous = nodeAtual.previous;

			this.size--;
			
		}
		
	}
	

	@Override
	public int getSize() {
		return this.size;
	}

	
	@Override
	public int getIndexOf(T elemento) {
		
		if(Objects.isNull(elemento)) throw new NullPointerException("Elemento nulo.");
		
		Node<T> nodeAtual = this.head;
		
		for(int i = 0; i < this.size; i++) {
			
			if(nodeAtual.value.equals(elemento)) return i;
			
			nodeAtual = nodeAtual.next;
			
		}
		
		throw new RuntimeException("Elemento " + elemento + " não está presente na lista circular.");
		
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public T next() {
		this.current = current.next;
		return current.value;
	}
	
	
	@Override
	public T previous() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class Node<T> {
		
		T value;
		Node<T> previous;
		Node<T> next;
		
		Node(T value){
			this.value = value;
		}
	}
}


