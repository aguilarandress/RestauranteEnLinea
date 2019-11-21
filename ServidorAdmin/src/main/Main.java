package main;

import server.TCPServer;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controllers.MainController;
import views.MainView;

public class Main {

	public static void main(String[] args) {

    try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		// Inicializar servidor
		MainView view = new MainView();
		MainController controller = new MainController(view);
	}
}