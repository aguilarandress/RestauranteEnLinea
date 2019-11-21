package messages;

import java.io.Serializable;

import models.pedidos.Pedido;

/**
 * Clase serializable para enviar pedidos a traves del socket
 * @author Andres
 *
 */
public class PedidoMensaje implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedidoSolicitado;
	
	
	public PedidoMensaje (Pedido pedido) {
		this.pedidoSolicitado = pedido;
	}
	
	/**
	 * Obtener el pedido enviado
	 * @return El objeto de tipo pedido que se esta enviando 
	 */
	public Pedido getPedidoSolicitado() {
		return pedidoSolicitado;
	}
	
	/**
	 * Configura un nuevo pedido que se va a enviar 
	 * @param pedidoSolicitado El pedido nuevo
	 */
	public void setPedidoSolicitado(Pedido pedidoSolicitado) {
		this.pedidoSolicitado = pedidoSolicitado;
	}
	
}
