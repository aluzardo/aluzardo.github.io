package alc.appnaranja.vista;


public interface IVistaPedirCita1 {
	
	/**
	 * Obtiene el nombre introducido por el usuario en el campo nombre
	 * @return Nombre introducido por el usuario
	 */
	public String getTextoNombre();
	
	/**
	 * Obtiene el telefono introducido por el usuario en el campo telefono
	 * @return Telefono introducido por el usuario
	 */
	public String getTextoTelefono();
	
	/**
	 * Obtiene el estado del botón Hombre de la vista
	 * @return Estado del botón Hombre
	 */
	public boolean getEstadoBotonHombre();
	
	/**
	 * Obtiene el estado del botón Mujer de la vista
	 * @return Estado del botón Mujer
	 */
	public boolean getEstadoBotonMujer();
	
	/**
	 * Actualiza la lista de peluquerías de la vista
	 * @param lista Listado de peluquerías que se muestra en la vista.
	 */
	public void setListaPeluquerias(Object lista);
	
	/**
	 * Obtiene la peluquería que ha seleccionado el usuario en la vista
	 * @return Nombre de la peluquería seleccionada por el usuario
	 */
	public String getPeluqueriaSeleccionada();
	
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
