package models.alimento;

/**
 * 
 * @author Kenneth Sanchez
 * @author Fabian Vargas
 * @author Andres Aguilar
 */
public class Alimento implements IConstants{
	
	private static final TipoAlimento[] TIPOS = {TipoAlimento.BEBIDA, TipoAlimento.ENTRADA, 
			TipoAlimento.PLATO_FUERTE, TipoAlimento.POSTRE};
	
	private String codigo, nombre, descripcion;
	private int racion;
	private float calorias, precio;
	private boolean habilitado;
	private TipoAlimento tipo;
	
	/**
	 * Crea un nuevo alimento de acuerdo al tipo
	 * @param pTipoConstant
	 */
	public Alimento(int pTipoConstant) {
		tipo = TIPOS[pTipoConstant];
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
	
	
}
