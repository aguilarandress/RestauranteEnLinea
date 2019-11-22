package models.catalogo;

import java.util.ArrayList;

import models.alimento.Alimento;
import models.cola.Cola;

/**
 * Clase que representa el catalogo de comidas
 * @author Andres
 *
 */
public class Catalogo {
	
	private static float montoExpress;
	private static float montoEmpaque;
	private Cola<Alimento> alimentos;
	
	public Catalogo() {
		this.montoExpress = 0;
		this.montoEmpaque = 0;
		this.alimentos = new Cola<Alimento>();
	}
	
	/**
	 * Obtiene el monto express
	 * @return El monto que se realiza por un pedido express
	 */
	public static float getMontoExpress() {
		return montoExpress;
	}

	/**
	 * Configura el monto que se realiza por un pedido express
	 * @param montoExpress El nuevo monto
	 */
	public static void setMontoExpress(float montoExpress) {
		Catalogo.montoExpress = montoExpress;
	}
	
	/**
	 * Obtiene el monto del empaque
	 * @return El monto que se realiza por el empaque
	 */
	public static float getMontoEmpaque() {
		return montoEmpaque;
	}
	
	/**
	 * Configura el monto realizado por el empaque
	 * @param montoEmpaque El nuevo monto
	 */
	public static void setMontoEmpaque(float montoEmpaque) {
		Catalogo.montoEmpaque = montoEmpaque;
	}
	
	/**
	 * Obtiene la lista de alimentos del catalogo
	 * @return La lista de alimentos del catalogo
	 */
	public Cola<Alimento> getAlimentos() {
		return alimentos;
	}

	/**
	 * Configura la nueva lista de alimentos
	 * @param alimentos Configura la nueva lista de alimentos
	 */
	public void setAlimentos(Cola<Alimento> alimentos) {
		for (int posActual = 0; posActual < alimentos.getCantidad(); posActual++) {
			this.agregarAlimento(alimentos.get(posActual));
		}
	}
	
	/**
	 * Revisa si ya existe un alimento con un codigo
	 * @param codigoAlimento
	 * @return true si el alimento ya existe, false de lo contrario
	 */
	public boolean revisarSiAlimentoExiste(String codigoAlimento) {
		for (int posActual = 0; posActual < alimentos.getCantidad(); posActual++) {
			Alimento actual = alimentos.get(posActual);
			if (actual.getCodigo() == codigoAlimento) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Agregar un alimento nuevo al catalogo
	 * @param alimentoNuevo El alimento nuevo que se desea agregar
	 */
	public void agregarAlimento(Alimento alimentoNuevo) {
		if (!this.revisarSiAlimentoExiste(alimentoNuevo.getCodigo())) {
			this.alimentos.enqueue(alimentoNuevo);
		}
	}
}
