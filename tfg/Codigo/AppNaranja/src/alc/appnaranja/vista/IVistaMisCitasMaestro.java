package alc.appnaranja.vista;

public interface IVistaMisCitasMaestro {
	
	/**
	 * Actualiza el listado de citas que aparece en la vista
	 * @param listaCitas Lista de las citas a actualizar
	 */
	public void setListaCitas(Object listaCitas);
	
	/**
	 * Obtiene la cita seleccionada por el usuario
	 * @return Cita seleccionada por el usuario
	 */
	public String getCitaSeleccionada();
	
	
	//añadido nuevo
	/**
	 * Obtiene el telefono del usuario que sirve para descargar las citas
	 * @return Telefono que introdujo el usuario
	 */
	public String getTelefono();
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
	 * Muestra una alerta si el usuario no ha introducido su numero de telefono previamente.
	 * @param titulo Título de la alerta.
	 */
	public void mostrarAlerta(String titulo);

	
	

}
