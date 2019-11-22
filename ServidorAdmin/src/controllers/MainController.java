package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import catalogoXML.CreadorXML;
import models.alimento.Alimento;
import models.catalogo.Catalogo;
import views.MainView;
import server.TCPServer;

/**
 * Controlador principal de la aplicacion
 * @author Andres
 *
 */
public class MainController {
	
	private MainView view;
	private TCPServer mainServer;
	private Catalogo catalogo;
	
	public MainController(MainView view, Catalogo catalogo) {
		this.view = view;
		this.catalogo = catalogo;
		
		// Iniciar servidor
		this.mainServer = new TCPServer(this);
		mainServer.start();
		
		// Agregar eventos a los componentes
		this.view.agregaActionListenerMontoExpressBtn(new EventoMontoExpressBtn());
		
		// Cargar alimentos del catalogo
		this.cargarCatalogo();
		view.setVisible(true);
	}
	
	/**
	 * Carga el catalogo de alimentos
	 */
	public void cargarCatalogo() {
		// TODO: Obtener monto express y de empaque del XML
		
		// Obtener alimentos guardados en el xml
		CreadorXML catalogo = CreadorXML.getInstance();
		// Cargar alimentos en el modelo
		ArrayList<Alimento> alimentos = catalogo.ObtenerCatalogo();
		this.catalogo.setAlimentos(alimentos);
		this.view.crearCatalogo(this.catalogo.getAlimentos());
	}
	
	/**
	 * Agregar una actividad nueva a la bitacora de conexiones
	 * @param actividad La actividad de conexion nueva
	 */
	public void agregarActividadDeConexion(String actividad) {
		this.view.agregarABitacoraConexiones(actividad);
	}
	
	private class EventoMontoExpressBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// Obtener monto ingresado
				String montoIngresado = view.getMontoExpressInput().getText();
				float montoExpress = Float.parseFloat(montoIngresado);
				// Guardar en el modelo
				Catalogo.setMontoExpress(montoExpress);
				view.getMontoExpressLabel().setText(montoIngresado);
				
				// TODO: Modificar en el XML
				view.getMontoExpressInput().setText("");
				view.displayMessage(true, "Monto express modificado");
			} catch (Exception parseExpcetion) {
				view.displayMessage(false, "Por favor ingrese un valor valido");
			}
		}
	}
	
}
