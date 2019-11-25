package models.alimento;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * 
 * @author Kenneth Sanchez
 * @author Fabian Vargas
 * @author Andres Aguilar
 */
public class Alimento implements IConstants, Serializable {
	
	private static final TipoAlimento[] TIPOS = {TipoAlimento.BEBIDA, TipoAlimento.ENTRADA, 
			TipoAlimento.PLATO_FUERTE, TipoAlimento.POSTRE};
	
	private String codigo, nombre, descripcion,ImagenPath;
	private int racion;
	private float calorias, precio;
	private boolean habilitado;
	private TipoAlimento tipo;
	private byte contenidoImagen[];
	
	/**
	 * Carga el contenido de la imagen
	 */
	public void cargarContenidoImagen() {
		if (this.ImagenPath != null) {
			try {
				BufferedImage bImage = ImageIO.read(new File(this.ImagenPath));
				ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
				ImageIO.write(bImage, "jpg", byteOutputStream);
				this.contenidoImagen = byteOutputStream.toByteArray();
			} catch (IOException e) {
				System.out.println("**ERROR** Cargar imagen");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Obtiene el estado de habilitado del alimento
	 * @return Un booleano que representa si esta habilitado o no el alimento
	 */
	public boolean getHabilitado() {
		return this.habilitado;
	}
	
	public String toString() {
		return this.nombre + " - " + this.precio;
	}
	
	/**
	 * Crea un nuevo alimento de acuerdo al tipo
	 * @param pTipoConstant
	 */
	public Alimento(int pTipoConstant) {
		tipo = TIPOS[pTipoConstant];
		habilitado = true;
	}

	/**
	 * Retorna el codigo del alimento
	 * @return String
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Cambia el codigo del alimento
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el nombre del alimento
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Cambia el nombre del alimento
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene la descripcion del alimento
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Cambia la descripcion del alimento
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtiene la cantidad de raciones
	 * @return int
	 */
	public int getRacion() {
		return racion;
	}
	
	/**
	 * Cambia la cantidad de raciones
	 * @param racion
	 */
	public void setRacion(int racion) {
		this.racion = racion;
	}
	
	/**
	 * Obtiene las calorias del alimento
	 * @return float
	 */
	public float getCalorias() {
		return calorias;
	}
	
	/**
	 * Cambia las calorias del alimento
	 * @param calorias
	 */
	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}
	
	/**
	 * Obtiene el precio del alimento
	 * @return float
	 */
	public float getPrecio() {
		return precio;
	}
	
	/**
	 * Cambia el precio del alimento
	 * @param precio
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	/**
	 * Verifica que el alimento este habilitado
	 * @return boolean
	 */
	public boolean isHabilitado() {
		return habilitado;
	}
	
	/**
	 * Cambia el estado de habilitado del alimento
	 * @param habilitado
	 */
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	/**
	 * Obtiene el tipo de alimento
	 * @return TipoAlimento
	 */
	public TipoAlimento getTipo() {
		return tipo;
	}
	
	/**
	 * Cambia el tipo del alimento
	 * @param tipo
	 */
	public void setTipo(TipoAlimento tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Cambiar el path de imagen del alimento
	 * @param Imagenpath Nuevo path
	 */
	public void setImagenPath(String Imagenpath) {
		this.ImagenPath = Imagenpath;
	}
	
	/**
	 * Retorna el path de la imagen
	 * @return Un String con el path de la imagen
	 */
	public String getImagenPath() {
		return this.ImagenPath;
	}
	
}