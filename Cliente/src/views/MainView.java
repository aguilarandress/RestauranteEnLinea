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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("Aplicacion Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabbedPane.setBounds(0, 0, 772, 450);
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
		
		JList menuList = new JList();
		menuList.setBounds(27, 26, 279, 360);
		menuPanel.add(menuList);
	}
	
	/**
	 * Agregar evento para cuando la aplicacion se cierra
	 * @param windowListener El event listener 
	 */
	public void addWindowBtnCloseEvent(WindowListener windowListener) {
		this.addWindowListener(windowListener);
	}
	
	public JTabbedPane getTabbedPane() {
		return this.tabbedPane;
	}
}
