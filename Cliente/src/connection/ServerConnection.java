package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import controllers.MainController;
import models.alimento.Alimento;

/**
 * Clase que mantiene la comunicacion del servidor al cliente
 * @author Andres
 *
 */
public class ServerConnection implements Runnable {

	private Socket server;
	private ObjectInputStream objectInputStream;
	private MainController controller;

	public ServerConnection(Socket s, MainController controller) {
		try {
			this.server = s;
			this.objectInputStream = new ObjectInputStream(this.server.getInputStream());
			this.controller = controller;
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				Object inputRecibido = (Object) this.objectInputStream.readUnshared();
				if (inputRecibido instanceof String) {
					// Obtener tokens
					String mensaje = (String) inputRecibido;
					String tokens[] = mensaje.split(" ");
					if (tokens[0].equals("costo")) {
						this.controller.desplegarCosto(tokens[1]);
					}
				}
				else {
					System.out.println("Alimentos recibidos...");
					ArrayList<Alimento> alimentos = (ArrayList<Alimento>) inputRecibido;
					this.controller.setAlimentosMenu(alimentos);
				}
			}
		} catch (IOException e) {
			System.out.println("Error with socket...");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error de casting...");
			e.printStackTrace();
		} finally {
			// Cerrar streams
			try {
				this.objectInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
