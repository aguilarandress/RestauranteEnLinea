package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class AbrirPlatilloView extends JFrame{
	JLabel nombreLabel;
	JLabel precioLabel;
	JLabel caloriasLabel;
	JLabel racionLabel;
	JLabel nombrePlatillo;
	JLabel precioPlatillo;
	JLabel caloriasPlatillo;
	JLabel racionPlatillo;
	JSpinner spinner;
	JButton btnComprar;
	private JLabel lblCantidad;
	
	public AbrirPlatilloView() {
		setTitle("Ver platillo");
		setSize(452,389);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Nombre Label
		nombreLabel = new JLabel();
		nombreLabel.setBounds(20, 20, 120, 20);
		nombreLabel.setText("Nombre:");
		getContentPane().add(nombreLabel);
		//Precio Label 
		precioLabel = new JLabel();
		precioLabel.setBounds(20, 70, 120, 20);
		precioLabel.setText("Precio:");
		getContentPane().add(precioLabel);
		//Calorias Label 
		caloriasLabel = new JLabel();
		caloriasLabel.setText("Calorias:");
		caloriasLabel.setBounds(20, 120, 120, 20);
		getContentPane().add(caloriasLabel);
		// Racion Label
		racionLabel = new JLabel();
		racionLabel.setText("Racion:");
		racionLabel.setBounds(20, 170, 120, 20);
		getContentPane().add(racionLabel);
		
		// Nombre Platillo
		nombrePlatillo = new JLabel();
		nombrePlatillo.setBounds(120, 20, 120, 20);
		nombrePlatillo.setText("");
		getContentPane().add(nombrePlatillo);
		//Precio Platillo
		precioPlatillo = new JLabel();
		precioPlatillo.setBounds(120, 70, 120, 20);
		precioPlatillo.setText("");
		getContentPane().add(precioPlatillo);
		//Calorias Platillo
		caloriasPlatillo = new JLabel();
		caloriasPlatillo.setText("");
		caloriasPlatillo.setBounds(120, 120, 120, 20);
		getContentPane().add(caloriasPlatillo);
		// Racion Platillo
		racionPlatillo = new JLabel();
		racionPlatillo.setText("");
		racionPlatillo.setBounds(120, 170, 120, 20);
		getContentPane().add(racionPlatillo);
		
		spinner = new JSpinner();
		spinner.setBounds(20, 264, 62, 22);
		getContentPane().add(spinner);
		
		lblCantidad = new JLabel("Cantidad a seleccionar:");
		lblCantidad.setBounds(20, 234, 163, 16);
		getContentPane().add(lblCantidad);
		
		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(289, 302, 97, 25);
		getContentPane().add(btnComprar);
		this.setVisible(true);
	}

	public JLabel getNombrePlatillo() {
		return nombrePlatillo;
	}

	public void setNombrePlatillo(JLabel nombrePlatillo) {
		this.nombrePlatillo = nombrePlatillo;
	}

	public JLabel getPrecioPlatillo() {
		return precioPlatillo;
	}

	public void setPrecioPlatillo(JLabel precioPlatillo) {
		this.precioPlatillo = precioPlatillo;
	}

	public JLabel getCaloriasPlatillo() {
		return caloriasPlatillo;
	}

	public void setCaloriasPlatillo(JLabel caloriasPlatillo) {
		this.caloriasPlatillo = caloriasPlatillo;
	}

	public JLabel getRacionPlatillo() {
		return racionPlatillo;
	}

	public void setRacionPlatillo(JLabel racionPlatillo) {
		this.racionPlatillo = racionPlatillo;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}
	
	public JButton getBtnComprar() {
		return btnComprar;
	}

	public void setBtnComprar(JButton btnComprar) {
		this.btnComprar = btnComprar;
	}
}
