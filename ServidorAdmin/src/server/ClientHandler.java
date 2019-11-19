package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    private Socket cliente;
    private BufferedReader in;
    public PrintWriter out;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.cliente = clientSocket;
        in = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));
        out = new PrintWriter(cliente.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Obtener request del cliente
                String request = in.readLine();
                // Revisar si se desea terminar la conexion
                if (request.contains("quit")) {
                    cliente.close();
                    break;
                }
                else {
                    System.out.println("[SERVER] Se recibio: " + request);
                    // Responder al cliente
                    out.println("El servidor recivio: " + request);
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
