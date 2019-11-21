package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.DefaultListModel;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel conexionesPanel = new JPanel();
	private JLabel bitacoraConexionesTitle = new JLabel("Bitacora de conexiones");
	private JSeparator separator = new JSeparator();
	// Bitacora de conexiones
	private DefaultListModel<String> bitacoraConexionesListModel = new DefaultListModel<String>();
	private JList bitacoraConexionesList = new JList(bitacoraConexionesListModel);
	
	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("Aplicacion Administradora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane.setBounds(0, 0, 664, 378);
		contentPane.add(tabbedPane);
		
		tabbedPane.addTab("Conexiones", null, conexionesPanel, null);
		conexionesPanel.setLayout(null);
		
		bitacoraConexionesTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		bitacoraConexionesTitle.setBounds(10, 11, 209, 24);
		conexionesPanel.add(bitacoraConexionesTitle);
		
		separator.setBounds(10, 46, 209, 2);
		conexionesPanel.add(separator);
		
		bitacoraConexionesList.setBounds(256, 46, 334, 234);
		conexionesPanel.add(bitacoraConexionesList);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
	}
	
	/**
	 * Agregar una actividad nueva a la bitacora de conexiones
	 * @param actividad
	 */
	public void agregarABitacoraConexiones(String actividad) {
		this.bitacoraConexionesListModel.addElement(actividad);
	}
}
