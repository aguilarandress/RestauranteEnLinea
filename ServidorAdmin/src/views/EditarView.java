package views;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Vista para editar un platillo
 * @author Kenneth Sanchez
 *
 */
public class EditarView extends JFrame {
	
	private JLabel codigoPHLabel, nombrePHLabel, caloriasPHLabel,
				   precioPHLabel, descripcionPHLabel, imagePHLabel;
	
	private JLabel codigoLabel;
	
	private JTextField nombreInput, caloriasInput, precioInput, imagenInput,
						codigoInput;
	
	private JTextArea descripcionInput;
	
	private JButton actualizarBtn, eliminarBtn;
	
	private JCheckBox habilitadoCheck;
	
	/**
	 * Metodo constructor
	 */
	public EditarView() {
		this.setSize(500, 475);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// CODIGO
		codigoPHLabel = new JLabel("Codigo: ");
		codigoPHLabel.setBounds(10, 10, 100, 25);
		this.add(codigoPHLabel);
		
		codigoLabel = new JLabel("");
		codigoLabel.setBounds(120, 10, 70, 25);
		this.add(codigoLabel);
		
		codigoInput = new JTextField("");
		codigoInput.setBounds(145, 10, 325, 25);
		this.add(codigoInput);
		
		// NOMBRE
		nombrePHLabel = new JLabel("Nombre: ");
		nombrePHLabel.setBounds(10, 40, 100, 25);
		this.add(nombrePHLabel);
		
		nombreInput = new JTextField("");
		nombreInput.setBounds(120, 40, 350, 25);
		this.add(nombreInput);
		
		// CALORIAS
		caloriasPHLabel = new JLabel("Calorias: ");
		caloriasPHLabel.setBounds(10, 70, 100, 25);
		this.add(caloriasPHLabel);
		
		caloriasInput = new JTextField("");
		caloriasInput.setBounds(120, 70, 350, 25);
		this.add(caloriasInput);
		
		// PRECIO
		precioPHLabel = new JLabel("Precio: ");
		precioPHLabel.setBounds(10, 100, 100, 25);
		this.add(precioPHLabel);
		
		precioInput = new JTextField("");
		precioInput.setBounds(120, 100, 350, 25);
		this.add(precioInput);
		
		// IMAGEN
		imagePHLabel = new JLabel("Imagen: ");
		imagePHLabel.setBounds(10, 130, 100, 25);
		this.add(imagePHLabel);
		
		imagenInput = new JTextField("");
		imagenInput.setBounds(120, 130, 350, 25);
		this.add(imagenInput);
		
		// DESCRIPCION
		descripcionPHLabel = new JLabel("Descripcion: ");
		descripcionPHLabel.setBounds(10, 160, 100, 25);
		this.add(descripcionPHLabel);
		
		descripcionInput = new JTextArea("");
		descripcionInput.setLineWrap(true);
		descripcionInput.setWrapStyleWord(true);
		descripcionInput.setBounds(120, 160, 350, 200);
		descripcionInput.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(descripcionInput);

		// HABILITAR
		habilitadoCheck = new JCheckBox("HABILITADO");
		habilitadoCheck.setBounds(10, 365, 100, 25);
		this.add(habilitadoCheck);
		
		// Separador
		JSeparator separador = new JSeparator();
		separador.setBounds(10, 395, 460, 10);
		this.add(separador);
		
		// ACTUALIZAR
		actualizarBtn = new JButton("Actualizar");
		actualizarBtn.setBounds(10, 405, 210, 25);
		this.add(actualizarBtn);
		
		// ELIMINAR
		eliminarBtn = new JButton("Eliminar");
		eliminarBtn.setBounds(230, 405, 240, 25);
		this.add(eliminarBtn);
		
		
		this.setVisible(true);
	}
	

	/**
	 * Retorna el checkbox para habilitar el alimento
	 * @return JCheckBox
	 */
	public JCheckBox getHabilitadoCheck() {
		return habilitadoCheck;
	}


	/**
	 * Retorna el boton de eliminar
	 * @return JButton
	 */
	public JButton getEliminarBtn() {
		return eliminarBtn;
	}

	
	/**
	 * Retorna el textfield de imagen
	 * @return JTextField
	 */
	public JTextField getImagenInput() {
		return imagenInput;
	}

	
	/**
	 * Retorna el textfield de codigo
	 * @return JTextField
	 */
	public JTextField getCodigoInput() {
		return codigoInput;
	}


	/**
	 * Retorna el codigo del platillo
	 * @return JLabel
	 */
	public JLabel getCodigoLabel() {
		return codigoLabel;
	}
	
	/**
	 * Retorna el textfield de nombre
	 * @return JTextField
	 */
	public JTextField getNombreInput() {
		return nombreInput;
	}

	/**
	 * Retorna el textfield de calorias
	 * @return JTextField
	 */
	public JTextField getCaloriasInput() {
		return caloriasInput;
	}


	/**
	 * Retorna el textfield de precio
	 * @return JTextField
	 */
	public JTextField getPrecioInput() {
		return precioInput;
	}
	
	/**
	 * Retorna el boton de actualizar platillo
	 * @return JButton
	 */
	public JButton getActualizarBtn() {
		return actualizarBtn;
	}

	/**
	 * Retorna el textarea de descripcion
	 * @return JTextArea
	 */
	public JTextArea getDescripcionInput() {
		return descripcionInput;
	}
}
