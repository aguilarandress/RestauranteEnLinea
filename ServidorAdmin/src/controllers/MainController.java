package controllers;

import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import server.TCPServer;

public class MainController {
	
	private MainView view;
	private TCPServer mainServer;
	
	public MainController(MainView view) {
		this.view = view;
		
		this.mainServer = new TCPServer(this);
		mainServer.start();
		
		// Event listeners
		// view.addBtnActionListener(new EventoBtn());
		
		view.setVisible(true);
	}
	
	// public void setTextField(String message) { this.view.setTextField(message); }
	
	private class EventoBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainServer.writeToAll("Hello clients...");
		}
		
	}
	
}
