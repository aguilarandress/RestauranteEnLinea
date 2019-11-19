package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer implements Runnable {

    private final int PORT = 9090;
    private ExecutorService pool = Executors.newFixedThreadPool(15);
    private ArrayList<ClientHandler> clients = new ArrayList<>();

    @Override
    public void run() {
        try {
            // Iniciar servidor
            ServerSocket listener = new ServerSocket(this.PORT);
            System.out.println("Servidor iniciado en el puerto: " + this.PORT);
            // Esperar conexiones
            while (true) {
                System.out.println("[SERVER] Esperando conexiones...");
                // Crear conexion
                Socket cliente = listener.accept();
                System.out.println("[SERVER] Un cliente se ha conectado");
                // Spawnear un client handler para la conexion
                ClientHandler clientHandler = new ClientHandler(cliente);
                clients.add(clientHandler);
                // Agregar al thread pool
                pool.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
