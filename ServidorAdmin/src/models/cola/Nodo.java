package models.cola;

/**
 * 
 * @author Kenneth Sanchez
 *
 * @param <T>
 */
public class Nodo <T>{
	private T value;
	private Nodo<T> siguiente;
	private int prioridad;
	
	/**
	 * Metodo constructor
	 * @param pValue
	 */
	public Nodo(T pValue) {
		this.value = pValue;
		siguiente = null;
	}
	
	/**
	 * Obtiene el valor del nodo
	 * @return T
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Obtiene el nodo siguiente
	 * @return Nodo<T>
	 */
	public Nodo<T> getSiguiente() {
		return siguiente;
	}
	
	/**
	 * Cambia el siguiente de Nodo
	 * @param siguiente
	 */
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
	
	/**
	 * Obtiene la prioridad del nodo
	 * @return int
	 */
	public int getPrioridad() {
		return prioridad;
	}
	
	/**
	 * Cambia la prioridad del nodo
	 * @param prioridad
	 */
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
}