package alc.appnaranja.presentador;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import alc.appnaranja.AppMediador;
import alc.appnaranja.modelo.DatosPeluqueria;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PresentadorMapa implements IPresentadorMapa {

	private GoogleMap mapa;
	private ArrayList<DatosPeluqueria> peluquerias = new ArrayList<DatosPeluqueria>();
	private BroadcastReceiver receptorDatosPeluquerias = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			if (intent.getAction().equals(AppMediador.AVISO_PELUQUERIAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					peluquerias = (ArrayList<DatosPeluqueria>)extras.getSerializable(AppMediador.DATOS_PELUQUERIAS);	
					presentarMapa();
					appMediador.getVistaMapa().eliminarProgreso();
					
				}else{
					appMediador.getVistaMapa().eliminarProgreso();
					appMediador.getVistaMapa().mostrarAlerta("No ha sido posible cargar la lista de peluquerías vuelva a intentarlo");
				}
			}

		}
	};
	

	@Override
	public void mostrarVistaMapa() {
		AppMediador.getInstance().getModelo().obtenerDatosPeluquerias();
		AppMediador.getInstance().getVistaMapa().mostrarProgreso("Cargando datos de peluquerías...");
		AppMediador.getInstance().registerReceiver(receptorDatosPeluquerias, new String[]{AppMediador.AVISO_PELUQUERIAS});
	
	}

	@Override
	public void presentarMapaTodasPeluquerias() {
		AppMediador.getInstance().getVistaMapa().setTextoMapa("Se muestran todas las peluquerías");
		mapa = (GoogleMap) AppMediador.getInstance().getVistaMapa().getMapa();
		
		// muevo el mapa a donde quiero
		CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(AppMediador.getInstance().getModelo().posicionPeluqueria("Triana"), 7);
		mapa.moveCamera(camUpd1);
				
	}

	@Override
	public void presentarMapa() {
		String peluqueriaSeleccionada = AppMediador.getInstance().getModelo().getPeluqueriaSeleccionada();
		mapa = (GoogleMap) AppMediador.getInstance().getVistaMapa().getMapa();
		AppMediador.getInstance().getVistaMapa().setTextoMapa("Peluquería Naranja de " + peluqueriaSeleccionada);
		// elijo el tipo de mapa
		mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		// pongo en el mapa la posición gps
		mapa.setMyLocationEnabled(true);
		
		// añado un marcador para todas las peluquerias
		for(int i = 0;i<peluquerias.size();i++){
			if(peluquerias.get(i).getId_peluqueria().equalsIgnoreCase(peluqueriaSeleccionada)){
				mapa.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).position(
						peluquerias.get(i).getLocalizacion()).snippet(peluquerias.get(i).getDescripcion()).title(
						"Peluquería Naranja: " + peluquerias.get(i).getId_peluqueria())).showInfoWindow();

			}else{
				mapa.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).position(
						peluquerias.get(i).getLocalizacion()).snippet(peluquerias.get(i).getDescripcion()).title(
						"Peluquería Naranja: " + peluquerias.get(i).getId_peluqueria()));
			
			}
				
			
		}
		
		
		// muevo el mapa a donde quiero
		
		CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(AppMediador.getInstance().getModelo().posicionPeluqueria(peluqueriaSeleccionada), 15);
		mapa.moveCamera(camUpd1);
	
	}

	@Override
	public void presentarMapaPeluqueriaCercana() {
		
		mapa = (GoogleMap) AppMediador.getInstance().getVistaMapa().getMapa();
		if(mapa.getMyLocation()!= null){
			
		
		LatLng miPosicion = new LatLng(mapa.getMyLocation().getLatitude(), mapa.getMyLocation().getLongitude());
		
		double menorDistancia =0;
		int peluqueriaCercana =0;
		
		for(int i = 0;i<AppMediador.getInstance().getModelo().posicionTodasPeluquerias().size();i++){
			if(menorDistancia == 0){
				menorDistancia = distanciaEntre(miPosicion, AppMediador.getInstance().getModelo().posicionTodasPeluquerias().get(i));
			}
			
			if(distanciaEntre(miPosicion, AppMediador.getInstance().getModelo().posicionTodasPeluquerias().get(i))< menorDistancia){
				menorDistancia = distanciaEntre(miPosicion, AppMediador.getInstance().getModelo().posicionTodasPeluquerias().get(i));
				peluqueriaCercana = i;
			}
			
		}
		
		 DecimalFormat df = new DecimalFormat("#.##");
		AppMediador.getInstance().getVistaMapa().setTextoMapa("La Peluquería más cercana está en " + peluquerias.get(peluqueriaCercana).getId_peluqueria()+ " a " + df.format(menorDistancia)+ "kilometros");

			CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(AppMediador.getInstance().getModelo().posicionTodasPeluquerias().get(peluqueriaCercana), 15);
			mapa.moveCamera(camUpd1);
		}else{
			AppMediador.getInstance().getVistaMapa().mostrarAlerta("No hemos podido calcular tu posición... Activa el GPS y vuelve a intentarlo");
		}

	}
	
	public static double distanciaEntre(LatLng desde, LatLng hasta) {  

		double lat1 = desde.latitude;
		double lng1 = desde.longitude;
		double lat2 = hasta.latitude;
		double lng2 = hasta.longitude;
		double earthRadius = 6371; // kilmetros
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
				* Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		return dist;
	  } 
	
	

	
	

}
