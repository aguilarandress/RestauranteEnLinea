package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnPresioname = new JButton("Presioname");
	private JTextPane textPane = new JTextPane();
	
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
		
		textField = new JTextField();
		textField.setBounds(90, 145, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnPresioname.setBounds(280, 144, 89, 23);
		contentPane.add(btnPresioname);
		
		
		textPane.setBounds(90, 200, 143, 86);
		contentPane.add(textPane);
	}
	
	public void addBtnActionListener(ActionListener listenerForBtn) {
		this.btnPresioname.addActionListener(listenerForBtn);
	}
	
	public void addTextPane(String text) {
		this.textPane.setText(this.textPane.getText() + text);
	}
}
