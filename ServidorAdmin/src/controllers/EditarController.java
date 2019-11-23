package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	/**
	 * Metodo constructor
	 */
	public EditarController(Alimento pAlimentoSelected) {
		vista = new EditarView();
		alimentoSelected = pAlimentoSelected;
		
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

	
	private class EventoActualizar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("AAA");
		}
		
	}

}
