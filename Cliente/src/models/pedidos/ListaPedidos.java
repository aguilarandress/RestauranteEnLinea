package models.pedidos;

import java.util.LinkedList;

/**
 * 
 * @author Kenneth Sanchez
 * @author Fabian Vargas
 * @author Andres Aguilar
 */
public class ListaPedidos {

	private LinkedList<Pedido> pedidos;
	
	private int cantidadExpress, cantidadEnSitio, cantidadRecoger;
	
	/**
	 * Metodo Constructor
	 */
	public ListaPedidos() {
		pedidos = new LinkedList<Pedido>();
		cantidadExpress = 0;
		cantidadEnSitio = 0;
		cantidadRecoger = 0;
	}
	
	/**
	 * Agrega un nuevo pedido a la lista de pedidos
	 * @param pPedido
	 */
	public void agregarPedido(Pedido pPedido) {
		
		if (pPedido instanceof PedidoExpress) {
			this.cantidadExpress += 1;
		} else if (pPedido instanceof PedidoRecoger) {
			this.cantidadRecoger += 1;
		} else if (pPedido instanceof PedidoSitio) {
			this.cantidadEnSitio += 1;
		} else {
			return;
		}
		
		pedidos.add(pPedido);
	}
	
	/**
	 * Obtiene los pedidos
	 * @return LinkedList<Pedido>
	 */
	public LinkedList<Pedido> getPedidos() {
		return pedidos;
	}
	
	/**
	 * Cambia los pedidos
	 * @param pedidos
	 */
	public void setPedidos(LinkedList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	/**
	 * Obtiene la cantidad de pedidos express
	 * @return int
	 */
	public int getCantidadExpress() {
		return cantidadExpress;
	}
	
	/**
	 * Cambia la cantidad de pedidos express
	 * @param cantidadExpress
	 */
	public void setCantidadExpress(int cantidadExpress) {
		this.cantidadExpress = cantidadExpress;
	}
	
	/**
	 * Obtiene la cantidad de pedidos en sitio
	 * @return int
	 */
	public int getCantidadEnSitio() {
		return cantidadEnSitio;
	}

	/**
	 * Cambia la cantidad de pedidos en sitio
	 * @param cantidadEnSitio
	 */
	public void setCantidadEnSitio(int cantidadEnSitio) {
		this.cantidadEnSitio = cantidadEnSitio;
	}

	/**
	 * Obtiene la cantidad de pedidos para recoger
	 * @return int
	 */
	public int getCantidadRecoger() {
		return cantidadRecoger;
	}

	/**
	 * Cambia la cantidad de pedidos para recoger
	 * @param cantidadRecoger
	 */
	public void setCantidadRecoger(int cantidadRecoger) {
		this.cantidadRecoger = cantidadRecoger;
	}
}