package views;

import catalogoXML.CreadorXML;

import models.alimento.*;
import models.cola.Cola;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JTree;

import java.awt.Color;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel conexionesPanel = new JPanel();
	private JLabel bitacoraConexionesTitle = new JLabel("Bitacora de conexiones");
	private JSeparator separator = new JSeparator();
	// Bitacora de conexiones
	private DefaultListModel<String> bitacoraConexionesListModel = new DefaultListModel<String>();
	private JList bitacoraConexionesList = new JList(bitacoraConexionesListModel);
	
	// Menu
	private JTree catalogoTree;
	private JLabel imagenLabel;
	private JTextArea descripLabel;
	private JPanel imagenPanel;
	private  JScrollPane scrollPaneDescrip;
	private JButton editarBtn;
	private JButton agregarBtn;
	
	// Montos
	private JLabel montoExpressLabel = new JLabel("");
	private JLabel montoEmpaqueLabel = new JLabel("");
	private JTextField montoExpressInput;
	private JTextField montoEmpaqueInput;
	private JButton montoExpressBtn = new JButton("Actualizar monto express");
	private JButton montoEmpaqueBtn = new JButton("Actualizar monto empaque");
	
	
	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("Aplicacion Administradora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane.setBounds(0, 0, 684, 470);
		contentPane.add(tabbedPane);
		
		tabbedPane.addTab("Conexiones", null, conexionesPanel, null);
		conexionesPanel.setLayout(null);
		
		bitacoraConexionesTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		bitacoraConexionesTitle.setBounds(10, 11, 209, 24);
		conexionesPanel.add(bitacoraConexionesTitle);
		
		separator.setBounds(10, 46, 209, 2);
		conexionesPanel.add(separator);
		
		bitacoraConexionesList.setBounds(65, 59, 535, 331);
		conexionesPanel.add(bitacoraConexionesList);
		
		// Menu
		catalogoTree = new JTree();
		catalogoTree.setBounds(0, 0, 200, 450);
		catalogoTree.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		editarBtn = new JButton("Editar");
		editarBtn.setBounds(150, 410, 100, 25);
		editarBtn.setEnabled(false);
		
		agregarBtn = new JButton("Agregar");
		agregarBtn.setBounds(0, 410, 100, 25);
		
		JScrollPane scrollPane = new JScrollPane(catalogoTree);
		scrollPane.setViewportView(catalogoTree);
		scrollPane.setBounds(0, 0, 250, 400);
		scrollPane.setBorder(null);
		
		imagenPanel = new JPanel();
		
		imagenLabel = new JLabel();
		imagenLabel.setBounds(0,50,400,400);
		
		imagenPanel.setBounds(262, 0, 385, 300);
		imagenPanel.add(imagenLabel);
		
		scrollPaneDescrip = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		panel_1.add(scrollPane);
		panel_1.add(imagenPanel);
		panel_1.add(editarBtn);
		panel_1.add(agregarBtn);
		
		panel_1.setLayout(null);
		tabbedPane.addTab("Menu", null, panel_1, null);
		scrollPaneDescrip.setBounds(262, 313, 385, 94);
		
		panel_1.add(scrollPaneDescrip);
		descripLabel = new JTextArea();
		scrollPaneDescrip.setColumnHeaderView(descripLabel);
		
		descripLabel.setLineWrap(true);
		descripLabel.setWrapStyleWord(true);
		descripLabel.setEditable(false);
		
		// Montos
		JPanel montosPanel = new JPanel();
		tabbedPane.addTab("Montos", null, montosPanel, null);
		montosPanel.setLayout(null);
		
		JLabel montosMenuTituloLabel = new JLabel("Montos del menu");
		montosMenuTituloLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		montosMenuTituloLabel.setBounds(10, 11, 134, 25);
		montosPanel.add(montosMenuTituloLabel);
		
		JSeparator montosMenuSeparator = new JSeparator();
		montosMenuSeparator.setBounds(10, 45, 134, 2);
		montosPanel.add(montosMenuSeparator);
		
		JLabel lblMontoExpress = new JLabel("Monto Express:");
		lblMontoExpress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMontoExpress.setBounds(94, 70, 190, 14);
		montosPanel.add(lblMontoExpress);
		
		montoExpressLabel.setBounds(94, 95, 190, 14);
		montosPanel.add(montoExpressLabel);
		
		montoExpressInput = new JTextField();
		montoExpressInput.setToolTipText("");
		montoExpressInput.setBounds(94, 136, 190, 20);
		montosPanel.add(montoExpressInput);
		montoExpressInput.setColumns(10);
		
		JLabel lblMontoEmpaque = new JLabel("Monto Empaque:");
		lblMontoEmpaque.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMontoEmpaque.setBounds(361, 70, 190, 14);
		montosPanel.add(lblMontoEmpaque);
		
		montoEmpaqueLabel.setBounds(361, 95, 190, 14);
		montosPanel.add(montoEmpaqueLabel);
		
		montoEmpaqueInput = new JTextField();
		montoEmpaqueInput.setToolTipText("");
		montoEmpaqueInput.setColumns(10);
		montoEmpaqueInput.setBounds(361, 136, 190, 20);
		montosPanel.add(montoEmpaqueInput);
		
		montoExpressBtn.setBounds(94, 194, 190, 23);
		montosPanel.add(montoExpressBtn);
		
		montoEmpaqueBtn.setBounds(361, 194, 190, 23);
		montosPanel.add(montoEmpaqueBtn);
	}
	
	public JButton getEditarBtn() {
		return editarBtn;
	}

	public JLabel getMontoExpressLabel() {
		return montoExpressLabel;
	}

	public JLabel getMontoEmpaqueLabel() {
		return montoEmpaqueLabel;
	}

	public JTextField getMontoExpressInput() {
		return montoExpressInput;
	}

	public JTextField getMontoEmpaqueInput() {
		return montoEmpaqueInput;
	}

	public JButton getMontoExpressBtn() {
		return montoExpressBtn;
	}

	public JButton getMontoEmpaqueBtn() {
		return montoEmpaqueBtn;
	}

	/**
	 * Agregar una actividad nueva a la bitacora de conexiones
	 * @param actividad
	 */
	public void agregarABitacoraConexiones(String actividad) {
		this.bitacoraConexionesListModel.addElement(actividad);
	}
	
	
	/**
	 * Despliega un mensaje en pantalla con un dialog
	 * @param success Si el mensaje es de exito o no
	 * @param message El mensaje que se desea desplegar
	 */
	public void displayMessage(boolean success, String message) {
		JOptionPane.showMessageDialog(this, message, "Mensaje", success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * Obtiene el JTree con el catalogo
	 * @return JTree
	 */
	public JTree getTreeCatalogo() {
		return this.catalogoTree;
	}
	
	/**
	 * Obtiene el input para la descripcion
	 * @return JTextArea
	 */
	public JTextArea getDescripLabel() {
		return descripLabel;
	}

	
	/**
	 * Obtiene el label en el que se carga la imagen
	 * @return JLabel
	 */
	public JLabel getImagenLabel() {
		return imagenLabel;
	}

	
}
