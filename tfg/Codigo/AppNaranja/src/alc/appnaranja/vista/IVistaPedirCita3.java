package alc.appnaranja.vista;


public interface IVistaPedirCita3 {
	/**
	 * Actulaiza los días disponibles en la vista
	 * @param dias Dias disponibles
	 */
	public void setDiasDisponibles(Object dias);
	
	/**
	 * Actualiza las horas disponibles en la vista
	 * @param horas Horas disponibles en la vista
	 */
	public void setHorasDisponibles(Object horas);
	
	/**
	 * Obtiene el día seleccionado por el usuario en la vista
	 * @return Día seleccionado en la vista
	 */
	public String getDia();
	
	/**
	 * Obtiene la hora seleccionada en la vista
	 * @return Hora seleccionada en la vita
	 */
	public String getHora();
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
