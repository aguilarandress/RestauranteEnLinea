package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel menuPanel;
	private JPanel crearPedidoPanel;
	
	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("Aplicacion Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 629, 355);
		contentPane.add(tabbedPane);
		
		menuPanel = new JPanel();
		tabbedPane.addTab("Menu", null, menuPanel, null);
		
		crearPedidoPanel = new JPanel();
		tabbedPane.addTab("Crear Pedido", null, crearPedidoPanel, null);
	}
	
	/**
	 * Agregar evento para cuando la aplicacion se cierra
	 * @param windowListener El event listener 
	 */
	public void addWindowBtnCloseEvent(WindowListener windowListener) {
		this.addWindowListener(windowListener);
	}
	
//	public void addBtnActionListener(ActionListener listenerForBtn) {
//		this.btnPresioname.addActionListener(listenerForBtn);
//	}
//	
//	public void addTextPane(String text) {
//		this.textPane.setText(this.textPane.getText() + text);
//	}
}
