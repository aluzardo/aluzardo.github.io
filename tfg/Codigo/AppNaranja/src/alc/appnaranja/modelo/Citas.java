package alc.appnaranja.modelo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import alc.appnaranja.AppMediador;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Citas {
	
	public static final String nombreTabla = "citas";
	public static final String CAMPO_IDENTIFICADOR = "id_cita";
	public static final String CAMPO_HORARIO = "id_horario";
	public static final String CAMPO_NOMBRE = "nombre";
	public static final String CAMPO_TELEFONO = "telefono";
	public static final String CAMPO_SEXO = "sexo";


	public static boolean insertarCita(Object cita) {
		ParseObject po = new ParseObject(nombreTabla);
		po.put(CAMPO_IDENTIFICADOR, ((DatosCita) cita).getId_cita());
		po.put(CAMPO_HORARIO, ((DatosCita) cita).getId_horario());
		po.put(CAMPO_NOMBRE, ((DatosCita) cita).getNombre());
		po.put(CAMPO_TELEFONO, ((DatosCita) cita).getTelefono());
		po.put(CAMPO_SEXO, ((DatosCita) cita).getSexo());
		po.saveInBackground();
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean eliminarCita(String id_cita) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_IDENTIFICADOR, id_cita);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {
				if (e == null) {
					for (ParseObject reg: registros) {	
					reg.deleteInBackground(new DeleteCallback() {
						   public void done(ParseException e) {
							   AppMediador appMediador = AppMediador.getInstance();
							     if (e == null) {
							    	 appMediador.sendBroadcast(AppMediador.AVISO_ELIMINACION, null);
							     } else {
							  
							     }
							   }
							 });
					}	
				} 
				
			}

		});
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static Object obtenerListaCitas(String telefono) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_TELEFONO, telefono);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {
				AppMediador appMediador = AppMediador.getInstance();
				if (e == null) {
					ArrayList<String> lista = new ArrayList<String>();
					for (ParseObject reg: registros) {						
						lista.add(reg.getString(CAMPO_IDENTIFICADOR));
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_LISTA_CITAS, lista);
					appMediador.sendBroadcast(AppMediador.AVISO_LISTA_CITAS, extras);
					
				} else {
					appMediador.sendBroadcast(AppMediador.AVISO_LISTA_CITAS, null);

				}
				
			}

		});
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public static Object obtenerInfoCita(String identificador) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_IDENTIFICADOR, identificador);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {
				AppMediador appMediador = AppMediador.getInstance();
				if (e == null) {
					DatosCita datosCita = null;
					for (ParseObject reg: registros) {	
					datosCita = new DatosCita(reg.getString(CAMPO_IDENTIFICADOR), reg.getString(CAMPO_HORARIO), reg.getString(CAMPO_NOMBRE), reg.getString(CAMPO_TELEFONO), reg.getString(CAMPO_SEXO));
					}
										
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_CITA_MODELO, datosCita);
					appMediador.sendBroadcast(AppMediador.AVISO_CITA_MODELO, extras);
					
				} else {
					
					appMediador.sendBroadcast(AppMediador.AVISO_CITA_MODELO, null);

				}
				
			}

		});
		
		return null;
	}

}
