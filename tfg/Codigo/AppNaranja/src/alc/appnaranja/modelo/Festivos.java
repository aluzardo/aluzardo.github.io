package alc.appnaranja.modelo;

import java.util.ArrayList;
import java.util.List;

import alc.appnaranja.AppMediador;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Festivos {
	
	public static final String nombreTabla = "festivos";
	public static final String CAMPO_IDENTIFICADOR = "id_horario";
	public static final String CAMPO_PELUQUERIA = "id_peluqueria";
	public static final String CAMPO_FECHA = "fecha";
	
	
	@SuppressWarnings("unchecked")
	public static Object[] obtenerTodosFestivos(String id_peluqueria) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_PELUQUERIA, id_peluqueria);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				AppMediador appMediador = AppMediador.getInstance();
				if (e == null) {
					ArrayList<String> lista = new ArrayList<String>();
					for (ParseObject reg: registros) {						
						lista.add(reg.getString(CAMPO_FECHA));
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_FESTIVOS, lista);
					appMediador.sendBroadcast(AppMediador.AVISO_FESTIVOS, extras);
					
				} else {
					
					appMediador.sendBroadcast(AppMediador.AVISO_FESTIVOS, null);
					
				}
				
			}

		});
		return null;
	}

}
