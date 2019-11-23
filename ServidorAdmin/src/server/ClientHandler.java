package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase que representa una conexion a un cliente
 * @author Andres
 *
 */
public class ClientHandler implements Runnable {

    private Socket cliente;
    private ObjectInputStream inputObject;
    private TCPServer server;
    public PrintWriter out;

    public ClientHandler(Socket clientSocket, TCPServer server) throws IOException {
        this.cliente = clientSocket;
        this.server = server;
        // Crear streams de input y output
        this.inputObject = new ObjectInputStream(this.cliente.getInputStream());
        out = new PrintWriter(cliente.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
            	Object inputRecibido = (Object) this.inputObject.readObject();
            	// Es un mensaje tipo string
            	if (inputRecibido instanceof String) {
            		// El cliente desea salir
            		if (inputRecibido.equals("quit")) {
            			this.server.getController().agregarActividadDeConexion("[SERVER] Cliente desconectado...");
            			cliente.close();
            			break;
            		}
            		// Enviar alimentos
            		else if (inputRecibido.equals("alimentos")) {
            			this.enviarAlimentos();
            		}
            	}
            }
        } catch (IOException e) {
            e.getStackTrace();
        } catch (ClassNotFoundException e) {
			System.out.println("No se encontro la clase...");
			e.printStackTrace();
		} finally {
            out.close();
            try {
                //in.close();
                this.inputObject.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void enviarAlimentos() {
    	
    }
}
