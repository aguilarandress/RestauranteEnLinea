package messages;

import java.io.Serializable;

import models.alimento.Alimento;

/**
 * Clase para enviar un objecto alimento a traves de un socket
 * @author Andres
 *
 */
public class AlimentoMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Alimento alimento;
	private byte contenidoImagen[];
	
	public AlimentoMessage(Alimento alimento, byte contenidoImagen[]) {
		this.alimento = alimento;
		this.contenidoImagen = contenidoImagen;
	}
	
	/**
	 * Obtener el alimento que contiene
	 * @return
	 */
	public Alimento getAlimento() {
		return alimento;
	}

	/**
	 * 
	 * @param alimento
	 */
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public byte[] getContenidoImagen() {
		return contenidoImagen;
	}

	public void setContenidoImagen(byte[] contenidoImagen) {
		this.contenidoImagen = contenidoImagen;
	}
	
}
