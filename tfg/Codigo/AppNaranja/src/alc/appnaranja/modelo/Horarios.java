package alc.appnaranja.modelo;

import java.util.ArrayList;
import java.util.List;

import alc.appnaranja.AppMediador;
import android.os.Bundle;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Horarios {
	
	public static final String nombreTabla = "horarios";
	public static final String CAMPO_IDENTIFICADOR = "id_horario";
	public static final String CAMPO_PELUQUERIA = "id_peluqueria";
	public static final String CAMPO_HORA = "hora";
	public static final String CAMPO_FECHA = "fecha";
	
	@SuppressWarnings("unchecked")
	public static Object[] obtenerHorarios(String id_peluqueria) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_PELUQUERIA, id_peluqueria);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				AppMediador appMediador = AppMediador.getInstance();
				if (e == null) {
					ArrayList<DatosHorario> lista = new ArrayList<DatosHorario>();
					for (ParseObject reg: registros) {						
						lista.add(new DatosHorario(reg.getString(CAMPO_IDENTIFICADOR), reg.getString(CAMPO_PELUQUERIA), reg.getString(CAMPO_FECHA), reg.getString(CAMPO_HORA)));
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_HORARIOS, lista);
					appMediador.sendBroadcast(AppMediador.AVISO_HORARIOS, extras);
					
				} else {
					
					appMediador.sendBroadcast(AppMediador.AVISO_HORARIOS, null);
					
				}
				
			}

		});
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Object obtenerHorario(String id_horario) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_IDENTIFICADOR, id_horario);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				AppMediador appMediador = AppMediador.getInstance();
				if (e == null) {
					DatosHorario horario =null;
					for (ParseObject reg: registros) {						
						horario = new DatosHorario(reg.getString(CAMPO_IDENTIFICADOR), reg.getString(CAMPO_PELUQUERIA), reg.getString(CAMPO_FECHA), reg.getString(CAMPO_HORA));
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_HORARIO, horario);
					appMediador.sendBroadcast(AppMediador.AVISO_HORARIO, extras);
					
				} else {
					
					appMediador.sendBroadcast(AppMediador.AVISO_HORARIO, null);
					
				}
				
			}

		});
		return null;
	}
	
	public static boolean insertarHorario(Object cita) {
		ParseObject po = new ParseObject(nombreTabla);
		po.put(CAMPO_IDENTIFICADOR, ((DatosHorario) cita).getId_horario());
		po.put(CAMPO_PELUQUERIA, ((DatosHorario) cita).getId_peluqueria());
		po.put(CAMPO_HORA, ((DatosHorario) cita).getHora());
		po.put(CAMPO_FECHA, ((DatosHorario) cita).getFecha());
		po.saveInBackground();
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean eliminarHorario(String id_horario) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_IDENTIFICADOR, id_horario);
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

}
