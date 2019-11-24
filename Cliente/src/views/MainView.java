package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTable;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTree menuTree;
	private JLabel imagenLabel;
	private JTextArea areaDescrip;
	private JTable carrito;
	private DefaultTableModel modeloTabla;
	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("Aplicacion Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabbedPane.setBounds(0, 0, 798, 500);
		contentPane.add(tabbedPane);
		
		JPanel bienvenidaPanel = new JPanel();
		tabbedPane.addTab("Inicio", null, bienvenidaPanel, null);
		bienvenidaPanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("\u00A1Bienvenido a nuesto restaurante!");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		titleLabel.setBounds(180, 74, 551, 49);
		bienvenidaPanel.add(titleLabel);
		
		JPanel menuPanel = new JPanel();
		tabbedPane.addTab("Menu", null, menuPanel, null);
		menuPanel.setLayout(null);
		
		menuTree = new JTree();
		menuTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Catalogo") {
				{
				}
			}
		));
		menuTree.setBounds(31, 29, 339, 416);
		menuPanel.add(menuTree);
		
		JScrollPane descripPane = new JScrollPane();
		menuPanel.add(descripPane);
		
		imagenLabel = new JLabel();
		menuPanel.add(imagenLabel);
		imagenLabel.setBounds(443, 13, 300, 300);
		areaDescrip = new JTextArea();
		descripPane.setColumnHeaderView(areaDescrip);
		descripPane.setBounds(426, 352, 334, 105);
		areaDescrip.setLineWrap(true);
		areaDescrip.setWrapStyleWord(true);
		areaDescrip.setEditable(false);
		
		JPanel panelCarrito = new JPanel();
		panelCarrito.setLayout(null);
		tabbedPane.addTab("Carrito",null, panelCarrito,null);
		carrito = new JTable();
		carrito.setBounds(162, 54, 500, 403);
		panelCarrito.add(carrito);
		modeloTabla = new DefaultTableModel();
		
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Cantidad");
		carrito.setModel(modeloTabla);
		
		JLabel lblNombrePlatillo = new JLabel("Nombre Platillo:");
		lblNombrePlatillo.setBounds(216, 25, 117, 16);
		panelCarrito.add(lblNombrePlatillo);
		
		JLabel lblCantidadSeleccionada = new JLabel("Cantidad Seleccionada:");
		lblCantidadSeleccionada.setBounds(453, 25, 134, 16);
		panelCarrito.add(lblCantidadSeleccionada);
	}
	
	/**
	 * Agregar evento para cuando la aplicacion se cierra
	 * @param windowListener El event listener 
	 */
	public void addWindowBtnCloseEvent(WindowListener windowListener) {
		this.addWindowListener(windowListener);
	}
	
	public JTree getMenuList() {
		return menuTree;
	}

	public void setMenuList(JTree menuTree) {
		this.menuTree = menuTree;
	}
	
	public JTabbedPane getTabbedPane() {
		return this.tabbedPane;
	}

	public JLabel getImagenLabel() {
		return imagenLabel;
	}

	public void setImagenLabel(JLabel imagenLabel) {
		this.imagenLabel = imagenLabel;
	}

	public JTextArea getAreaDescrip() {
		return areaDescrip;
	}

	public void setAreaDescrip(JTextArea areaDescrip) {
		this.areaDescrip = areaDescrip;
	}
	
	public DefaultTableModel getCarritoModel() {
		return this.modeloTabla;
	}
}
