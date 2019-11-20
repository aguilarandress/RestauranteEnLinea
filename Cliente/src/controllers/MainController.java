package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import views.MainView;
import connection.ServerConnection;
import connection.ClientSocket;

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
	
}
