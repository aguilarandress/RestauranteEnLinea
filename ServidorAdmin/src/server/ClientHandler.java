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
            		else if (inputRecibido.equals("alimentos")) {
            			
            		}
            	}
            	
            	
                // Obtener request del cliente
//                String request = in.readLine();
//                if (request == null) break;
//                // Obtener tokens
//                String[] message = request.split(" ");
//                // Revisar si se desea terminar la conexion
//                if (request.equals("quit")) {
//                	// Desconectar cliente del servidor
//                	this.server.getController().agregarActividadDeConexion("[SERVER] Cliente desconectado...");
//                    cliente.close();
//                    break;
//                }
//                else if (message[0].equals("send")) {
//                	this.server.sendToController(message[1]);
//                	this.out.println("client HelloWorld");
//                }
//                else {
//                    System.out.println("[SERVER] Se recibio: " + request);
//                    // Responder al cliente
//                    out.println("El servidor recibio: " + request);
//                }
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
}
