package alc.appnaranja.presentador;

public interface IPresentadorMisCitasDetalle {

	/**
	 * Se realizan todas las operaciones necesarias para mostrar la VistaMisCitasDetalle.
	 */
	public void mostrarVistaMisCitasDetalle();
	
	/**
	 * Carga del modelo la información de la cita
	 */
	public void cargarInfoCita();
	
	/**
	 * Presenta la información de la cita en la vista.
	 */
	public void presentarInfoCita();
	
	/**
	 * Cancela la cita y la borra del modelo
	 */
	public void cancelarCita();
	
	/**
	 * Lanza la actividad MisCitasMaestro de la aplicación, donde le aparece al usuario un listado con las citas que ha pedido.
	 */
	public void lanzarMisCitasMaestro();
}


