package connection;

import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectOutputStream;

import models.pedidos.Pedido;

/**
 * Clase para controlar la comunicacion de cliente --> servidor
 * @author Andres
 */
public class ClientSocket {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5000;
    private Socket socket;
    private ObjectOutputStream out;

    public ClientSocket() throws IOException {
        this.socket = new Socket(SERVER_IP, SERVER_PORT);
        this.out = new ObjectOutputStream(this.socket.getOutputStream());
    }

    /**
     * Escribe un mensaje al servidor
     * @param message El mensaje tipo String que se desea enviar al servidor
     */
    public void writeMessageToServer(String message) {
        try {
        	this.out.reset();
			this.out.writeUnshared(message);
			this.out.flush();
		} catch (IOException e) {
			System.out.println("**ERROR**: Write Object");
			e.printStackTrace();
		}
    }
    
    public void enviarPedido(Pedido pedidoAEnviar) {
    	 try {
         	this.out.reset();
 			this.out.writeUnshared(pedidoAEnviar);
 			this.out.flush();
 		} catch (IOException e) {
 			System.out.println("**ERROR**: Write Pedido");
 			e.printStackTrace();
 		}
    }

    /**
     * Obtiene el socket que se comunca con el servidor
     * @return El socket utilizado para comunicacion
     */
    public Socket getSocket() {
        return this.socket;
    }
}
