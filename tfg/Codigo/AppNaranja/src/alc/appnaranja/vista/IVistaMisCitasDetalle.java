package alc.appnaranja.vista;



public interface IVistaMisCitasDetalle {
	
	/**
	 * Actualiza el texto que aparece en la vista	
	 * @param texto Texto que pertenece a los detalles de la cita
	 */
	public void setTextoMiCita(String texto);
	
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
	 * Muestra una alerta para confirmar si quiere cancelar la cita.
	 * @param titulo Título de la alerta.
	 */
	public void mostrarAlerta(String titulo);



}
