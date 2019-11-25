package views;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 
 * @author Kenneth Sanchez
 *
 */
public class PedidosTablaView extends JFrame{
	JTable tabla;
	
	/**
	 * Metodo Constructor
	 * @param pDatos
	 */
	public PedidosTablaView(Object[][] pDatos) {
		this.setTitle("Tabla de pedidos");
		this.setLayout(null);
		this.setResizable(false);
		this.setBounds(100, 100, 550, 80);
		
		String[] headers = {"Pedidos Totales", "En Sitio", "Express", "Recoger"};
		
		tabla = new JTable(pDatos, headers);
		tabla.setBounds(0, 30, 550, 130);
		tabla.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setViewportView(tabla);
		scrollPane.setBounds(0, 0, 550, 75);
		this.add(scrollPane);
		
		this.setVisible(true);
	}
}
