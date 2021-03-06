package models.pedidos;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import models.alimento.Alimento;

/**
 * 
 * @author Kenneth Sanchez
 * @author Fabian Vargas
 * @author Andres Aguilar
 */
public abstract class Pedido implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Alimento> alimentos;
	private String nombrePersona;
	private Date fecha;
	
	/**
	 * Agrega un nuevo pedido al set de alimentos del pedido
	 * @param pAlimento
	 */
	public void agregarAlimento(Alimento pAlimento) {
		alimentos.add(pAlimento);
	}
	
	/**
	 * Elimina un alimento que se encuentra en el set de alimentos
	 * @param pAlimento
	 */
	public void eliminarAlimento(Alimento pAlimento) {
		alimentos.remove(pAlimento);
	}
	
	/**
	 * Obtiene los alimentos del pedido
	 * @return LinkedList<Alimento>
	 */
	public LinkedList<Alimento> getAlimentos() {
		return alimentos;
	}
	
	/**
	 * Cambia el set de alimentos del pedido
	 * @param alimentos
	 */
	public void setAlimentos(LinkedList<Alimento> alimentos) {
		this.alimentos = alimentos;
	}
	
	/**
	 * Obtiene el nombre de la persona que hizo el pedido
	 * @return String
	 */
	public String getNombrePersona() {
		return nombrePersona;
	}
	
	/**
	 * Cambia el nombre de la persona que hizo el pedido
	 * @param nombrePersona
	 */
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	
	/**
	 * Obtiene la fecha en la que se hizo el pedido
	 * @return Date
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Cambia la fecha en la que se hizo el pedido
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
