package alc.appnaranja.presentador;

public interface IPresentadorConfirmarCita {
	
	/**
	 * Se realizan todas las operaciones necesarias para mostrar la VistaConfirmarCita.
	 */
	public void mostrarVistaConfirmarCita();
	
	/**
	 * Se carga del modelo la información de la cita.
	 */
	public void cargarInfoCita();
	
	/**
	 * Se presenta en la vista la información de la cita.
	 */
	public void presentarInfoCita();
	
	/**
	 * Se introduce la cita en la base de datos externa y queda confirmada.
	 */
	public void confirmarCita();
	
	/**
	 * Lanza la actividad Principal de la aplicación, donde el usuario puede acceder a todas las funciones de la aplicación.
	 */
	public void lanzarPrincipal();

}
