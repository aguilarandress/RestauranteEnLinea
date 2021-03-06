package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Locale; 

import controllers.MainController;
import models.alimento.Alimento;
import models.cola.*;
import models.pedidos.*;

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
            	Object inputRecibido = (Object) this.inputObject.readUnshared();
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
            			Cola<Alimento> colaAlimentos = this.controller.getCatalogo().getAlimentos();
            			this.enviarAlimentos(colaAlimentos);
            		}
            	}
            	if (inputRecibido instanceof Pedido) {
            		Pedido pedidoNuevo = (Pedido) inputRecibido;
            		// Agregar al menu
            		this.controller.getPedidos().agregarPedido(pedidoNuevo);
            		// Obtener el tipo de pedido
            		String tipoPedido = "";
            		if (pedidoNuevo instanceof PedidoExpress) tipoPedido += "pedido express";
            		if (pedidoNuevo instanceof PedidoSitio) tipoPedido += "pedido en sitio";
            		if (pedidoNuevo instanceof PedidoRecoger) tipoPedido += "pedido para recoger";
            		// Agregar fecha
            		String actividadNueva = pedidoNuevo.getNombrePersona() + " solicito un " + tipoPedido;
            		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            		String fecha = formatter.format(pedidoNuevo.getFecha());
            		actividadNueva += " a las " + fecha;
            		this.controller.agregarActividadPedido(actividadNueva);
            		
            		// TODO: Arreglar costo (Montos y cantidad de alimentos)
            		float costoTotal = 0;
            		for (Alimento unAlimento : pedidoNuevo.getAlimentos()) {
            			costoTotal += unAlimento.getPrecio();
            		}
            		// Agregar monto dependiendo del tipo de pedido
            		if (pedidoNuevo instanceof PedidoExpress) {
            			costoTotal += this.controller.getCatalogo().getMontoExpress();
            		}
            		else if (pedidoNuevo instanceof PedidoRecoger) {
            			costoTotal += this.controller.getCatalogo().getMontoEmpaque();
            		}
            		
            		try {
                		this.outputObject.reset();
            			this.outputObject.writeUnshared("costo " + String.valueOf(costoTotal));
            			this.outputObject.flush();
            		} catch (IOException e) {
            			System.out.println("**ERROR** Al enviar el costo");
            			e.printStackTrace();
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
    
    /**
     * Envia los alimentos al socket recientemente conectado
     * @param colaAlimentos La cola de prioridad con los alimentos
     */
    public void enviarAlimentos(Cola<Alimento> colaAlimentos) {
    	// Obtener elemento de la cola 
    	ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
    	while (!colaAlimentos.isEmpty()) {
    		Alimento alimentoActual = colaAlimentos.dequeue();
    		if (alimentoActual.getHabilitado()) {
    			System.out.println(alimentoActual.getNombre());
        		alimentoActual.cargarContenidoImagen();
        		alimentos.add(alimentoActual);
    		}
    	}
    	// Volver a encolar los alimentos
    	for (Alimento alimentoActual : alimentos) {
    		colaAlimentos.enqueue(alimentoActual);
    	}
    	try {
    		this.outputObject.reset();
			this.outputObject.writeUnshared(alimentos);
			this.outputObject.flush();
		} catch (IOException e) {
			System.out.println("**ERROR** Al enviar objeto");
			e.printStackTrace();
		}
    }
}
