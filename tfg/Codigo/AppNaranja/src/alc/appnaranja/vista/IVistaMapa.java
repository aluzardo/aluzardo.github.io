package alc.appnaranja.vista;


public interface IVistaMapa {
	
	/**
	 * Actualiza el texto que aparece en el mapa
	 * @param texto Texto que pertenece al mapa
	 */
	public void setTextoMapa(String texto);
	/**
	 * Obtiene el mapa que aparece en la vista
	 * @return Mapa de la vista
	 */
	public Object getMapa();
	/**
	 * Muestra una barra de progreso para indicar que se está haciendo alguna operación con la nube.
	 * @param mensaje mensaje que se muestra mientras se carga
	 */
	public void mostrarProgreso(String mensaje);
	
	/**
	 * Elimina la barra de progreso.
	 */
	public void eliminarProgreso();
	
	/**
	 * Muestra una alerta si ha habido algún error.
	 * @param titulo Título de la alerta.
	 */
	public void mostrarAlerta(String titulo);
	
}
