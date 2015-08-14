package alc.appnaranja.modelo;

import java.util.ArrayList;
import java.util.List;

import alc.appnaranja.AppMediador;
import android.os.Bundle;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Servicios {
	
	public static final String nombreTabla = "servicios";
	public static final String CAMPO_IDENTIFICADOR = "id_servicio";
	public static final String CAMPO_NOMBRE = "nombre";
	public static final String CAMPO_PRECIO = "precio";

	@SuppressWarnings("unchecked")
	public static Object[] obtenerTodosServicios() {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.orderByAscending(CAMPO_IDENTIFICADOR);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				AppMediador appMediador = AppMediador.getInstance();
				if (e == null) {
					ArrayList<DatosServicios> lista = new ArrayList<DatosServicios>();
					for (ParseObject reg: registros) {						
						lista.add(new DatosServicios(reg.getString(CAMPO_IDENTIFICADOR), reg.getString(CAMPO_NOMBRE), reg.getString(CAMPO_PRECIO)));
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_SERVICIOS_MODELO, lista);
					appMediador.sendBroadcast(AppMediador.AVISO_SERVICIOS_MODELO, extras);
					
				} else {
					
					appMediador.sendBroadcast(AppMediador.AVISO_SERVICIOS_MODELO, null);
					
				}
				
			}

		});
		return null;
	}

}
