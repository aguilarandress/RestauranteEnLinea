package connection;

import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;

/**
 * Clase para controlar la comunicacion de cliente --> servidor
 * @author Andres
 */
public class ClientSocket {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 3000;
    private Socket socket;
    private PrintWriter out;

    public ClientSocket() throws IOException {
        this.socket = new Socket(SERVER_IP, SERVER_PORT);
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
    }

    /**
     * Escribe un mensaje al servidor
     * @param message El mensaje tipo String que se desea enviar al servidor
     */
    public void writeMessageToServer(String message) {
        this.out.println(message.getBytes());
    }

    /**
     * Obtiene el flujo de comunicacion del cliente
     * @return Outputstream del socket
     */
    public PrintWriter getOutputStream() {
        return this.out;
    }

    /**
     * Obtiene el socket que se comunca con el servidor
     * @return El socket utilizado para comunicacion
     */
    public Socket getSocket() {
        return this.socket;
    }
}
