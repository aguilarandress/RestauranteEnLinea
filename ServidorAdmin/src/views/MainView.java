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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private JButton editarBtn, agregarBtn;
	
	// Montos
	private JLabel montoExpressLabel = new JLabel("");
	private JLabel montoEmpaqueLabel = new JLabel("");
	private JTextField montoExpressInput;
	private JTextField montoEmpaqueInput;
	private JButton montoExpressBtn = new JButton("Actualizar monto express");
	private JButton montoEmpaqueBtn = new JButton("Actualizar monto empaque");
	
	// Historial de pedidos
	private DefaultListModel<String> historialPedidosListModel = new DefaultListModel<String>();
	private JList historialPedidosList = new JList(historialPedidosListModel);
	
	// Pedidos 
	private JLabel topPedidosLabel, nuncaPedidosLabel;
	private JButton verGraficoBtn, verTablaBtn, actualizarPedidosBtn;
	private JList listaTopPedidos, listaNuncaPedidos;
	private JSeparator separator_1;
	
	// Actividades en el servidor
	private JList listaActividades;
	private DefaultListModel<String> listaActividadesModel;
	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("Aplicacion Administradora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 520);
		setResizable(false);
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
		
		/*
		 * MENU
		 */
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
		
		/*
		 * MONTOS
		 */
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
		
		/*
		 * ESTADISTICAS
		 */
		JPanel estadisticasPanel = new JPanel();
		tabbedPane.addTab("Estadisticas", null, estadisticasPanel, null);
		estadisticasPanel.setLayout(null);
		
		topPedidosLabel = new JLabel("TOP 10:");
		topPedidosLabel.setBounds(30, 5, 150, 25);
		estadisticasPanel.add(topPedidosLabel);
		
		listaTopPedidos = new JList();
		listaTopPedidos.setBounds(30, 30, 150, 400);
		listaTopPedidos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		estadisticasPanel.add(listaTopPedidos);
		
		nuncaPedidosLabel = new JLabel("Nunca Pedidos:");
		nuncaPedidosLabel.setBounds(210, 5, 150, 25);
		estadisticasPanel.add(nuncaPedidosLabel);
		
		listaNuncaPedidos = new JList();
		listaNuncaPedidos.setBounds(210, 30, 150, 400);
		listaNuncaPedidos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		estadisticasPanel.add(listaNuncaPedidos);
		
		verGraficoBtn = new JButton("Grafico de Pedidos");
		verGraficoBtn.setBounds(380, 30, 275, 30);
		estadisticasPanel.add(verGraficoBtn);
		
		verTablaBtn = new JButton("Tabla de Pedidos");
		verTablaBtn.setBounds(380, 70, 275, 30);
		estadisticasPanel.add(verTablaBtn);
		
		actualizarPedidosBtn = new JButton("Actualizar Pedidos");
		actualizarPedidosBtn.setBounds(380, 110, 275, 30);
		estadisticasPanel.add(actualizarPedidosBtn);
		
		JPanel historialPanel = new JPanel();
		tabbedPane.addTab("Historial Pedidos", null, historialPanel, null);
		historialPanel.setLayout(null);
		
		JLabel lblHistorialDePedidos = new JLabel("Historial de pedidos");
		lblHistorialDePedidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHistorialDePedidos.setBounds(10, 11, 263, 25);
		historialPanel.add(lblHistorialDePedidos);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 47, 263, 12);
		historialPanel.add(separator_1);
		
		historialPedidosList.setBounds(166, 70, 366, 296);
		historialPanel.add(historialPedidosList);
		
		JPanel ActividadesPanel = new JPanel();
		tabbedPane.addTab("Actividades", null, ActividadesPanel, null);
		ActividadesPanel.setLayout(null);
		
		listaActividadesModel = new DefaultListModel<String>();
		
		listaActividades = new JList(listaActividadesModel);
		listaActividades.setBounds(85, 61, 532, 323);
		ActividadesPanel.add(listaActividades);
		
		JLabel lblActividades = new JLabel("Actividades en la aplicacion");
		lblActividades.setBounds(251, 27, 298, 21);
		ActividadesPanel.add(lblActividades);
		
	}	
	
	/**
	 * Obtiene el boton de actualizar pedidos
	 * @return JButton
	 */
	public JButton getActualizarPedidosBtn() {
		return actualizarPedidosBtn;
	}

	/**
	 * Retorna el boton para ver el grafico
	 * @return
	 */
	public JButton getVerGraficoBtn() {
		return verGraficoBtn;
	}

	/**
	 * Retorna el boton para ver la table
	 * @return JButton
	 */
	public JButton getVerTablaBtn() {
		return verTablaBtn;
	}
	
	/**
	 * Retorna la lista de los mas pedidos
	 * @return JList
	 */
	public JList getListaTopPedidos() {
		return listaTopPedidos;
	}
	
	/**
	 * Retorna la lista de nunca pedidos
	 * @return JList
	 */
	public JList getListaNuncaPedidos() {
		return listaNuncaPedidos;
	}

	/**
	 * Obtiene el boton de editar
	 * @return JButton
	 */
	public JButton getEditarBtn() {
		return editarBtn;
	}

	/**
	 * Obtiene el label donde se encuentra el monto express
	 * @return JLabel
	 */
	public JLabel getMontoExpressLabel() {
		return montoExpressLabel;
	}
	
	/**
	 * Obtiene el label donde se encuentra el monto de empaque
	 * @return
	 */
	public JLabel getMontoEmpaqueLabel() {
		return montoEmpaqueLabel;
	}

	/**
	 * Obtiene el text field del nuevo monto
	 * @return JTextField
	 */
	public JTextField getMontoExpressInput() {
		return montoExpressInput;
	}
	
	/**
	 * Obtiene el text field del nuevo monto
	 * @return JTextfield
	 */
	public JTextField getMontoEmpaqueInput() {
		return montoEmpaqueInput;
	}

	/**
	 * Obtiene el boton de actualizar monto express
	 * @return JButton
	 */
	public JButton getMontoExpressBtn() {
		return montoExpressBtn;
	}
	
	/**
	 * Obtiene el boton de actualizar monto empaque
	 * @return JButton
	 */
	public JButton getMontoEmpaqueBtn() {
		return montoEmpaqueBtn;
	}
	
	/**
	 * Obtiene le boton de agregar un nuevo platillo
	 * @return JButton
	 */
	public JButton getAgregarBtn() {
		return agregarBtn;
	}

	/**
	 * Agregar una actividad nueva a la bitacora de conexiones
	 * @param actividad Una actividad de conexion
	 */
	public void agregarABitacoraConexiones(String actividad) {
		this.bitacoraConexionesListModel.addElement(actividad);
	}
	
	/**
	 * Agregar una actividad del pedido al historial de pedidos
	 * @param actividadPedido Una nueva activdad de pedido
	 */
	public void agregarAHistorialDePedidos(String actividadPedido) {
		this.historialPedidosListModel.addElement(actividadPedido);
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
	
	/**
	 * Obtiene la lista donde se guardan las actividades del servidor
	 * @return JList
	 */
	public DefaultListModel getlistaActividades() {
		return this.listaActividadesModel;
	}
}
