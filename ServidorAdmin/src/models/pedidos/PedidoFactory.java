package models.pedidos;

/**
 * 
 * @author Kenneth Sanchez
 * @author Fabian Vargas
 * @author Andres Aguilar
 */
public class PedidoFactory implements IConstants{
	
	/**
	 * Crea un nuevo pedido dependiendo del codigo pasado como
	 * parametro, en caso de que no se encuentre el codigo se
	 * retorna null.
	 * @param pTipoPedido
	 * @return Pedido
	 */
	public Pedido crearPedido(int pTipoPedido) {
		
		switch (pTipoPedido) {
		
			case PEDIDO_EXPRESS:
				return new PedidoExpress();
				
			case PEDIDO_RECOGER:
				return new PedidoRecoger();
				
			case PEDIDO_SITIO:
				return new PedidoSitio();
		}
		
		// No encuentra el codigo
		return null;
	}
}
