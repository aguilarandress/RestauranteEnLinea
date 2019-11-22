package controllers;

import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import catalogoXML.CreadorXML;
import models.alimento.Alimento;
import models.cola.Cola;
import server.TCPServer;

public class MainController {
	
	private MainView view;
	private TCPServer mainServer;
	
	public MainController(MainView view) {
		this.view = view;
		
		this.mainServer = new TCPServer(this);
		mainServer.start();
		
		// Event listeners
		// view.addBtnActionListener(new EventoBtn());
		
		// Cargar alimentos del catalogo
		this.cargarCatalogo();
		view.setVisible(true);
	}
	
	// public void setTextField(String message) { this.view.setTextField(message); }
	
	/**
	 * Carga el catalogo de alimentos
	 */
	public void cargarCatalogo() {
		// Obtener alimentos guardados en el xml
		CreadorXML catalogo = CreadorXML.getInstance();
		Cola<Alimento> alimentos = catalogo.ObtenerCatalogo();
		this.view.crearCatalogo(alimentos);
	}
	
	/**
	 * Agregar una actividad nueva a la bitacora de conexiones
	 * @param actividad La actividad de conexion nueva
	 */
	public void agregarActividadDeConexion(String actividad) {
		this.view.agregarABitacoraConexiones(actividad);
	}
	
	private class EventoBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainServer.writeToAll("Hello clients...");
		}
		
	}
	
}
