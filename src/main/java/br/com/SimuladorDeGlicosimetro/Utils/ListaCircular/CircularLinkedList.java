package br.com.SimuladorDeGlicosimetro.Utils.ListaCircular;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Implementação de uma lista circular duplamente encadeada, permitindo a navegação contínua em ambas as direções.
 * 
 * @param <T> tipo de dado o qual a lista terá.
 */
public class CircularLinkedList<T> implements CircularList<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private Node<T> current;
	private int size;

	
	/**
	 * Retorna o elemento no índice especificado.
	 * 
	 * @param indice índice do elemento a ser retornado.
	 * @throws IndexOutOfBoundsException caso o índice seja inválido.
	 * @return o elemento no índice informado.
	 */
	@Override
	public T get(int indice) {
		
		if (indice < 0 || indice >= this.size) throw new IndexOutOfBoundsException("Índice " + indice + " inválido.");
		
		return getNode(indice).value;			
	}
	
	
	/**
	 * Adiciona um elemento ao final da lista.
	 * 
	 * @param elemento elemento a ser adicionado ao final da lista.
	 * @throws NullPointerException caso o elemento seja nulo.
	 */
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

	/**
	 * Insere o elemento no índice especificado, deslocando os elementos subsequentes (se houver).
	 * 
	 * @param elemento o elemento a ser inserido na lista.
	 * @param indice a posição em que o elemento deve ser inserido. Pode variar
	 * de zero até o tamanho atual da lista. O índice 0 representa uma inserção
	 * no início e o valor do tamanho da lista representa inserção no final.
	 * 
	 * @throws IndexOutOfBoundsException caso o índice informado seja menor que zero ou maior que o tamanho atual da lista.
	 * @throws NullPointerException caso o elemento informado seja nulo, uma vez que a lista não permite a inserção de valores nulos.
	 * 
	 */
	@Override
	public void add(T elemento, int indice) {
		
		if(indice < 0 || indice > this.size) throw new IndexOutOfBoundsException("Índice " + indice + " inválido.");

		if(Objects.isNull(elemento)) throw new NullPointerException("Elemento nulo.");
		
		Node<T> novoNode = new Node<T>(elemento);
		
		if (this.size == 0) {
			
			this.head = novoNode;
			this.tail = novoNode;
			
			this.head.next = this.head;
			this.head.previous = this.head;
			this.current = this.head;
			
		} else if (indice == 0) {
			
			novoNode.next = this.head;
			novoNode.previous = this.tail;
			
			
			this.tail.next = novoNode;
			this.head.previous = novoNode;
			
			this.head = novoNode;
			
		} else if (indice == this.size) {
			
			novoNode.previous = this.tail;
			novoNode.next = this.head;
			
			this.tail.next = novoNode;
			this.head.previous = novoNode;
			
			this.tail = novoNode;
			
		} else {
		
			Node<T> nodeAtual = getNode(indice);
			
			novoNode.previous = nodeAtual.previous;
			novoNode.next = nodeAtual;
			
			nodeAtual.previous.next = novoNode;
			nodeAtual.previous = novoNode;
			
		}
		
		this.size ++;
	}
	
	
	/**
	 * Remove o elemento no índice especificado.
	 * 
	 * @param indice é o índice do elemento a ser removido.
	 * @throws IndexOutOfBoundsException caso o índice seja inválido.
	 */
	@Override
	public void remove(int indice) {
		
		if (indice < 0 || indice >= this.size) throw new IndexOutOfBoundsException("Índice " + indice + " inválido.");
		
		if (this.size == 1) {
			
			this.head = null;
			this.tail = null;
			this.current = null;

			this.size--;
			return;
			
		} else if (indice == 0) {
			
			Node<T> nodeRemovido = this.head;
			
			if(nodeRemovido == this.current) this.current = this.current.next;
			
			this.head = this.head.next;
			this.tail.next = this.head;
			this.head.previous = this.tail;

			this.size--;
			return;
			
		} else if (indice == this.size - 1) {
			
			if(this.tail == this.current) this.current = this.current.next;
			
			this.tail = this.tail.previous;
			this.tail.next = this.head;
			this.head.previous = this.tail;

			this.size--;
			return;
			
		} else {
			
			Node<T> nodeAtual = getNode(indice);
			
			if(nodeAtual == this.current) this.current = this.current.next;
			
			nodeAtual.previous.next = nodeAtual.next;
			nodeAtual.next.previous = nodeAtual.previous;

			this.size--;
			
		}
		
	}
	
	
	/**
	 * Retorna o tamanho da lista.
	 * 
	 * @return o tamanho da lista.
	 */
	@Override
	public int getSize() {
		return this.size;
	}

	
	/**
	 * Retorna o índice da primeira ocorrência do elemento.
	 * 
	 * @throws NullPointerException caso o elemento a ser buscado seja nulo.
	 * @throws NoSuchElementException caso o elemento não esteja na lista.
	 * @return o índice da primeira ocorrência do elemento.
	 */
	@Override
	public int getIndexOf(T elemento) {
		
		if(Objects.isNull(elemento)) throw new NullPointerException("Elemento nulo.");
		
		Node<T> nodeAtual = this.head;
		
		for(int i = 0; i < this.size; i++) {
			
			if(nodeAtual.value.equals(elemento)) return i;
			
			nodeAtual = nodeAtual.next;
			
		}
		
		throw new NoSuchElementException("Elemento " + elemento + " não está presente na lista circular.");
		
	}

	
	/**
	 * Move o cursor para o próximo elemento e retorna seu valor.
	 * 
	 * @throws NoSuchElementException caso a lista esteja vazia.
	 * @return o próximo elemento apontado pelo cursor.
	 */
	@Override
	public T next() {

		if(isEmpty()) throw new NoSuchElementException("Lista vazia.");
		
		this.current = this.current.next;
		return this.current.value;
	}
	
	
	/**
	 * Move o cursor para o elemento anterior e retorna seu valor.
	 * 
	 * @throws NoSuchElementException caso a lista esteja vazia.
	 * @return o elemento anterior apontado pelo cursor.
	 */
	@Override
	public T previous() {
		
		if(isEmpty()) throw new NoSuchElementException("Lista vazia.");
		
		this.current = this.current.previous;
		return this.current.value;
	}
	
	/**
	 * Verifica se a lista está vazia.
	 * 
	 * @return {@code true} caso a lista esteja vazia e {@code false} caso a lista não esteja vazia.
	 */
	@Override
	public boolean isEmpty() {
		
		return this.size == 0;
		
	}
	
	
	/**
	 * Remove todos os elementos da lista, deixando-a vazia.
	 */
	@Override
	public void clear() {

		this.head = null;
		this.tail = null;
		this.current = null;
		this.size = 0;
		
	}
	
	/**
	 * Retorna o elemento atualmente apontado pelo cursor.
	 * 
	 * @see #next()
	 * @see #previous()
	 * 
	 * @return O elemento atual do cursor.
	 */
	@Override
	public T peek() {
		
		if(Objects.isNull(this.current)) throw new NoSuchElementException("Não há um elemento para ser visualizado.");
		
		return this.current.value;
	}
	
	
	private Node<T> getNode(int indice){
		
		if (indice < 0 || indice >= this.size) throw new IndexOutOfBoundsException("Índice " + indice + " inválido.");
		
		Node<T> nodeAtual;
		
		if ( indice < this.size / 2 ) {
			
			nodeAtual = this.head;
			
			for (int i = 0; i < indice; i++) {
				nodeAtual = nodeAtual.next;
			}
			
		} else {
			
			nodeAtual = this.tail;
			
			for (int i = this.size - 1; i > indice ; i--) {
				nodeAtual = nodeAtual.previous;
			}
			
		}
		
		return nodeAtual;
		
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			Node<T> nodeAtual = head;
			final int tamanhoInicial = size;
			int contador = 0;
			
			@Override
			public boolean hasNext() {
				
				return contador < tamanhoInicial;
				
			}
			
			@Override
			public T next() {
				
				if(!hasNext()) throw new NoSuchElementException("Não há um elemento para ser visualizado.");
				
				T valorAtual = nodeAtual.value;
				
				nodeAtual = nodeAtual.next;
				contador++;
				
				return valorAtual;
				
			}
			
		};
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