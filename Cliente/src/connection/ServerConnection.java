package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import controllers.MainController;

/**
 * Clase que mantiene la comunicacion del servidor al cliente
 * @author Andres
 *
 */
public class ServerConnection implements Runnable {

	private Socket server;
	private BufferedReader in;
	private MainController controller;

	public ServerConnection(Socket s, MainController controller) {
		try {
			this.server = s;
			this.in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			this.controller = controller;
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				// Obtener respuesta del servidor
				String serverResponse = in.readLine();
				if (serverResponse == null)
					break;
				System.out.println("Servidor respondio: " + serverResponse);
				// Obtener tokens
				String[] tokens = serverResponse.split(" ");
				if (tokens[0].equals("client")) {
					// this.controller.addTextPane(tokens[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
