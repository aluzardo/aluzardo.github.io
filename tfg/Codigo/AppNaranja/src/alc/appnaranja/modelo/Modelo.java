package alc.appnaranja.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.google.android.gms.maps.model.LatLng;

import alc.appnaranja.AppMediador;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class Modelo implements IModelo {
	private static Modelo singleton = null;
	
	private String citaSeleccionada;
	private String peluqueriaSeleccionada;
	
	
	private String nombre;
	private String telefono;
	private String sexo;
	private String peluqueria;
	private String fecha;
	private String hora;
	private ArrayList<String> serviciosSeleccionados = new ArrayList<String>();
	
	private static DatosCita datosCita;
	private static ArrayList<String> listaServicios = new ArrayList<String>();
	private static DatosHorario horarioCita = null;

	
	private static ArrayList<DatosPeluqueria> peluquerias = new ArrayList<DatosPeluqueria>();
	
	private static ArrayList<DatosHorario> horariosOcupados = new ArrayList<DatosHorario>();
	private static ArrayList<String> festivos = new ArrayList<String>();

	
	private static BroadcastReceiver receptorDatos = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			if (intent.getAction().equals(AppMediador.AVISO_CITA_MODELO)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					datosCita = (DatosCita)extras.getSerializable(AppMediador.DATOS_CITA_MODELO);	
					CitaServicio.obtenerCitaServicio(datosCita.getId_cita());
					appMediador.registerReceiver(receptorDatos, new String[]{AppMediador.AVISO_SERVICIOSCITA});
				} 
			} 
			
			if (intent.getAction().equals(AppMediador.AVISO_SERVICIOSCITA)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					listaServicios = (ArrayList<String>)extras.getSerializable(AppMediador.DATOS_SERVICIOSCITA);	
					Horarios.obtenerHorario(datosCita.getId_horario());
					appMediador.registerReceiver(receptorDatos, new String[]{AppMediador.AVISO_HORARIO});
				}
			}
			
			if (intent.getAction().equals(AppMediador.AVISO_HORARIO)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					horarioCita = (DatosHorario)extras.getSerializable(AppMediador.DATOS_HORARIO);	
					String dirigido = "";
					if(datosCita.getSexo().equalsIgnoreCase("Hombre")){
						dirigido = "Don";
					}
					if(datosCita.getSexo().equalsIgnoreCase("Mujer")){
						dirigido = "Doña";
					}
					String infoCita = dirigido + " "+ datosCita.getNombre() + " ha reservado hora para "
							+ listaServicios.toString() + " el día " + horarioCita.getFecha() + " a las "
							+ horarioCita.getHora() + " en la Peluquería Naranja " + horarioCita.getId_peluqueria() + ". \n"
							+ "\nSi no puede asistir a la cita o alguno de los datos es incorrecto por favor cancele la cita y pida una nueva";
					Bundle extra = new Bundle();
					extra.putSerializable(AppMediador.DATOS_CITA, infoCita);
					appMediador.sendBroadcast(AppMediador.AVISO_CITA, extra);
				} 
			}
			
			if (intent.getAction().equals(AppMediador.AVISO_SERVICIOS_MODELO)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					ArrayList<DatosServicios> servicios = (ArrayList<DatosServicios>)extras.getSerializable(AppMediador.DATOS_SERVICIOS_MODELO);	
					
					ArrayList<String> listaServicios =  new ArrayList<String>();
					for (int i = 0; i < servicios.size(); i++){
						listaServicios.add(servicios.get(i).getNombre()+" ("+servicios.get(i).getPrecio()+" €)");
					}
					Bundle extra = new Bundle();
					extra.putSerializable(AppMediador.DATOS_SERVICIOS, listaServicios);
					appMediador.sendBroadcast(AppMediador.AVISO_SERVICIOS, extra);
				} 
			}

			if (intent.getAction().equals(AppMediador.AVISO_PELUQUERIAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					peluquerias = (ArrayList<DatosPeluqueria>)extras.getSerializable(AppMediador.DATOS_PELUQUERIAS);	
				} 
			} 
			
			if (intent.getAction().equals(AppMediador.AVISO_HORARIOS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					horariosOcupados = (ArrayList<DatosHorario>)extras.getSerializable(AppMediador.DATOS_HORARIOS);	
					
				} 
			}
			
			if (intent.getAction().equals(AppMediador.AVISO_FESTIVOS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					festivos = (ArrayList<String>)extras.getSerializable(AppMediador.DATOS_FESTIVOS);		
				} 
			}
			

		}
	};

	public static Modelo getInstance() {
		if (singleton == null){
			singleton = new Modelo();
		}
		return singleton;
	}
	@Override
	public void obtenerListaPeluquerias() {
		Peluquerias.obtenerListaPeluquerias();
	}
	@Override
	public String obtenerDescripcionPeluqueria(String id_peluqueria) {
		String descripcionPeluqueria = "Esta peluquería no se ha podido cargar de la base de datos";
		if(id_peluqueria!=null){
			for (int i = 0; i < peluquerias.size(); i++){
				if(peluquerias.get(i).getId_peluqueria().contentEquals(id_peluqueria)){
					descripcionPeluqueria = peluquerias.get(i).getDescripcion()+"\n"+peluquerias.get(i).getDireccion()+"\n"+peluquerias.get(i).getTelefono();
				}
			}
		}

		return descripcionPeluqueria;
	}
	@Override
	public Bitmap obtenerImagenPeluqueria(String id_peluqueria) {
			byte[] datosImagen = null; 
			
			if(id_peluqueria!=null){
				for (int i = 0; i < peluquerias.size(); i++){
					if(peluquerias.get(i).getId_peluqueria().contentEquals(id_peluqueria)){
						datosImagen=peluquerias.get(i).getImagen();
					}
				}
			}
			
			if(datosImagen==null){
				return null;
			}else{
				Bitmap bmp = BitmapFactory.decodeByteArray(datosImagen, 0,datosImagen.length);
				return bmp;
			}
	}
	@Override
	public LatLng posicionPeluqueria(String id_peluqueria) {
		LatLng posicionPeluqueria= new LatLng(0,0);
		if(id_peluqueria!=null){
			for (int i = 0; i < peluquerias.size(); i++){
				if(peluquerias.get(i).getId_peluqueria().contentEquals(id_peluqueria)){
					posicionPeluqueria=peluquerias.get(i).getLocalizacion();
				}
			}
		}
		return posicionPeluqueria;
	}
	
	@Override
	public ArrayList<LatLng> posicionTodasPeluquerias() {
		ArrayList<LatLng> posicionTodasPeluquerias =  new ArrayList<LatLng>();
		for (int i = 0; i < peluquerias.size(); i++){
			posicionTodasPeluquerias.add(peluquerias.get(i).getLocalizacion());
		}
		return posicionTodasPeluquerias;
	}
	@Override
	public void guardarDatos(String nombre, String telefono, String sexo, String peluqueria) {
		
		this.nombre=nombre;
		this.telefono=telefono;
		this.sexo=sexo;
		this.peluqueria=peluqueria;
		
		AppMediador.getInstance().registerReceiver(receptorDatos, new String[]{AppMediador.AVISO_HORARIOS, AppMediador.AVISO_FESTIVOS});
		Horarios.obtenerHorarios(peluqueria);
		Festivos.obtenerTodosFestivos(peluqueria);
	}
	@Override
	public void obtenerServicios() {
		AppMediador.getInstance().registerReceiver(receptorDatos, new String[]{AppMediador.AVISO_SERVICIOS_MODELO});
		Servicios.obtenerTodosServicios();
	}

	
	@Override
	public String calculaPrecioTotal(ArrayList<String> servicios) {
		int precio =0;
		ArrayList<Integer> precios =  new ArrayList<Integer>();
		//busco los precios en el String de todos los servicios
		for (int k = 0; k < servicios.size(); k++){
			for (int i = 0; i < servicios.get(k).length(); i++) {
				char caracter = servicios.get(k).charAt(i);
				if (Character.isDigit(caracter)) {
					precios.add(Character.getNumericValue(caracter));				
				} 
			}

		}
		for (int k = 0; k < precios.size(); k++){
			precio = precios.get(k)+precio;
		}

		return "Total: "+precio+" €";
	}
	@Override
	public void guardarDatos(ArrayList<String> servicios) {
		this.serviciosSeleccionados=servicios;
	}
	@SuppressLint("SimpleDateFormat")
	@Override
	public ArrayList<String> obtenerFechas(String id_peluqueria) {

		ArrayList<String> fechas =  new ArrayList<String>();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		//construllo un Array con las fechas de los 30 dias posteriores a hoy
		for (int i = 0; i < 30; i++){
			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			calendar.add(Calendar.DATE, i+1);
			String fecha = formatoFecha.format(calendar.getTime());
			fechas.add(fecha);
		}
		
		//Elimino las fechas ocupadas por festivos
		
		for (int i = 0; i < festivos.size(); i++){
			fechas.remove(festivos.get(i));
		}
		
		//Elimino las fechas con todos los horarios ocupados
		for (int i = 0; i < fechas.size(); i++){
			if(obtenerHoras(fechas.get(i)).isEmpty()){
				fechas.remove(i);
			}
		}

		return fechas;
	}
	@Override
	public ArrayList<String> obtenerHoras(String fecha) {
		ArrayList<String> horas =  new ArrayList<String>();
		int horaApertura = 9;
		for (int i = 0; i < 12; i++){
			horas.add(""+(horaApertura+i)+":00");
		}
		
		//Elimino las horas ocupadas
		for (int i = 0; i < horariosOcupados.size(); i++){
			if(horariosOcupados.get(i).getFecha().contentEquals(fecha)){
				horas.remove(horariosOcupados.get(i).getHora());
			}
		}		
		return horas;
		
		
	}
	@Override
	public void guardarDatos(String fecha, String hora) {
		this.fecha=fecha;
		this.hora=hora;
	}
	@Override
	public String obtenerInfoCita() {
		
		String dirigido = "";
		if(sexo.equalsIgnoreCase("Hombre")){
			dirigido = "Don";
		}
		if(sexo.equalsIgnoreCase("Mujer")){
			dirigido = "Doña";
		}
	
		
		String infoCita = dirigido + " "+ nombre + " ha reservado hora para "
				+ serviciosSeleccionados.toString() + " el día " + fecha + " a las "
				+ hora + " en la Peluquería Naranja " + peluqueria + ". \n"
				+ "\nSi todos los datos son correctos, por favor confirma la reserva";
		
		return infoCita;
	}
	@Override
	public void confirmarCita() {
		
		String id_cita = fecha+hora+nombre;
		String id_horario = fecha+hora;
		
		DatosCita datosCita = new DatosCita(id_cita, id_horario, nombre, telefono, sexo);
		Citas.insertarCita(datosCita);

		DatosHorario datosHorario = new DatosHorario(id_horario, peluqueria, fecha, hora);
		Horarios.insertarHorario(datosHorario);
		
		for(int i = 0; i < serviciosSeleccionados.size(); i++){
			DatosCitaServicio datosCitaServicio = new DatosCitaServicio(id_cita, serviciosSeleccionados.get(i));
			CitaServicio.insertarCitaServicio(datosCitaServicio);
		}
	}
	@Override
	public void obtenerListaCitas(String id_usuario) {
		Citas.obtenerListaCitas(id_usuario);
		
	}
	@Override
	public void obtenerInfoCita(String id_cita) {
		AppMediador.getInstance().registerReceiver(receptorDatos, new String[]{AppMediador.AVISO_CITA_MODELO});
		Citas.obtenerInfoCita(id_cita);
	}
	@Override
	public void cancelarCita() {
		Citas.eliminarCita(citaSeleccionada);
		CitaServicio.eliminarCitaServicio(citaSeleccionada);
		Horarios.eliminarHorario(datosCita.getId_horario());
	}
	
	@Override
	public void setCitaSeleccionada(String id_cita) {
		this.citaSeleccionada= id_cita;
		
	}
	@Override
	public String getCitaSeleccionada() {
		return citaSeleccionada;
		
	}
	@Override
	public void setPeluqueriaSeleccionada(String id_peluqueria) {
		this.peluqueriaSeleccionada= id_peluqueria;
		
		
	}
	@Override
	public String getPeluqueriaSeleccionada() {
		return peluqueriaSeleccionada;
	}
	@Override
	public void setTelefono(String telefono) {
		this.telefono = telefono;

	}
	@Override
	public String getTelefono() {
		return telefono;
	}
	@Override
	public void obtenerDatosPeluquerias() {
		AppMediador.getInstance().registerReceiver(receptorDatos, new String[]{AppMediador.AVISO_PELUQUERIAS});
		Peluquerias.obtenerTodasPeluquerias();
	}

	
	
}
