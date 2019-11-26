package controllers;
import views.AbrirPlatilloView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.alimento.Alimento;
/**
 * Controlador para la view de la ventana para comprar un platillo
 * @author Fabian Vargas
 *
 */
public class AbrirPlatilloController {
	AbrirPlatilloView view;
	MainController controller;
	Alimento alimentoSelected;
	
	public AbrirPlatilloController(MainController controller) {
		view = new AbrirPlatilloView();
		this.controller = controller;
		alimentoSelected = controller.getAlimentoSelected();
		System.out.println(alimentoSelected.getNombre());
		
		view.getNombrePlatillo().setText(alimentoSelected.getNombre());
		view.getPrecioPlatillo().setText(Float.toString(alimentoSelected.getPrecio()));
		view.getCaloriasPlatillo().setText(Float.toString(alimentoSelected.getCalorias()));
		view.getRacionPlatillo().setText(Integer.toString(alimentoSelected.getRacion()));
		
		view.getBtnComprar().addActionListener(new EventoComprarBtn());
	}
	
	/**
	 * Evento boton para comprar platillos
	 * @author Fabi�n Vargas
	 *
	 */
	private class EventoComprarBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int cantidad  = Integer.valueOf(view.getSpinner().getValue().toString());
			for(int i = 0; i<cantidad;i++) {
				controller.getAlimentosPedidos().add(alimentoSelected);	
			}
			controller.agregarTablaCarrito(alimentoSelected.getNombre(), Integer.toString(cantidad));
			view.dispose();
		}
		
	}
}