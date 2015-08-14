package alc.appnaranja.modelo;


import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.graphics.Bitmap;


public interface IModelo {
	/**
	 * Obtiene la lista de peluquerías
	 */
	public void obtenerListaPeluquerias();
	
	/**
	 * Obtiene la descripción de la peluquería
	 * @param id_peluqueria Identificador de la peluquería
	 * @return Descripción de la peluquería
	 */
	public String obtenerDescripcionPeluqueria(String id_peluqueria);
	
	/**
	 * Obtiene la imagen de la peluquería
	 * @param id_peluqueria Identificador de la peluquería
	 * @return Imagen de la peluquería.
	 */
	public Bitmap obtenerImagenPeluqueria(String id_peluqueria);
	
	/**
	 * Obtiene la ubicación geográfica de la peluquería.
	 * @param id_peluqueria Identificador de la peluquería.
	 * @return Posición geográfica de la peluquería.
	 */
	public LatLng posicionPeluqueria(String id_peluqueria);
	
	/**
	 * Obtiene la ubicación geográfica de todas las peluquerías.
	 * @return Todas las posiciones geográficas de las peluquerías.
	 */
	public ArrayList<LatLng> posicionTodasPeluquerias();
	
	/**
	 * Guarda en el modelo los datos introducidos por el usuario.
	 * @param nombre Nombre del usuario.
	 * @param telefono Teléfono del usuario, utilizado como identificador del mismo.
	 * @param sexo Sexo del usuario.
	 * @param peluqueria Peluquería elegida por el usuario.
	 */
	public void guardarDatos(String nombre, String telefono, String sexo, String peluqueria);
	
	/**
	 * Obtiene los servicios
	 */
	public void obtenerServicios();
	
	/**
	 * Calcula el precio total segun los servicios.
	 * @param servicios Listado de servicios.
	 * @return Precio de los servicios seleccionados.
	 */
	public String calculaPrecioTotal(ArrayList<String> servicios);
	
	/**
	 * Guarda los datos introducidos por el usuario.
	 * @param servicios Listado de servicios introducidos por el usuario.
	 */
	public void guardarDatos(ArrayList<String> servicios);
	
	
	/**
	 * Obtiene las fechas disponibles para pedir cita.
	 * @return Fechas disponibles para pedir cita.
	 */
	public ArrayList<String> obtenerFechas(String id_peluqueria);
	

	/**
	 * Obtiene las horas disponibles para una fecha determinada.
	 * @param fecha Fecha para buscar las horas.
	 * @return Listado con todas las horas disponibles.
	 */
	public ArrayList<String> obtenerHoras(String fecha);
	
	/**
	 * Guarda los datos introducidos por el usuario.
	 * @param fecha Fecha seleccionada por el usuario
	 * @param hora Hora seleccionada por el usuario.
	 */
	public void guardarDatos(String fecha, String hora);
	
	/**
	 * Obtiene infomación de la cita que ha ido introduciendo el usuario en la aplicación.
	 */
	public String obtenerInfoCita();
	
	/**
	 * Confirma la cita que introdujo el usuario en la aplicación y la envia a la base de datos externa.
	 * @return Información de la cita.
	 */
	public void confirmarCita();
	
	/**
	 * Obtiene el listado de citas que ha pedido el usuario a través de la aplicación.
	 * @param id_usuario Identificador del usuario.
	 * @return Listado con las citas.
	 */
	public void obtenerListaCitas(String id_usuario);
	
	/**
	 * Obtiene información de una cita con su identificador.
	 * @param id_cita Identificador de la cita.
	 */
	public void obtenerInfoCita(String id_cita);
	
	/**
	 * Cancela una cita que ha pedido el usuario y la borra de la base de datos externa.
	 * @return Retorna verdadero si se cancela con éxito o falso en caso contrario.
	 */
	public void cancelarCita();
	
	//Añadidos 
	
	/**
	 * Guarda la cita seleccionada en MisCitasMaestro
	 * @param id_cita Identificador de la cita
	 */
	public void setCitaSeleccionada(String id_cita);
	
	/**
	 * Obtiene la cita seleccionada en MisCitasMaestro
	 * @return id_cita Identificador de la cita
	 */
	public String getCitaSeleccionada();
	
	/**
	 * Guarda la peluqueria seleccionada en DondeEstamos
	 * @param id_peluqueria Identificador de la peluqueria
	 */
	public void setPeluqueriaSeleccionada(String id_peluqueria);
	
	/**
	 * Obtiene la peluqueria seleccionada en DondeEstamos
	 * @return id_peluqueria Identificador de la peluqueria
	 */
	public String getPeluqueriaSeleccionada();
	
	/**
	 * Guarda el telefono del usuario
	 * @param telefono Teléfono del usuario utilizado como identificador
	 */
	public void setTelefono(String telefono);
	/**
	 * Obtiene el telefono del usuario
	 * @return telefono Teléfono del usuario utilizado como identificador
	 */
	public String getTelefono();
	
	/**
	 * Obtiene del servidor externo los datos de todas las peluquerias
	 */
	public void obtenerDatosPeluquerias();
	
	
}
