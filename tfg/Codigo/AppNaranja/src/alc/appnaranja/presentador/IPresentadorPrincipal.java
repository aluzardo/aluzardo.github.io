package alc.appnaranja.presentador;

import android.app.Activity;

public interface IPresentadorPrincipal {

	/**
	 * Lanza la actividad Donde Estamos de la aplicación, donde se puede ver la ubicación de las distintas peluquerías.
	 */
	public void lanzarDondeEstamos();
	
	/**
	 * Lanza la actividad Pedir Cita de la aplicación, donde se puede pedir cita.
	 */
	public void lanzarPedirCita();
	
	/**
	 * Lanza la actividad Mis Citas de la aplicación, donde se pueden ver las citas que el usuario ha pedido.
	 */
	public void lanzarMisCitas();
	
	/**
	 * Lanza la actividad Ayuda de la aplicación, donde se proporciona ayuda para el uso de la aplicación.
	 */
	public void lanzarAyuda();
	
	/**
	 * Lanza la actividad Info de la aplicación, donde se proporciona información de la aplicación (versión, creadores...)
	 */
	public void lanzarInfo();
	
	/**
	 * Lanza la actividad Presentación de la aplicación, donde se proporciona una presentación de la empresa.
	 */
	public void lanzarPresentacion();
	
	/**
	 * Lanza la actividad Configuración de la aplicación, donde se puede cambiar la configuracion de la aplicación.
	 */
	public void lanzarConfiguracion();
	
	/**
	 * Lanza el navegador del móvil con una url específica.
	 * @param url URL de la página a la que se accederá
	 * @param a Actividad que lanza dicha url
	 */
	public void lanzarURL(String url, Activity a);
	
	/**
	 * Actualiza las preferencias por las seleccionadas por el usuario.
	 */
	public void actualizaPreferencias();
}
