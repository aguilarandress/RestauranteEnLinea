package main;

import server.TCPServer;
import controllers.MainController;
import views.MainView;

public class Main {

	public static void main(String[] args) {
		// Inicializar servidor
		MainView view = new MainView();
		
		MainController controller = new MainController(view);
	}

}
