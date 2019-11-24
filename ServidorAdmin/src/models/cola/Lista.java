package models.cola;

/**
 * 
 * @author Kenneth Sanchez
 * 
 * @param <T>
 */
public class Lista<T> {
	private Nodo<T> start;
	private Nodo<T> end;
	private int cantidad;
	
	/**
	 * Metodo constructor
	 */
	public Lista() {
		start = end = null;
		cantidad = 0;
	}
	
	/**
	 * Agrega un nodo al final de la lista
	 * @param nodo
	 */
	public void addEnd(Nodo<T> nodo) {
		if (start==null) {
			start = nodo;
			end = nodo;
		} else {
			end.setSiguiente(nodo);
			end = nodo;
		}
		cantidad++;
	}
	
	/**
	 * Agrega un nodo al inicio de la lista
	 * @param pNodo
	 */
	public void addStart(Nodo<T> pNodo) {
		if (start==null) {
			addEnd(pNodo);
		} else {
			pNodo.setSiguiente(start);
			start = pNodo;
			cantidad++;
		}
	}
	
	/**
	 * Remueve el nodo del inicio
	 * @return Nodo<T>
	 */
	public Nodo<T> removeStart() {
		if (cantidad == 0) {
			return start;
		} else if(start.getSiguiente() == null) {
			Nodo<T> first = start;
			start = null;
			end = null;
			cantidad--;
			return first;
		} else {
			Nodo<T> first = this.getInicio();
			start = this.getInicio().getSiguiente();
			first.setSiguiente(null);
			cantidad--;
			return first;
		}
	}
	
	/**
	 * Remueve un valor especificado
	 * @param pValor
	 * @return T
	 */
	public T remove(T pValor) {
		if (this.getInicio().getValue() == pValor) {
			return this.removeStart().getValue();
		}
		
		if (this.getEnd().getValue() == pValor) {
			return this.removeEnd().getValue();
		}
		
		Nodo<T> actual = this.getInicio();
		T valor = null;
		
		for (int i = 0; i < this.getCantidad(); i++) {
			if (actual.getSiguiente().getValue() == pValor) {
				
				valor = actual.getSiguiente().getValue();
				Nodo<T> borrar = actual.getSiguiente();
				actual.setSiguiente(borrar.getSiguiente());
				borrar.setSiguiente(null);
				
				this.cantidad -= 1;
				
				break;
			} else {
				actual = actual.getSiguiente();
			}
		}
		return valor;
	}
	
	/**
	 * Remueve el nodo del final de la lista
	 * @return Nodo<T>
	 */
	public Nodo<T> removeEnd() {
		if(cantidad == 0) {
			return end;
		} else if(start.getSiguiente() == end) {
			Nodo<T> ultimo = end;
			end = start;
			start.setSiguiente(null);
			cantidad--;
			return ultimo;
		} else {
			Nodo<T> ultimo = this.getEnd();
			end = this.getNodo(cantidad - 2);
			end.setSiguiente(null);
			cantidad--;
			return ultimo;
		}
	}
	
	/**
	 * Inserta un Nodo en la posicion especificada
	 * @param pNodo
	 * @param pPos
	 */
	public void insert(Nodo<T> pNodo, int pPos) {
		if (pPos==0) {
			addStart(pNodo);
		} else if (pPos>=cantidad) {
			addEnd(pNodo);
		} else {
			Nodo<T> current = start;
			int currentPosition = 0;
			while (currentPosition<pPos-1) {
				current=current.getSiguiente();
				currentPosition++;
			}
			
			pNodo.setSiguiente(current.getSiguiente());
			current.setSiguiente(pNodo);
			cantidad++;
		}
	}
	
	/**
	 * Obtiene el nodo de la posicion especificada
	 * @param pNodo
	 * @return Nodo<T>
	 */
	public Nodo<T> getNodo(int pNodo) {
		if (pNodo == 0) {
			return this.getInicio();
		} else if (pNodo == cantidad - 1) {
			return this.getEnd();
		} else if (pNodo >= cantidad) {
			return null;
		} else {
			Nodo<T> currentNodo = start;
			int currentPosition = 0;
			while (currentPosition < pNodo) {
				currentNodo = currentNodo.getSiguiente();
				currentPosition++;
			}
			return currentNodo;
		}
	}
	
	/**
	 * Obtiene el valor de un nodo en cierta posicion
	 * @param pPos
	 * @return T si la posicion existe, null de lo contrario
	 */
	public T get(int pPos) {
		if (pPos >= this.getCantidad() || pPos < 0) {
			return null;
		}
		
		if (pPos == 0) {
			return this.getInicio().getValue();
		} else if (pPos == cantidad - 1) {
			return this.getEnd().getValue();
		} else {
			Nodo<T> nodoActual = this.getInicio();
			int posActual = 0;
			while (posActual < pPos) {
				nodoActual = nodoActual.getSiguiente();
				posActual++;
			}
			return nodoActual.getValue();
		}
	}
	
	/**
	 * Obtiene el inicio de la lista
	 * @return Nodo<T>
	 */
	public Nodo<T> getInicio() {
		return start;
	}
	
	/**
	 * Obtiene el final de la lista
	 * @return Nodo<T>
	 */
	public Nodo<T> getEnd() {
		return end;
	}
	
	/**
	 * Cambia la cantidad de nodos que hay
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
	 * Obtiene la cantidad de nodos que hay
	 * @return int
	 */
	public int getCantidad() {
		return cantidad;
	}
	
	/**
	 * Hace un print de todos los nodos con su posicion y prioridad
	 */
	public void showAll() {
		Nodo<T> current = start;
		int currentPosition = 0;
		while (currentPosition < cantidad) {
			System.out.println("Nodo " + currentPosition + ": " + current.getValue() + 
					" Prioridad: " + current.getPrioridad());
			current=current.getSiguiente();
			currentPosition++;
		}
	}
}
