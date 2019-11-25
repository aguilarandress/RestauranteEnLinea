package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	public EditarController(MainController pMainController) {
		vista = new EditarView();
		mainController = pMainController;
		alimentoSelected = mainController.getAlimentoSelected();
		
		// Eventos
		vista.getActualizarBtn().addActionListener(new EventoActualizar());
		vista.getCodigoInput().addKeyListener(new EventoSoloNumeros());
		vista.getEliminarBtn().addActionListener(new EventoEliminar());
		
		// Parte el codigo del alimento
		String[] codigo = alimentoSelected.getCodigo().split("-");
		
		// Cambia los textos
		vista.getCodigoLabel().setText(codigo[0] += "-");
		vista.getCodigoInput().setText(codigo[1]);
		vista.getNombreInput().setText(alimentoSelected.getNombre());
		vista.getCaloriasInput().setText(String.valueOf(alimentoSelected.getCalorias()));
		vista.getPrecioInput().setText(String.valueOf(alimentoSelected.getPrecio()));
		vista.getImagenInput().setText(alimentoSelected.getImagenPath());
		vista.getDescripcionInput().setText(alimentoSelected.getDescripcion());
		vista.getHabilitadoCheck().setSelected(alimentoSelected.isHabilitado());
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
			String numCodigo = vista.getCodigoInput().getText().trim();
			
			boolean estadoHabilitado = vista.getHabilitadoCheck().isSelected();
			
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
			
			// Validacion de float
			try {
				Float.parseFloat(nuevasCalorias);
				Float.parseFloat(nuevoPrecio);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Error al editar calorias o precio");
				return;
			}
			
			// Concatenacion de codigo
			String[] codigoSplit = alimentoSelected.getCodigo().split("-");
			String nuevoCodigo = codigoSplit[0];
			nuevoCodigo += "-";
			nuevoCodigo += numCodigo;
			
			// Validacion codigo ya existente
			for (int i = 0; i < mainController.getCatalogo().getAlimentos().getCantidad(); i++) {
				Alimento actual = mainController.getCatalogo().getAlimentos().get(i);
				
				if (actual.equals(alimentoSelected)) continue;
				
				if (actual.getCodigo().equals(nuevoCodigo)) {
					JOptionPane.showMessageDialog(null, "Ese codigo ya existe");
					return;
				}
			}
			
			// Cambios al alimento
			alimentoSelected.setNombre(nuevoNombre);
			alimentoSelected.setCalorias(Float.valueOf(nuevasCalorias));
			alimentoSelected.setPrecio(Float.valueOf(nuevoPrecio));
			alimentoSelected.setImagenPath(nuevaImagen);
			alimentoSelected.setDescripcion(nuevaDescripcion);
			alimentoSelected.setCodigo(nuevoCodigo);
			alimentoSelected.setHabilitado(estadoHabilitado);
			
			// Actualizacion de vista principal y XML
			CreadorXML.getInstance().RecrearCatalogo(mainController.getCatalogo().getAlimentos());
			mainController.crearCatalogo(mainController.getCatalogo().getAlimentos());
			
			// TODO: Enviar a todos los sockets al ser actualizado
			mainController.getMainServer().actualizarAlimentos();
			
			vista.dispose();
		}	
	}
	
	/**
	 * Evento para eliminar un platillo del catalogo
	 * @author Kenneth Sanchez 
	 *
	 */
	private class EventoEliminar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Elimina el alimento del catalogo
			mainController.getCatalogo().getAlimentos().remove(alimentoSelected);
			
			// Actualiza la vista y XML
			CreadorXML.getInstance().RecrearCatalogo(mainController.getCatalogo().getAlimentos());
			mainController.crearCatalogo(mainController.getCatalogo().getAlimentos());
			mainController.getMainServer().actualizarAlimentos();
			vista.dispose();
		}
		
	}
	
	/**
	 * Evento para solo aceptar numeros como input
	 * @author Kenneth Sanchez
	 *
	 */
	private class EventoSoloNumeros extends KeyAdapter {
		
		/**
		 * Evento para solo aceptar numeros
		 */
		public void keyPressed(KeyEvent ke) {
            String value = vista.getCodigoInput().getText().trim();
            int l = value.length();
            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == 8) {
               vista.getCodigoInput().setEditable(true);
            } else {
               vista.getCodigoInput().setEditable(false);
            }
         }
	}
}
