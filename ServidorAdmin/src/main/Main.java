package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import server.TCPServer;
import controllers.MainController;
import views.MainView;
import models.catalogo.Catalogo;

public class Main {

	public static void main(String[] args) {

    try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Inicializar MVC
		MainView view = new MainView();
		Catalogo catalogo = new Catalogo();
		MainController controller = new MainController(view, catalogo);
	}
}