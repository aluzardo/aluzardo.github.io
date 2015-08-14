package alc.appnaranja.presentador;


public interface IPresentadorDondeEstamos {

	/**
	 * Se realizan todas las operaciones necesarias para mostrar la VistaDondeEstamos.
	 */
	public void mostrarVistaDondeEstamos();
	
	/**
	 * Carga del modelo la lista de peluquerías.
	 */
	public void cargarListaPeluquerias();
	
	/**
	 * Presenta en la vista la lista de peluquerías.
	 */
	public void presentarListaPeluquerias();
	
	/**
	 * Se cargan del modelo los datos de la peluquería seleccionada
	 */
	public void cargarDatosPeluqueria();
	
	/**
	 * Se presentan en la vista los datos de la peluquería (Imagen y descripción).
	 */
	public void presentarDatosPeluqueria();
	
	/**
	 * Lanza la actividad Mapa de la aplicación, donde aparece un mapa con la posición de la peluquería.
	 */
	public void lanzarMapa();

}
