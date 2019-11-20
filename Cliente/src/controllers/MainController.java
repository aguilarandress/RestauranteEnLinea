package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import views.MainView;
import connection.ServerConnection;


public class MainController {
	
	private MainView view;
	
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 3000;
	private Socket socket;
	
	private PrintWriter out;
	
	public MainController(MainView view) {
		this.view = view;
		try {
			this.socket = new Socket(SERVER_IP, SERVER_PORT);
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			// Iniciar server connection
			ServerConnection serverConnection = new ServerConnection(this.socket, this);
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
			out.println("send Enviadodesdemisocket");
		}
		
	}
	
}
