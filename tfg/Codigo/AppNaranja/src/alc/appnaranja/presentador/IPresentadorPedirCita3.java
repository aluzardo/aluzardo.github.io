package alc.appnaranja.presentador;


public interface IPresentadorPedirCita3 {
	
	/**
	 * Se realizan todas las operaciones necesarias para mostrar la VistaPedirCita3.
	 */
	public void mostrarVistaPedirCita3();
	
	/**
	 * Carga las fechas disponibles del modelo
	 */
	public void cargarFechas();
	
	/**
	 * Presenta en la vista las fechas disponibles
	 */
	public void presentarFechas();
	
	/**
	 * Carga las horas disponibles del modelo.
	 */
	public void cargarHoras();
	
	/**
	 * Presenta las horas disponibles en la vista.
	 */
	public void presentarHoras();
	
	/**
	 * Guarda en el modelo los datos introducidos por el usuario en la vista
	 */
	public void guardarDatos();
	
	/**
	 * Lanza la actividad ConfirmarCita de la aplicación, donde el usuario puede confirmar la cita que ha introducido.
	 */
	public void lanzarConfirmarCita();
	

}
