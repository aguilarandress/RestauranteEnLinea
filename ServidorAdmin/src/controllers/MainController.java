package controllers;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import catalogoXML.CreadorXML;
import models.alimento.Alimento;
import models.alimento.TipoAlimento;
import models.cola.Cola;
import models.catalogo.Catalogo;
import views.AgregarAlimentoView;
import views.EditarView;
import views.MainView;
import server.TCPServer;

/**
 * Controlador principal de la aplicacion
 * @author Andres
 *
 */
public class MainController {
	
	private MainView view;
	private TCPServer mainServer;
	private Catalogo catalogo;
	
	private Alimento alimentoSelected;
	
	public MainController(MainView view, Catalogo catalogo) {
		this.view = view;
		this.catalogo = catalogo;
		
		// Iniciar servidor
		this.mainServer = new TCPServer(this);
		mainServer.start();
		
		// Agregar eventos a los componentes
		this.view.getMontoExpressBtn().addActionListener(new EventoMontoExpressBtn());
		this.view.getMontoEmpaqueBtn().addActionListener(new EventoMontoEmpaqueBtn());
		this.view.getTreeCatalogo().addTreeSelectionListener(new EventoSeleccionarCodigoTree());
		this.view.getEditarBtn().addActionListener(new EventoEditarPlatillo(this));
		this.view.getAgregarBtn().addActionListener(new EventoAgregarAlimento(this));
		
		// Pone la imagen por defecto
		this.blanquearImagen();
		
		// Cargar alimentos del catalogo
		this.cargarCatalogo();
		view.setVisible(true);
	}
	
	/**
	 * Carga el catalogo de alimentos
	 */
	public void cargarCatalogo() {
		// TODO: Obtener monto express y de empaque del XML
		
		// Obtener alimentos guardados en el xml
		CreadorXML catalogo = CreadorXML.getInstance();
		Cola<Alimento> alimentos = catalogo.ObtenerCatalogo();
		this.catalogo.setAlimentos(alimentos);
		this.crearCatalogo(alimentos);
	}
	
	
	/**
	 * Obtiene el catalogo
	 * @return Catalogo
	 */
	public Catalogo getCatalogo() {
		return catalogo;
	}
	
	/**
	 * Cambia el catalogo
	 * @param catalogo
	 */
	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	/**
	 * Agregar una actividad nueva a la bitacora de conexiones
	 * @param actividad La actividad de conexion nueva
	 */
	public void agregarActividadDeConexion(String actividad) {
		this.view.agregarABitacoraConexiones(actividad);
	}
	
	/**
	 * Cargar los alimentos a la vista
	 * @param alimentos Los alimentos que se van a cargar
	 */
	public void crearCatalogo(Cola<Alimento> alimentos) {
		this.catalogo.setAlimentos(alimentos);
		
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Catalogo");
		
		DefaultMutableTreeNode entradas = new DefaultMutableTreeNode("Entradas");
		
		DefaultMutableTreeNode platosF = new DefaultMutableTreeNode("Platos Fuertes");
		
		DefaultMutableTreeNode bebidas = new DefaultMutableTreeNode("Bebidas");
		
		DefaultMutableTreeNode postres = new DefaultMutableTreeNode("Postres");
		for(int posActual = 0; posActual < alimentos.getCantidad(); posActual++) {
			Alimento actual = alimentos.get(posActual);
			
			// Nombre de la comida
			DefaultMutableTreeNode codigo = new DefaultMutableTreeNode(actual.getCodigo());
			
			// Codigo de la comida
			DefaultMutableTreeNode nombreNode = new DefaultMutableTreeNode("Nombre");
			DefaultMutableTreeNode nombre = new DefaultMutableTreeNode(actual.getNombre());
			nombreNode.add(nombre);
			codigo.add(nombreNode);
			
			// Calorias de la comida		
			DefaultMutableTreeNode caloriasNode = new DefaultMutableTreeNode("Calorias");
			DefaultMutableTreeNode calorias = new DefaultMutableTreeNode(Float.toString(
					actual.getCalorias()));
			caloriasNode.add(calorias);
			codigo.add(caloriasNode);
			
			// Precio de la comida
			DefaultMutableTreeNode precioNode = new DefaultMutableTreeNode("Precio");
			DefaultMutableTreeNode precio = new DefaultMutableTreeNode(Float.toString(
					actual.getPrecio()));
			precioNode.add(precio);
			codigo.add(precioNode);
			
			// Agregar segun corresponda
			if (actual.getTipo().equals(TipoAlimento.ENTRADA)) {
				entradas.add(codigo);
			}
			else if(actual.getTipo().equals(TipoAlimento.PLATO_FUERTE)) {
				platosF.add(codigo);
			}
			else if(actual.getTipo().equals(TipoAlimento.BEBIDA)) {
				bebidas.add(codigo);
			}
			else {
				postres.add(codigo);
			}
		}
		
		raiz.add(entradas);
		raiz.add(platosF);
		raiz.add(bebidas);
		raiz.add(postres);
		DefaultTreeModel modelo = new DefaultTreeModel(raiz);
		this.view.getTreeCatalogo().setModel(modelo);
	}
	
