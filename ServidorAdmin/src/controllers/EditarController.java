package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import catalogoXML.CreadorXML;
import models.alimento.Alimento;
import views.EditarView;
import views.MainView;

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
			
			String nuevoNombre = vista.getNombreInput().getText().trim();
			String nuevasCalorias = vista.getCaloriasInput().getText().trim();
			String nuevoPrecio = vista.getPrecioInput().getText().trim();
			String nuevaImagen = vista.getImagenInput().getText().trim();
			String nuevaDescripcion = vista.getDescripcionInput().getText().trim();
			
			// Validacion
			if (nuevoNombre.equals("") || nuevasCalorias.equals("") ||
					nuevoPrecio.equals("") || nuevaImagen.equals("") ||
					nuevaDescripcion.equals("")) {
				JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
				return;
			}
			
			// Validacion de imagen
			if (!mainController.verificarImagen(nuevaImagen)) {
				JOptionPane.showMessageDialog(null, "Imagen invalida");
				return;
			}
			
			// Cambios al alimento
			alimentoSelected.setNombre(nuevoNombre);
			alimentoSelected.setCalorias(Float.valueOf(nuevasCalorias));
			alimentoSelected.setPrecio(Float.valueOf(nuevoPrecio));
			alimentoSelected.setImagenPath(nuevaImagen);
			alimentoSelected.setDescripcion(nuevaDescripcion);
			
			// Actualizacion de vista principal y XML
			CreadorXML.getInstance().RecrearCatalogo(mainController.getCatalogo().getAlimentos());
			mainController.crearCatalogo(mainController.getCatalogo().getAlimentos());
		}	
	}
}
