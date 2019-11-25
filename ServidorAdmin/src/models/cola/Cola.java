package models.cola;

/**
 * 
 *  Cola generica, ordenado por priordad de mayor a menor
 * 
 * @author Kenneth Sanchez
 * @param <T>
 */
public class Cola<T> extends Lista<T>{
	
	private int menorPrioridad = 0;
	
	/**
	 * Encola un elemento con la menor prioridad posible por defecto
	 * @param pValor
	 */
	public void enqueue(T pValor) {
		this.enqueue(pValor, menorPrioridad);
	}
	
	/**
	 * Encola un elemento dependiendo de la prioridad especificada
	 * @param pValor
	 * @param prioridad
	 */
	public void enqueue(T pValor, int prioridad) {
		
		Nodo<T> nuevoNodo = new Nodo<T>(pValor);
		
		nuevoNodo.setPrioridad(prioridad);
		
		if (this.getCantidad() == 0 || nuevoNodo.getPrioridad() == menorPrioridad) {
		
			this.addEnd(nuevoNodo);
			
		} else {
			// Numero de prioridad menor o igual al ultimo
			if (nuevoNodo.getPrioridad() <= this.getEnd().getPrioridad() ) { 

				this.addEnd(nuevoNodo);
			
			// Numero de prioridad mayor al inicio de la cola
			} else if (nuevoNodo.getPrioridad() > this.getInicio().getPrioridad()) {
				
				this.addStart(nuevoNodo);
			
			} else { // Caso general
				
				Nodo<T> actual = this.getInicio();

				while (actual != null) {
					
					if (actual.getPrioridad() >= nuevoNodo.getPrioridad() &&
						actual.getSiguiente().getPrioridad() < nuevoNodo.getPrioridad()) {
			
						nuevoNodo.setSiguiente(actual.getSiguiente());
						actual.setSiguiente(nuevoNodo);
						
						this.setCantidad(this.getCantidad() + 1);
						break;
						
					} else {
						
						actual = actual.getSiguiente();
						
					}
				}
				
			}
		}
	}
	
	/**
	 * Remueve el elemento al inicio de la cola
	 * @return T
	 */
	public T dequeue() {
		return this.removeStart().getValue();
	}
	
	/**
	 * Revisa si la cola esta vacia
	 * @return boolean
	 */
	public boolean isEmpty() {
		return this.getCantidad() == 0;
	}
	
	/**
	 * Cambia la prioridad del elemento especificado
	 * @param pValor
	 * @param nuevaPrioridad
	 */
	public void cambiarPrioridad(T pValor, int nuevaPrioridad) {
		this.remove(pValor);
		this.enqueue(pValor, nuevaPrioridad);
		
	}

	
	public static void main(String[] args) {
		Cola<String> a = new Cola<String>();
		
		a.enqueue("A", 2);
		a.enqueue("B", 5);
		a.enqueue("C", 3);
		a.enqueue("D", 10);
			
		a.cambiarPrioridad("C", 6);
		a.cambiarPrioridad("A", 8);
		a.cambiarPrioridad("D", 3);
		
		a.remove("C");

		
		a.showAll();
	}
}
