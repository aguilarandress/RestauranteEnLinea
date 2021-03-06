package controllers;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import views.MainView;
import connection.ServerConnection;
import models.alimento.Alimento;
import models.alimento.TipoAlimento;
import connection.ClientSocket;
import models.pedidos.*;

/**
 * Controlador principal de la aplicacion
 * @author Andres
 *
 */
public class MainController {
	
	private MainView view;
	private ClientSocket clientSocket;
	private ArrayList<Alimento> alimentos;
	private Alimento alimentoSelected;
	private ArrayList<Alimento> alimentosPedidos;
	public MainController(MainView view) {
		this.view = view;
		try {
			// Crear socket
			this.clientSocket = new ClientSocket();
			// Iniciar server connection
			ServerConnection serverConnection = new ServerConnection(clientSocket.getSocket(), this);
			new Thread(serverConnection).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Configurar eventos
		this.view.addWindowBtnCloseEvent(new CloseWindowEvent());
		this.view.getTabbedPane().addChangeListener(new TabbedPaneChangeListener());
		this.view.getMenuList().addMouseListener(new EventoTreeSelection(this));
		this.view.getMenuList().addTreeSelectionListener(new CargarImagenEvento());
		this.view.getRealizarPedidoVisitaBtn().addActionListener(new RealizarPedidoVisitaListener());
		this.view.getNumeroTelefonoExpressInput().addKeyListener(new EventoSoloNumerosExpress());
		this.view.getTelefonoPedidoRecogerInput().addKeyListener(new EventoSoloNumerosRecoger());
		this.view.getPedidoExpressBtn().addActionListener(new RealizarPedidoExpressListener());
		this.view.getPedidoRecogerBtn().addActionListener(new RealizarPedidoRecogerListener());
		this.view.getBtnEliminar().addActionListener(new EventoEliminarCarrito());
		this.view.setVisible(true);
		this.alimentos = new ArrayList<Alimento>();
		this.alimentosPedidos = new ArrayList<Alimento>();
		blanquearImagen();
		
	}
	
	/**
	 * Evento para unicamente permitir enteros en el numero de telefono en el pedido express
	 * @author Andres Aguilar
	 *
	 */
	private class EventoSoloNumerosExpress extends KeyAdapter {
		
		/**
		 * Evento para solo aceptar numeros
		 */
		public void keyPressed(KeyEvent ke) {
            String value = view.getNumeroTelefonoExpressInput().getText().trim();
            int l = value.length();
            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == 8) {
               view.getNumeroTelefonoExpressInput().setEditable(true);
            } else {
               view.getNumeroTelefonoExpressInput().setEditable(false);
            }
         }
	}
	
