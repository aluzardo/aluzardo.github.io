package alc.appnaranja.presentador;


import java.util.ArrayList;

import alc.appnaranja.AppMediador;
import alc.appnaranja.modelo.IModelo;
import alc.appnaranja.vista.IVistaDondeEstamos;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;



public class PresentadorDondeEstamos implements IPresentadorDondeEstamos {

	private ArrayList<String> peluquerias;
	private String descripcionPeluqueria;
	private Bitmap imagenPeluqueria;
	private BroadcastReceiver receptorDatosPeluquerias = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			if (intent.getAction().equals(AppMediador.AVISO_LISTA_PELUQUERIAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					peluquerias = (ArrayList<String>)extras.getSerializable(AppMediador.DATOS_LISTA_PELUQUERIAS);	
					presentarListaPeluquerias();
				}else{
					appMediador.getVistaDondeEstamos().eliminarProgreso();
					appMediador.getVistaDondeEstamos().mostrarAlerta("No ha sido posible cargar la lista de peluquerías vuelva a intentarlo");
				}
			}
			if (intent.getAction().equals(AppMediador.AVISO_PELUQUERIAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					appMediador.getVistaDondeEstamos().eliminarProgreso();
						
				}else{
					appMediador.getVistaDondeEstamos().eliminarProgreso();
					appMediador.getVistaDondeEstamos().mostrarAlerta("No ha sido posible cargar la lista de peluquerías vuelva a intentarlo");
				}
			} 

		}
	};
	

	@Override
	public void mostrarVistaDondeEstamos() {
		cargarListaPeluquerias();

	}
	
	@Override
	public void lanzarMapa() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.getModelo().setPeluqueriaSeleccionada(appMediador.getVistaDondeEstamos().getPeluqueriaSeleccionada());
		appMediador.launchActivity(appMediador.getVistaParaMapa(), appMediador.getVistaDondeEstamos(), null);
	  
	}
	@Override
	public void cargarListaPeluquerias() {
		AppMediador.getInstance().getModelo().obtenerDatosPeluquerias();
		AppMediador.getInstance().getModelo().obtenerListaPeluquerias();
		AppMediador.getInstance().getVistaDondeEstamos().mostrarProgreso("Cargando lista de peluquerías...");
		AppMediador.getInstance().registerReceiver(receptorDatosPeluquerias, new String[]{AppMediador.AVISO_LISTA_PELUQUERIAS, AppMediador.AVISO_PELUQUERIAS});
		
	
	}
	@Override
	public void presentarListaPeluquerias() {
		AppMediador.getInstance().getVistaDondeEstamos().setListaPeluquerias(peluquerias);
		
		
	}
	@Override
	public void cargarDatosPeluqueria() {
		AppMediador appMediador = AppMediador.getInstance();
		IVistaDondeEstamos vistaDondeEstamos = appMediador.getVistaDondeEstamos();
		IModelo modelo = appMediador.getModelo();
		String id_peluqueria = vistaDondeEstamos.getPeluqueriaSeleccionada();
		
		imagenPeluqueria = modelo.obtenerImagenPeluqueria(id_peluqueria);
		descripcionPeluqueria = modelo.obtenerDescripcionPeluqueria(id_peluqueria);
		
			
		
		
	}
	@Override
	public void presentarDatosPeluqueria() {
		cargarDatosPeluqueria();
		AppMediador.getInstance().getVistaDondeEstamos().setTextoDescripcionPeluqueria(descripcionPeluqueria);
		AppMediador.getInstance().getVistaDondeEstamos().setImagenPeluqueria(imagenPeluqueria);
		
	}
	

	
	

}
