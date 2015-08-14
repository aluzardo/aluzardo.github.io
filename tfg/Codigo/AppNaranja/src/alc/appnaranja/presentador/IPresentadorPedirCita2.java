package alc.appnaranja.presentador;

public interface IPresentadorPedirCita2 {
	
	/**
	 * Se realizan todas las operaciones necesarias para mostrar la VistaPedirCita2.
	 */
	public void mostrarVistaPedirCita2();
	
	/**
	 * Se cargan los precios de los servicios del modelo
	 */
	public void cargarServicios();
	
	/**
	 * Se presentan los precios de los servicios en la vista
	 */
	public void presentarServicios();
	
	/**
	 * Se calcula el precio total de los servicios seleccionados por el usuario.
	 */
	public void calculaPrecioTotal();
	
	/**
	 * Se cambia en la cista el precio total.
	 */
	public void cambiaPrecioVista();
	
	/**
	 * Se guardan en el modelo los datos introducidos por el usuario en la vista.
	 */
	public void guardarDatos();
	
	/**
	 * Lanza la actividad PedirCita3 de la aplicación, donde el usuario debe introducir la fecha en la que asistirá a la cita.
	 */
	public void lanzarPedirCita3();
	

}
