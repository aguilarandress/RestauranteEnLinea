package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;


import views.MainView;
import connection.ServerConnection;
import connection.ClientSocket;

/**
 * Controlador principal de la aplicacion
 * @author Andres
 *
 */
public class MainController {
	
	private MainView view;
	private ClientSocket clientSocket;

	public MainController(MainView view) {
		this.view = view;
		try {
			// Crear socket
			this.clientSocket = new ClientSocket();
			// Iniciar server connection
			ServerConnection serverConnection = new ServerConnection(clientSocket.getSocket(), this);
			new Thread(serverConnection).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Configurar eventos
		this.view.addBtnActionListener(new EventoBtn());
		this.view.addWindowBtnCloseEvent(new CloseWindowEvent());
		
		this.view.setVisible(true);
	}
	
	public void addTextPane(String message) {
		this.view.addTextPane(message);
	}
	
	private class EventoBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			clientSocket.writeMessageToServer("send Enviadodesdemisocket");
		}
		
	}
	
	private class CloseWindowEvent implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Cerrando ventana...");
			clientSocket.writeMessageToServer("quit");
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub	
		}
	}
	
}
