package views;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Vista para agregar un platillo
 * @author Kenneth Sanchez
 *
 */
public class AgregarAlimentoView extends JFrame {
	
	private JLabel tipoPHLabel, nombrePHLabel, caloriasPHLabel,
				   precioPHLabel, descripcionPHLabel, imagePHLabel;
	
	private JComboBox tipoBox;
	
	private JTextField nombreInput, caloriasInput, precioInput, imagenInput, codigoInput;
	
	private JTextArea descripcionInput;
	
	private JButton actualizarBtn;
	
	/**
	 * Metodo constructor
	 */
	public AgregarAlimentoView() {
		this.setSize(500, 455);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// TIPO
		tipoPHLabel = new JLabel("Tipo: ");
		tipoPHLabel.setBounds(10, 10, 100, 25);
		this.add(tipoPHLabel);
		
		tipoBox = new JComboBox();
		String[] opciones = {"Bebida", "Entrada", "Plato Fuerte", "Poste"};
		tipoBox.setModel(new DefaultComboBoxModel(opciones));
		tipoBox.setBounds(120, 10, 250, 25);
		this.add(tipoBox);
		
		codigoInput = new JTextField("");
		codigoInput.setBounds(380, 10, 90, 25);
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
		descripcionInput.setBounds(120, 160, 350, 200);
		descripcionInput.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(descripcionInput);
		
		// Actualizar
		actualizarBtn = new JButton("Agregar");
		actualizarBtn.setBounds(10, 370, 460, 25);
		this.add(actualizarBtn);
		
		this.setVisible(true);
	}
	
	
	/**
	 * Retorna el textfield de codigo
	 * @return JTextField
	 */
	public JTextField getCodigoInput() {
		return codigoInput;
	}

	
	/**
	 * Retorna el box con los tipos
	 * @return JComboBox
	 */
	public JComboBox getTipoBox() {
		return tipoBox;
	}


	/**
	 * Retorna el textfield de imagen
	 * @return JTextField
	 */
	public JTextField getImagenInput() {
		return imagenInput;
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
