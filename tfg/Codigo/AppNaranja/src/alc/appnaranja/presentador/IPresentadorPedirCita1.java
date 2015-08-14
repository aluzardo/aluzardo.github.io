package alc.appnaranja.presentador;


public interface IPresentadorPedirCita1 {
	
	/**
	 * Se realizan todas las operaciones necesarias para mostrar la VistaPedirCita1.
	 */
	public void mostrarVistaPedirCita1();
	
	/**
	 * Carga del modelo la lista de peluquerías.
	 */
	public void cargarListaPeluquerias();
	
	/**
	 * Presenta en la vista la lista de peluquerías.
	 */
	public void presentarListaPeluquerias();
	
	/**
	 * Guarda en el modelo los datos introducidos por el usuario.
	 */
	public void guardarDatos();
	
	/**
	 * Lanza la actividad PedirCita2 de la aplicación, donde el usuario debe introducir los servicios que realizará en la cita.
	 */
	public void lanzarPedirCita2();
	

}
