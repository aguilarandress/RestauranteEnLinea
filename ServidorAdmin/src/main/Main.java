package main;

import server.TCPServer;

public class Main {

	public static void main(String[] args) {
		// Inicializar servidor
		TCPServer mainServer = new TCPServer();
		new Thread(mainServer).start();
	}

}