	/**
	 * Verifica si la imagen existe
	 * @param path
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
	 * Carga la imagen correspondiente al alimento
	 * @param alimento
	 */
	public void cargarImagen(Alimento alimento) {
		view.getDescripLabel().setEditable(true);
        ImageIcon imageIcon = new ImageIcon(alimento.getImagenPath());
        Image copiaImage = imageIcon.getImage();
        Image resizedImage = copiaImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(resizedImage);
        view.getImagenLabel().setIcon(imageIcon);
        view.getDescripLabel().setText(alimento.getDescripcion());
        view.getDescripLabel().setEditable(false);
	}
	
	/**
	 * Blanque la imagen
	 */
	public void blanquearImagen() {
		view.getDescripLabel().setEditable(true);
		if(verificarImagen("../Imagenes/menu.jpg")) {
	        ImageIcon imageIcon = new ImageIcon("../Imagenes/menu.jpg");
	        Image copiaImage = imageIcon.getImage();
	        Image resizedImage = copiaImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
	        imageIcon = new ImageIcon(resizedImage);
	        view.getImagenLabel().setIcon(imageIcon);
	        view.getDescripLabel().setText("");
	        view.getDescripLabel().setEditable(false);
		}
	}
	
	
	private class EventoMontoExpressBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// Obtener monto ingresado
				String montoIngresado = view.getMontoExpressInput().getText();
				float montoExpress = Float.parseFloat(montoIngresado);
				// Guardar en el modelo
				Catalogo.setMontoExpress(montoExpress);
				view.getMontoExpressLabel().setText(montoIngresado);
				
				// TODO: Modificar en el XML
				view.getMontoExpressInput().setText("");
				view.displayMessage(true, "Monto express modificado");
			} catch (Exception parseExpcetion) {
				view.displayMessage(false, "Por favor ingrese un valor valido");
			}
		}
	}
	
	private class EventoMontoEmpaqueBtn implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// Obtener monto ingresado
				String montoIngresado = view.getMontoEmpaqueInput().getText();
				float montoEmpaque = Float.parseFloat(montoIngresado);
				// Guardar en el modelo
				Catalogo.setMontoEmpaque(montoEmpaque);
				view.getMontoEmpaqueLabel().setText(montoIngresado);
				
				// TODO: Modificar en el XML
				view.getMontoEmpaqueInput().setText("");
				view.displayMessage(true, "Monto de empaque modificado");
			} catch (Exception parseExpcetion) {
				view.displayMessage(false, "Por favor ingrese un valor valido");
			}
		}
	}
	
	private class EventoSeleccionarCodigoTree implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			if(view.getTreeCatalogo().getSelectionPath() == null) {
				System.out.println("Nada");
				blanquearImagen();
				return;
			}
			Cola<Alimento> alimentos = catalogo.getAlimentos();
			for(int i = 0; i < catalogo.getAlimentos().getCantidad(); i++) {
				Alimento alimento = catalogo.getAlimentos().get(i);
				
				if(alimento.getCodigo().equals(view.getTreeCatalogo().getSelectionPath().getLastPathComponent().toString())) {
					
					alimentoSelected = alimento;
					
					if(verificarImagen(alimento.getImagenPath())) {
						cargarImagen(alimento);
						view.getEditarBtn().setEnabled(true);
					} else {
						view.getEditarBtn().setEnabled(false);
					}
					
				}
			}
			
		}
		
	}
	
	/**
	 * Evento para poder editar la informacion de un platillo
	 * @author Kenneth Sanchez
	 *
	 */
	private class EventoEditarPlatillo implements ActionListener {
		MainController controller;
		
		/**
		 * Constructor
		 * @param pMainController
		 */
		public EventoEditarPlatillo(MainController pMainController) {
			controller = pMainController;
		}
		
		/**
		 * Se activa cada vez que hay un evento
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			EditarController editarController = new EditarController(alimentoSelected, controller);
		}
	}
	
	
	/**
	 * Evento que muestra la ventana para agregar un platillo
	 * @author Kenneth Sanchez
	 *
	 */
	private class EventoAgregarAlimento implements ActionListener {
		
		MainController controller;
		
		/**
		 * Constructor
		 * @param pMainController
		 */
		public EventoAgregarAlimento(MainController pMainController) {
			controller = pMainController;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			AgregarAlimentoController agregarController = new AgregarAlimentoController(controller);
		}
		
	}
}
