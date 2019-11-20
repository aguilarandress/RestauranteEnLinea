package main;

import connection.ServerConnection;
import controllers.MainController;
import views.MainView;

public class Main {

	public static void main(String[] args) {
		MainView view = new MainView();
		MainController controller = new MainController(view);
	}
}
