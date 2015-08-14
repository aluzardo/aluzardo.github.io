package alc.appnaranja.modelo;

import java.util.ArrayList;
import java.util.List;

import alc.appnaranja.AppMediador;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Peluquerias {
	
	public static final String nombreTabla = "peluquerias";
	public static final String CAMPO_IDENTIFICADOR = "id_peluqueria";
	public static final String CAMPO_DIRECCION = "direccion";
	public static final String CAMPO_TELEFONO = "telefono";
	public static final String CAMPO_DESCRIPCION = "descripcion";
	public static final String CAMPO_IMAGEN = "imagen";
	public static final String CAMPO_LOCALIZACION = "localizacion";

	@SuppressWarnings("unchecked")
	//Método no necesario se puede borrar porque cargo todas las peluquerias
	public static Object obtenerPeluqueria(String id_peluqueria) {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_IDENTIFICADOR, id_peluqueria);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				AppMediador appMediador = AppMediador.getInstance();
				if (e == null) {
					DatosPeluqueria datosPeluqueria = null;
					for (ParseObject reg: registros) {						
						try {
							datosPeluqueria = new DatosPeluqueria(reg.getString(CAMPO_IDENTIFICADOR), reg.getString(CAMPO_DIRECCION), reg.getString(CAMPO_TELEFONO), reg.getString(CAMPO_DESCRIPCION), reg.getParseFile(CAMPO_IMAGEN).getData(), new LatLng(reg.getParseGeoPoint(CAMPO_LOCALIZACION).getLatitude(), reg.getParseGeoPoint(CAMPO_LOCALIZACION).getLongitude()));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_PELUQUERIA, datosPeluqueria);
					appMediador.sendBroadcast(AppMediador.AVISO_PELUQUERIA, extras);
					
				} else {
					
					appMediador.sendBroadcast(AppMediador.AVISO_PELUQUERIA, null);
					
				}
				
			}

		});

		return null;
	}

	@SuppressWarnings("unchecked")
	public static Object[] obtenerTodasPeluquerias() {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.orderByAscending(CAMPO_IDENTIFICADOR);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				AppMediador appMediador = AppMediador.getInstance();
				if (e == null) {
					ArrayList<DatosPeluqueria> lista = new ArrayList<DatosPeluqueria>();
					for (ParseObject reg: registros) {						
						try {
							lista.add(new DatosPeluqueria(reg.getString(CAMPO_IDENTIFICADOR), reg.getString(CAMPO_DIRECCION), reg.getString(CAMPO_TELEFONO), reg.getString(CAMPO_DESCRIPCION), reg.getParseFile(CAMPO_IMAGEN).getData(), new LatLng(reg.getParseGeoPoint(CAMPO_LOCALIZACION).getLatitude(), reg.getParseGeoPoint(CAMPO_LOCALIZACION).getLongitude())));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_PELUQUERIAS, lista);
					appMediador.sendBroadcast(AppMediador.AVISO_PELUQUERIAS, extras);
					
				} else {
					
					appMediador.sendBroadcast(AppMediador.AVISO_PELUQUERIAS, null);
					
				}
				
			}

		});
		
		

		return null;
	}
	

	@SuppressWarnings("unchecked")
	public static Object[] obtenerListaPeluquerias() {
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.orderByAscending(CAMPO_IDENTIFICADOR);
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
					extras.putSerializable(AppMediador.DATOS_LISTA_PELUQUERIAS, lista);
					appMediador.sendBroadcast(AppMediador.AVISO_LISTA_PELUQUERIAS, extras);
					
				} else {
					
					appMediador.sendBroadcast(AppMediador.AVISO_LISTA_PELUQUERIAS, null);
					
				}
				
			}

		});
		
		

		return null;
	}

}
