package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.alimento.Alimento;
import views.EditarView;

/**
 * Controlador para editar un platillo
 * @author Kenneth Sanchez
 *
 */
public class EditarController {
	
	EditarView vista;
	Alimento alimentoSelected;
	MainController mainController;
	
	/**
	 * Metodo constructor
	 */
	public EditarController(Alimento pAlimentoSelected, MainController pMainController) {
		vista = new EditarView();
		alimentoSelected = pAlimentoSelected;
		mainController = pMainController;
		
		// Eventos
		vista.getActualizarBtn().addActionListener(new EventoActualizar());
		
		// Cambia los textos
		vista.getCodigoLabel().setText(alimentoSelected.getCodigo());
		vista.getNombreInput().setText(alimentoSelected.getNombre());
		vista.getCaloriasInput().setText(String.valueOf(alimentoSelected.getCalorias()));
		vista.getPrecioInput().setText(String.valueOf(alimentoSelected.getPrecio()));
		vista.getImagenInput().setText(alimentoSelected.getImagenPath());
		vista.getDescripcionInput().setText(alimentoSelected.getDescripcion());
	}

	
	/**
	 * Clase evento que ejecuta la accion de actualizar informacion
	 * @author Kenneth Sanchez
	 *
	 */
	private class EventoActualizar implements ActionListener {

		/**
		 * Inicia cada vez que detecta un evento
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (vista.getNombreInput().equals("") || vista.getCaloriasInput().equals("") ||
					vista.getPrecioInput().equals("") || vista.getImagenInput().equals("") ||
					vista.getDescripcionInput().equals("")) {
				JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
				return;
			}
		
		}
		
	}

}
