package alc.appnaranja.modelo;

import java.util.ArrayList;
import java.util.List;

import alc.appnaranja.AppMediador;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CitaServicio {
	
	public static final String nombreTabla = "citaServicio";
	public static final String CAMPO_CITA = "id_cita";
	public static final String CAMPO_SERVICIO = "id_servicio";
	
	public static boolean insertarCitaServicio(Object datosCitaServicio) {
		
			ParseObject po = new ParseObject(nombreTabla);
			po.put(CAMPO_CITA, ((DatosCitaServicio) datosCitaServicio).getId_cita());
			po.put(CAMPO_SERVICIO, ((DatosCitaServicio) datosCitaServicio).getId_servicio());
			po.saveInBackground();
	
		
		
		return false;
	}


	@SuppressWarnings("unchecked")
	public static boolean eliminarCitaServicio(String id_cita) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_CITA, id_cita);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {
				if (e == null) {
					for (ParseObject reg: registros) {	
					reg.deleteInBackground();
					}
					
				} 
				
			}

		});
		return false;
	}


	@SuppressWarnings("unchecked")
	public static Object obtenerCitaServicio(String id_cita) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_CITA, id_cita);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				AppMediador appMediador = AppMediador.getInstance();
				if (e == null) {
					ArrayList<String> lista = new ArrayList<String>();
					for (ParseObject reg: registros) {						
						lista.add(reg.getString(CAMPO_SERVICIO));
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_SERVICIOSCITA, lista);
					appMediador.sendBroadcast(AppMediador.AVISO_SERVICIOSCITA, extras);
					
				} else {
					
					appMediador.sendBroadcast(AppMediador.AVISO_SERVICIOSCITA, null);
					
				}
				
			}

		});
		return null;
	}

}
