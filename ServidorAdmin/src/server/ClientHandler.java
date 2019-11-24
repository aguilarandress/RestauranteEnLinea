package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import controllers.MainController;
import models.alimento.Alimento;
import models.cola.*;

/**
 * Clase que representa una conexion a un cliente
 * @author Andres
 *
 */
public class ClientHandler implements Runnable {

    private Socket cliente;
    private ObjectInputStream inputObject;
    private ObjectOutputStream outputObject;
    private MainController controller;
    private TCPServer server;
    public PrintWriter out;

    public ClientHandler(Socket clientSocket, MainController controller, TCPServer server) throws IOException {
        this.cliente = clientSocket;
        this.controller = controller;
        this.server = server;
        // Crear streams de input y output
        this.inputObject = new ObjectInputStream(this.cliente.getInputStream());
        this.outputObject = new ObjectOutputStream(this.cliente.getOutputStream());
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
            			System.out.println("Alimentos solicitados...");
            			Cola<Alimento> colaAlimentos = this.controller.getCatalogo().getAlimentos();
            			this.enviarAlimentos(colaAlimentos);
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
                this.inputObject.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void enviarAlimentos(Cola<Alimento> colaAlimentos) {
    	// Obtener elemento de la cola 
    	ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
    	while (!colaAlimentos.isEmpty()) {
    		Alimento alimentoActual = colaAlimentos.dequeue();
    		alimentoActual.cargarContenidoImagen();
    		alimentos.add(alimentoActual);
    	}
    	// Volver a encolar los alimentos
    	for (Alimento alimentoActual : alimentos) {
    		colaAlimentos.enqueue(alimentoActual);
    	}
    	try {
			this.outputObject.writeObject(alimentos);
			this.outputObject.flush();
		} catch (IOException e) {
			System.out.println("**ERROR** Al enviar objeto");
			e.printStackTrace();
		}
    }
}