	/**
	 * Evento para unicamente permitir enteros en el numero de telefono en el pedido de recoger
	 * @author Andres Aguilar
	 *
	 */
	private class EventoSoloNumerosRecoger extends KeyAdapter {
		/**
		 * Evento para solo aceptar numeros
		 */
		public void keyPressed(KeyEvent ke) {
            String value = view.getTelefonoPedidoRecogerInput().getText().trim();
            int l = value.length();
            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == 8) {
               view.getTelefonoPedidoRecogerInput().setEditable(true);
            } else {
               view.getTelefonoPedidoRecogerInput().setEditable(false);
            }
         }
	}
	
	/**
	 * Evento boton para realizar pedido de visita
	 * @author Andres Aguilar
	 *
	 */
	private class RealizarPedidoVisitaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Revisar si se ingreso el nombre
			if (view.getNombreVisitaInput().getText().isEmpty()) {
				view.displayMessage(false, "Por favor ingrese su nombre");
				return;
			}
			if (alimentosPedidos.isEmpty()) {
				view.displayMessage(false, "Su carrito se encuentra vacio");
				return;
			}
			// Crear pedido
			PedidoFactory pedidoFactory = new PedidoFactory();
			Pedido pedidoNuevo = pedidoFactory.crearPedido(2);
			PedidoSitio pedidoSitio = (PedidoSitio) pedidoNuevo;
			// Configurar datos
			pedidoSitio.setNombrePersona(view.getNombreVisitaInput().getText());
			pedidoSitio.setFecha(new Date());
			pedidoSitio.setNumeroMesa(((int)(Math.random()*((20-1)+1))+1));
			pedidoSitio.setAlimentos(new LinkedList<Alimento>(alimentosPedidos));
			
			view.displayMessage(true, "Pedido enviado");
			view.getNombreVisitaInput().setText("");
			alimentosPedidos.clear();
			// Limpiar carrito
			while(view.getCarritoModel().getRowCount() > 0)
			{
				view.getCarritoModel().removeRow(0);
			}
			
			clientSocket.enviarPedido(pedidoSitio);
		}
		
	}
	
	
	/**
	 * Evento para unicamente permitir enteros en el numero de telefono en el pedido express
	 * @author Andres Aguilar
	 *
	 */
	private class RealizarPedidoExpressListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Revisar que los campos esten llenos
			if (view.getNombrePedidoExpressInput().getText().isEmpty() || view.getNumeroTelefonoExpressInput().getText().isEmpty()|| view.getDireccionPedidoExpressInput().getText().isEmpty()) {
				view.displayMessage(false, "Por favor complete todos los datos");
				return;
			}
			if (alimentosPedidos.isEmpty()) {
				view.displayMessage(false, "Su carrito se encuentra vacio");
				return;
			}
			// Crear pedido
			PedidoFactory pedidoFactory = new PedidoFactory();
			Pedido pedidoNuevo = pedidoFactory.crearPedido(0);
			PedidoExpress pedidoExpress = (PedidoExpress) pedidoNuevo;
			// Configurar datos
			pedidoExpress.setNombrePersona(view.getNombrePedidoExpressInput().getText());
			pedidoExpress.setFecha(new Date());
			pedidoExpress.setNumeroCelular(Integer.parseInt(view.getNumeroTelefonoExpressInput().getText()));
			pedidoExpress.setDireccion(view.getDireccionPedidoExpressInput().getText());
			pedidoExpress.setAlimentos(new LinkedList<Alimento>(alimentosPedidos));
			
			view.displayMessage(true, "Pedido enviado");
			view.getNombrePedidoExpressInput().setText("");
			view.getNumeroTelefonoExpressInput().setText("");
			view.getDireccionPedidoExpressInput().setText("");
			alimentosPedidos.clear();
			// Limpiar carrito
			while(view.getCarritoModel().getRowCount() > 0)
			{
				view.getCarritoModel().removeRow(0);
			}
			
			clientSocket.enviarPedido(pedidoExpress);
		}
		
	}
	
	/**
	 * Evento para unicamente permitir enteros en el numero de telefono en el pedido para recoger
	 * @author Andres Aguilar
	 *
	 */
	private class RealizarPedidoRecogerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Revisar que los campos esten llenos
			if (view.getNombrePedidoRecogerInput().getText().isEmpty() || view.getTelefonoPedidoRecogerInput().getText().isEmpty()) {
				view.displayMessage(false, "Por favor complete todos los datos");
				return;
			}
			// Revisar que haya productos en el carrito
			if (alimentosPedidos.isEmpty()) {
				view.displayMessage(false, "Su carrito se encuentra vacio");
				return;
			}
			// Crear pedido
			PedidoFactory pedidoFactory = new PedidoFactory();
			Pedido pedidoNuevo = pedidoFactory.crearPedido(1);
			PedidoRecoger pedidoRecoger = (PedidoRecoger) pedidoNuevo;
			// Configurar datos
			pedidoRecoger.setNombrePersona(view.getNombrePedidoRecogerInput().getText());
			pedidoRecoger.setFecha(new Date());
			pedidoRecoger.setNumeroCelular(Integer.parseInt(view.getTelefonoPedidoRecogerInput().getText()));
			pedidoRecoger.setAlimentos(new LinkedList<Alimento>(alimentosPedidos));
			
			view.displayMessage(true, "Pedido enviado");
			
			view.getNombrePedidoRecogerInput().setText("");
			view.getTelefonoPedidoRecogerInput().setText("");
			
			alimentosPedidos.clear();
			// Limpiar carrito
			while(view.getCarritoModel().getRowCount() > 0)
			{
				view.getCarritoModel().removeRow(0);
			}
			
			clientSocket.enviarPedido(pedidoRecoger);
		}
		
	}
	
	/**
	 * Evento de tab para cargar el menu
	 * @author Andres Aguilar
	 *
	 */
	private class TabbedPaneChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			// Revisar si es el pane del menu
			JTabbedPane pane = (JTabbedPane) e.getSource();
			if (pane.getSelectedIndex() == 1) {
				clientSocket.writeMessageToServer("alimentos");
			}
		}
		
	}
	/**
	 * Evento para abrir ventana del alimento seleccionado
	 * @author fabia
	 *
	 */
	private class EventoTreeSelection implements MouseListener{
		MainController controller;
		public EventoTreeSelection(MainController controller) {
			this.controller = controller;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2 ) {
				if(view.getMenuList().getSelectionPath() == null) {
					blanquearImagen();
					return;
				}
				for(Alimento alimento : alimentos) {
					if(view.getMenuList().getSelectionPath().getLastPathComponent().toString() == alimento.getNombre()) {
						alimentoSelected = alimento;
						this.ventanaPlatillo();
					}
				}

			}
		}
		
		public void ventanaPlatillo() {
			AbrirPlatilloController abrirController= new AbrirPlatilloController(controller);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub		
		}	
	}
	
	/**
	 * Despliega el costo total en la interfaz
	 * @param costo Un String con el costo del pedido realizado
	 */
	public void desplegarCosto(String costo) {
		this.view.displayMessage(true, "El costo de su pedido es de " + costo);
	}
	
	/**
	 * Colocar los alimentos en el JTree
	 * @param alimentos
	 */
	public void setAlimentosMenu (ArrayList<Alimento> alimentos) {
		this.view.getMenuList().setModel(null);
		
		DefaultMutableTreeNode catalogo = new DefaultMutableTreeNode("Catalogo");
		DefaultMutableTreeNode entradas = new DefaultMutableTreeNode("Entradas");
		DefaultMutableTreeNode platosF = new DefaultMutableTreeNode("Platos Fuertes");
		DefaultMutableTreeNode bebidas = new DefaultMutableTreeNode("Bebidas");
		DefaultMutableTreeNode postres = new DefaultMutableTreeNode("Postres");
		for (Alimento alimentoActual : alimentos) {
			DefaultMutableTreeNode nombre = new DefaultMutableTreeNode(alimentoActual.getNombre());
			if(alimentoActual.getTipo() == TipoAlimento.BEBIDA) {
				bebidas.add(nombre);
			}
			else if(alimentoActual.getTipo() == TipoAlimento.PLATO_FUERTE) {
				platosF.add(nombre);
			}
			else if(alimentoActual.getTipo() == TipoAlimento.ENTRADA) {
				entradas.add(nombre);
			}
			else {
				postres.add(nombre);
			}
		}
		catalogo.add(entradas);
		catalogo.add(platosF);
		catalogo.add(bebidas);
		catalogo.add(postres);
		DefaultTreeModel modelo = new DefaultTreeModel(catalogo);
		view.getMenuList().setModel(modelo);
		this.alimentos = alimentos;
	}
	
	/**
	 * Evento para cuando la ventana se cierra enviar al servidor una se�al
	 * @author Andres Aguilar
	 *
	 */
	private class CloseWindowEvent implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Cerrando ventana...");
			clientSocket.writeMessageToServer("quit");
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub	
		}
	}
	/**
	 * Obtener el alimento seleccionado
	 * @return
	 */
	public Alimento getAlimentoSelected() {
		return this.alimentoSelected;
	}
	
	/**
	 * Evento para cargar una imagen
	 * @author Fabi�n Vargas
	 *
	 */
	private class CargarImagenEvento implements TreeSelectionListener{

		@Override
		public void valueChanged(TreeSelectionEvent arg0) {
			if(view.getMenuList().getSelectionPath() == null) {
				blanquearImagen();
				return;
			}
			for(Alimento alimento : alimentos) {
				if(view.getMenuList().getSelectionPath().getLastPathComponent().toString() == alimento.getNombre()) {
					if(verificarImagen(alimento.getImagenPath()))
						cargarImagen(alimento);
				}
			}
		}
		
	}
	
	/**
	 * Verifica que la imagen exista
	 * @param path Path a verificar
	 * @return boolean
	 */
	public boolean verificarImagen(String path) {
        String filepath = path;
        try {
            BufferedImage image = ImageIO.read(new File(filepath));
            if (image == null) {
                return false;
            }
            return true;
        } catch(IOException ex) {
            return false;
       }
	}
	
	/**
	 * Carga la imagen del alimento seleccionado
	 * @param alimento
	 */
	public void cargarImagen(Alimento alimento) {
		view.getAreaDescrip().setEditable(true);
        ImageIcon imageIcon = new ImageIcon(alimento.getImagenPath());
        Image copiaImage = imageIcon.getImage();
        Image resizedImage = copiaImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(resizedImage);
        view.getImagenLabel().setIcon(imageIcon);
        System.out.println(alimento.getDescripcion());
        view.getAreaDescrip().setText(alimento.getDescripcion());
        view.getAreaDescrip().setEditable(false);
	}
	
	/**
	 * Blanquea la imagen
	 */
	public void blanquearImagen() {
		view.getAreaDescrip().setEditable(true);
		if(verificarImagen("../Imagenes/menu.jpg")) {
	        ImageIcon imageIcon = new ImageIcon("../Imagenes/menu.jpg");
	        Image copiaImage = imageIcon.getImage();
	        Image resizedImage = copiaImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
	        imageIcon = new ImageIcon(resizedImage);
	        view.getImagenLabel().setIcon(imageIcon);
	        view.getAreaDescrip().setText("");
	        view.getAreaDescrip().setEditable(false);
		}
	}
	
	public ArrayList<Alimento> getAlimentosPedidos(){
		return this.alimentosPedidos;
	}
	
	/**
	 * Agregar al carrito las compras
	 * @param nombre Nombre del producto
	 * @param cantidad Cantidad pedida
	 */
	public void agregarTablaCarrito(String nombre, String cantidad) {
		String[] parametro = {nombre,cantidad};
		for(int i = 0; i<view.getCarritoModel().getRowCount();i++) {
			if(nombre.equals(view.getCarritoModel().getValueAt(i, 0))){
				String cantidadActual = (String) view.getCarritoModel().getValueAt(i, 1);
				cantidad = String.valueOf(Integer.parseInt(cantidad) + Integer.parseInt(cantidadActual));
				view.getCarritoModel().setValueAt(cantidad, i, 1);
				return;
			}
		}
		view.getCarritoModel().addRow(parametro);
	}
	
	/**
	 * Evento boton para eliminar producto del carrito
	 * @author Fabi�n Vargas
	 *
	 */
	private class EventoEliminarCarrito implements ActionListener {

		@Override	
		public void actionPerformed(ActionEvent e) {
	        if (view.getTableCarrito().getSelectedRow() > -1) {
	        	String cantidad = (String) view.getTableCarrito().getValueAt(view.getTableCarrito().getSelectedRow(), 1);
	        	String nombreAlimento = (String) view.getTableCarrito().getValueAt(view.getTableCarrito().getSelectedRow(), 0);
	        	int cantidadEntera = Integer.parseInt(cantidad);
	        	if(cantidadEntera == 1) {
	        		view.getCarritoModel().removeRow(view.getTableCarrito().getSelectedRow());

	        	}
	        	else {
	        		int cantidadNueva = Integer.parseInt(cantidad) -1;
	        		view.getTableCarrito().setValueAt(String.valueOf(cantidadNueva), view.getTableCarrito().getSelectedRow(), 1);
	        	}
        		for(int i = 0; i< alimentosPedidos.size(); i++) {
        			Alimento alimentoBorrar = alimentosPedidos.get(i);
        			if(alimentoBorrar.getNombre().equals(nombreAlimento)) {
        				alimentosPedidos.remove(i);
        				return;
        			}
        		}
	        }
			
		}
		
	}
}
