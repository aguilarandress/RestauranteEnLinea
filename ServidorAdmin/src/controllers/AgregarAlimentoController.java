package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import catalogoXML.CreadorXML;
import models.alimento.Alimento;
import views.AgregarAlimentoView;

/**
 * Controlador para agregar alimentos
 * @author Kenneth Sanchez
 *
 */
public class AgregarAlimentoController {
	AgregarAlimentoView vista;
	MainController mainController;
	
	/**
	 * Metodo constructor
	 */
	public AgregarAlimentoController(MainController pMainController) {
		vista = new AgregarAlimentoView();
		mainController = pMainController;
		vista.getCodigoInput().addKeyListener(new EventoSoloNumeros());
		vista.getActualizarBtn().addActionListener(new EventoAgregarPlatillo());
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
	
 	/**
	 * Evento para agregar un nuevo platillo
	 * @author Kenneth Sanchez
	 *
	 */
	private class EventoAgregarPlatillo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String nombre = vista.getNombreInput().getText().trim();
			String pathImagen = vista.getImagenInput().getText().trim();
			String calorias = vista.getCaloriasInput().getText().trim();
			String precio = vista.getPrecioInput().getText().trim();
			String descripcion = vista.getDescripcionInput().getText().trim();
			String numeroCodigo = vista.getCodigoInput().getText().trim();
			
			// Validacion
			if (nombre.isEmpty() || pathImagen.isEmpty() || calorias.isEmpty()
					|| precio.isEmpty() || descripcion.isEmpty() ||
					numeroCodigo.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
				return;
			}
			
			// Validacion float
			try {
				Float.parseFloat(calorias);
				Float.parseFloat(precio);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Error en precio o calorias");
				return;
			}
			
			// Validacion de imagen
			if (!mainController.verificarImagen(pathImagen)) {
				JOptionPane.showMessageDialog(null, "Imagen invalida");
				return;
			}
			
			// Se crea un nuevo alimento 
			Alimento nuevoAlimento = new Alimento(vista.getTipoBox().getSelectedIndex());
			
			// Crea el codigo del alimento
			String codigo = "";
			
			switch(nuevoAlimento.getTipo()) {
				case PLATO_FUERTE:
					codigo += "PRN-";
					break;
				case BEBIDA:
					codigo += "BEB-";
					break;
				case POSTRE:
					codigo += "PTR-";
					break;
				case ENTRADA:
					codigo += "ENT-";
					break;
			}
			
			codigo += numeroCodigo;
			
			// Validacion para que el codigo no se repita
			for (int i = 0; i < mainController.getCatalogo().getAlimentos().getCantidad(); i++) {
				Alimento actual = mainController.getCatalogo().getAlimentos().get(i);
				
				if (codigo.equals(actual.getCodigo())) {
					JOptionPane.showMessageDialog(null, "Ese codigo ya se encuentra en la lista");
					return;
				}
			}
			
			// Se cambian los atritbutos del nuevo alimento
			nuevoAlimento.setNombre(nombre);
			nuevoAlimento.setImagenPath(pathImagen);
			nuevoAlimento.setCalorias(Float.valueOf(calorias));
			nuevoAlimento.setPrecio(Float.valueOf(precio));
			nuevoAlimento.setCodigo(codigo);
			nuevoAlimento.setImagenPath(pathImagen);
			
			// Se agrega el alimento al catalogo
			mainController.getCatalogo().getAlimentos().enqueue(nuevoAlimento);
			
			// Actualiza el XML & el controlador 
			CreadorXML.getInstance().AgregarElementoNuevo(nuevoAlimento);
			mainController.crearCatalogo(mainController.getCatalogo().getAlimentos());
			vista.dispose();
		}
		
	}
}
