package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controllers.MainController;
import models.cola.Cola;
import models.alimento.Alimento;

/**
 * Clase contenedora del servidor TCP
 * @author Andres
 *
 */
public class TCPServer extends Thread {

    private final int PORT = 5000;
    private ServerSocket listener;
    private ExecutorService pool = Executors.newFixedThreadPool(15);
    private ArrayList<ClientHandler> clients;
    private MainController mainController;
    
    public TCPServer(MainController controller) {
    	this.mainController = controller;
    	this.clients = new ArrayList<ClientHandler>();
    }
    
    @Override
    public void run() {
        try {
            // Iniciar servidor
            this.listener = new ServerSocket(this.PORT);
            System.out.println("Servidor iniciado en el puerto: " + this.PORT);
            // Esperar conexiones
            while (true) {
                System.out.println("[SERVER] Esperando conexiones...");
                // Crear conexion
                Socket cliente = this.listener.accept();
                System.out.println("[SERVER] Un cliente se ha conectado");
                this.mainController.agregarActividadDeConexion("[SERVER] Un cliente se ha conectado");
                // Spawnear un client handler para la conexion
                ClientHandler clientHandler = new ClientHandler(cliente, this.mainController, this);
                clients.add(clientHandler);
                // Agregar al thread pool
                pool.execute(clientHandler);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Funcion para escribir a todos los sockets conectados
     * @param message Un string que contiene el mensaje que se desea enviar
     */
    public void writeToAll(String message) {
    	for (ClientHandler aClient : clients) {
            aClient.out.println(message);
        }
    }
    
    /**
     * Le envia a todos los sockets la lista nueva de alimentos
     */
    public void actualizarAlimentos() {
    	for (ClientHandler aClient : clients) {
    		aClient.enviarAlimentos(this.mainController.getCatalogo().getAlimentos());
    	}
    }
    
    /**
     * Cierra el servidor y todas sus conexionex
     */
    public void closeServer() {
    	try {
			this.listener.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public MainController getController() {
    	return this.mainController;
    }
    
    public void sendToController(String message) {
    	// this.mainController.setTextField(message);
    }
}
