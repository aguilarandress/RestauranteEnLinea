package controllers;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import views.MainView;
import connection.ServerConnection;
import models.alimento.Alimento;
import models.alimento.TipoAlimento;
import connection.ClientSocket;

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
		this.view.setVisible(true);
		this.alimentos = new ArrayList<Alimento>();
		this.alimentosPedidos = new ArrayList<Alimento>();
		blanquearImagen();
		
	}
	
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
	 * Colocar los alimentos en el JTree
	 * @param alimentos
	 */
	public void setAlimentosMenu (ArrayList<Alimento> alimentos) {
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
	 * @author fabia
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
	 * @param path
	 * @return
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
	
	public void agregarTablaCarrito(String nombre, String cantidad) {
		String[] parametro = {nombre,cantidad};
		view.getCarritoModel().addRow(parametro);
	}
}
