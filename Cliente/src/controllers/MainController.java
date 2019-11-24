package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import views.MainView;
import connection.ServerConnection;
import models.alimento.Alimento;
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
		this.view.addWindowBtnCloseEvent(new CloseWindowEvent());
		this.view.getTabbedPane().addChangeListener(new TabbedPaneChangeListener());
		
		this.view.setVisible(true);
	}
	
	private class TabbedPaneChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			// Revisar si es el pane del menu
			JTabbedPane pane = (JTabbedPane) e.getSource();
			if (pane.getSelectedIndex() == 1) {
				clientSocket.writeMessageToServer("alimentos");
			}
		}
		
	}
	
	public void setAlimentosMenu (ArrayList<Alimento> alimentos) {
		this.view.getAlimentosListModel().clear();
		for (Alimento alimentoActual : alimentos) {
			this.view.getAlimentosListModel().addElement(alimentoActual);
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
