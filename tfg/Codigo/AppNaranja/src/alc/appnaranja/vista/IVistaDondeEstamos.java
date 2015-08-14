package alc.appnaranja.vista;


public interface IVistaDondeEstamos {
	
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
	 * Actualiza la imagen de la peluquería que aparece en la vista
	 * @param imagen Imagen de la peluquería
	 */
	public void setImagenPeluqueria(Object imagen);
	/**
	 * Actualiza el texto de la descripción de la peluquería
	 * @param texto Texto de la descripción de la peluquería
	 */
	public void setTextoDescripcionPeluqueria(String texto);
	
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
