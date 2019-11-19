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

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnBoton = new JButton("Boton1");
	
	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBoton.setBounds(214, 98, 89, 23);
		contentPane.add(btnBoton);
		
		textField = new JTextField();
		textField.setBounds(50, 99, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	
	public void addBtnActionListener(ActionListener listenForBtn) {
		this.btnBoton.addActionListener(listenForBtn);
	}
	
	public void setTextField(String message) {
		this.textField.setText(message);
	}
}
