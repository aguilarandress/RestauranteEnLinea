package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private BufferedReader in;
    private TCPServer server;
    public PrintWriter out;

    public ClientHandler(Socket clientSocket, TCPServer server) throws IOException {
        this.cliente = clientSocket;
        this.server = server;
        // Crear streams de input y output
        in = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));
        out = new PrintWriter(cliente.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Obtener request del cliente
                String request = in.readLine();
                // Obtener tokens
                String[] message = request.split(" ");
                // Revisar si se desea terminar la conexion
                if (message[0].equals("quit")) {
                    cliente.close();
                    break;
                }
                else if (message[0].equals("send")) {
                	this.server.sendToController(message[1]);
                	this.out.println("client HelloWorld");
                }
                else {
                    System.out.println("[SERVER] Se recibio: " + request);
                    // Responder al cliente
                    out.println("El servidor recibio: " + request);
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
