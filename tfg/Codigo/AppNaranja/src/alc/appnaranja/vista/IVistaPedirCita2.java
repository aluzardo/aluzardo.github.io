package alc.appnaranja.vista;


public interface IVistaPedirCita2 {
	
	/**
	 * Obtiene el estado del botón CorteDePelo de la vista
	 * @return Estado del botón CorteDePelo
	 */
	public boolean getEstadoCorteDePelo();
	
	/**
	 * Obtiene el estado del botón Manicura de la vista
	 * @return Estado del botón Manicura
	 */
	public boolean getEstadoManicura();
	
	/**
	 * Obtiene el estado del botón Pedicura de la vista
	 * @return Estado del botón Pedicura
	 */
	public boolean getEstadoPedicura();
	
	/**
	 * Obtiene el estado del botón Mechas de la vista
	 * @return Estado del botón Mechas
	 */
	public boolean getEstadoMechas();
	
	/**
	 * Obtiene el estado del botón Tinte de la vista
	 * @return Estado del botón Tinte
	 */
	public boolean getEstadoTinte();
	
	/**
	 * Obtiene el estado del botón CorteDeFlecos de la vista
	 * @return Estado del botón CorteDeFlecos
	 */
	public boolean getEstadoCorteDeFlecos();
	
	
	/**
	 * Actualiza el precio total en la vista
	 * @param precio Precio a actualizar en la vista
	 */
	public void setPrecioTotal(String precio);
	
	
	//añadido nuevo
	
	/**
	 * Actualiza el precio del corte de pelo en la vista
	 * @param precio Precio a actualizar en la vista
	 */
	public void setPrecioCorteDePelo(String precio);
	
	/**
	 * Actualiza el precio de la manicura en la vista
	 * @param precio Precio a actualizar en la vista
	 */
	public void setPrecioManicura(String precio);
	
	/**
	 * Actualiza el precio de la pedicura en la vista
	 * @param precio Precio a actualizar en la vista
	 */
	public void setPrecioPedicura(String precio);
	
	/**
	 * Actualiza el precio de las mechas en la vista
	 * @param precio Precio a actualizar en la vista
	 */
	public void setPrecioMechas(String precio);
	
	/**
	 * Actualiza el precio del tinte en la vista
	 * @param precio Precio a actualizar en la vista
	 */
	public void setPrecioTinte(String precio);
	
	/**
	 * Actualiza el precio del corte de flecos en la vista
	 * @param precio Precio a actualizar en la vista
	 */
	public void setPrecioCorteDeFlecos(String precio);
	
	/**
	 * Muestra una alerta si ha habido algún error.
	 * @param titulo Título de la alerta.
	 */
	public void mostrarAlerta(String titulo);
	
	/**
	 * Muestra una barra de progreso para indicar que se está haciendo alguna operación con la nube.
	 * @param mensaje mensaje que se muestra mientras se carga
	 */
	public void mostrarProgreso(String mensaje);
	
	/**
	 * Elimina la barra de progreso.
	 */
	public void eliminarProgreso();
	
}
