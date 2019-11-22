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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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
	private JTree catalogoTree;
	
	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("Aplicacion Administradora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane.setBounds(0, 0, 664, 450);
		contentPane.add(tabbedPane);
		
		tabbedPane.addTab("Conexiones", null, conexionesPanel, null);
		conexionesPanel.setLayout(null);
		
		bitacoraConexionesTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		bitacoraConexionesTitle.setBounds(10, 11, 209, 24);
		conexionesPanel.add(bitacoraConexionesTitle);
		
		separator.setBounds(10, 46, 209, 2);
		conexionesPanel.add(separator);
		
		bitacoraConexionesList.setBounds(100, 59, 500, 300);
		conexionesPanel.add(bitacoraConexionesList);
		
		catalogoTree = new JTree();
		catalogoTree.setBounds(0, 0, 500, 450);
		catalogoTree.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		// catalogoTree.setModel(this.CrearCatalogo());
		
		JScrollPane scrollPane = new JScrollPane(catalogoTree);
		scrollPane.setViewportView(catalogoTree);
		scrollPane.setBounds(0, 0, 650, 400);
		scrollPane.setBorder(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.add(scrollPane);
		panel_1.setLayout(null);
		tabbedPane.addTab("Menu", null, panel_1, null);
	}
	
	/**
	 * Agregar una actividad nueva a la bitacora de conexiones
	 * @param actividad
	 */
	public void agregarABitacoraConexiones(String actividad) {
		this.bitacoraConexionesListModel.addElement(actividad);
	}
	
	/**
	 * Cargar los alimentos a la vista
	 * @param alimentos Los alimentos que se van a cargar
	 */
	public void crearCatalogo(Cola<Alimento> alimentos) {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Catalogo");
		
		DefaultMutableTreeNode entradas = new DefaultMutableTreeNode("Entradas");
		
		DefaultMutableTreeNode platosF = new DefaultMutableTreeNode("Platos Fuertes");
		
		DefaultMutableTreeNode bebidas = new DefaultMutableTreeNode("Bebidas");
		
		DefaultMutableTreeNode postres = new DefaultMutableTreeNode("Postres");
		for(int posActual = 0; posActual < alimentos.getCantidad(); posActual++) {
			Alimento actual = alimentos.get(posActual);
			
			// Nombre de la comida
			DefaultMutableTreeNode nombre = new DefaultMutableTreeNode(actual.getNombre());
			
			// Codigo de la comida
			DefaultMutableTreeNode codigoNode = new DefaultMutableTreeNode("Codigo");
			DefaultMutableTreeNode codigo = new DefaultMutableTreeNode(actual.getCodigo());
			codigoNode.add(codigo);
			nombre.add(codigoNode);
			
			// Descripcion de la comida
			DefaultMutableTreeNode descripcionNode = new DefaultMutableTreeNode("Descripcion");
			DefaultMutableTreeNode descripcion = new DefaultMutableTreeNode(actual.getDescripcion());
			descripcionNode.add(descripcion);
			nombre.add(descripcionNode);
			
			// Calorias de la comida		
			DefaultMutableTreeNode caloriasNode = new DefaultMutableTreeNode("Calorias");
			DefaultMutableTreeNode calorias = new DefaultMutableTreeNode(Float.toString(
					actual.getCalorias()));
			caloriasNode.add(calorias);
			nombre.add(caloriasNode);
			
			// Precio de la comida
			DefaultMutableTreeNode precioNode = new DefaultMutableTreeNode("Precio");
			DefaultMutableTreeNode precio = new DefaultMutableTreeNode(Float.toString(
					actual.getPrecio()));
			precioNode.add(precio);
			nombre.add(precioNode);
			
			// Agregar segun corresponda
			if(actual.getTipo().equals(TipoAlimento.ENTRADA)) {
				entradas.add(nombre);
			}
			else if(actual.getTipo().equals(TipoAlimento.PLATO_FUERTE)) {
				platosF.add(nombre);
			}
			else if(actual.getTipo().equals(TipoAlimento.BEBIDA)) {
				bebidas.add(nombre);
			}
			else {
				postres.add(nombre);
			}
		}
		
		raiz.add(entradas);
		raiz.add(platosF);
		raiz.add(bebidas);
		raiz.add(postres);
		DefaultTreeModel modelo = new DefaultTreeModel(raiz);
		this.catalogoTree.setModel(modelo);
	}
}
