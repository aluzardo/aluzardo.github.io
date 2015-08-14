package alc.appnaranja.presentador;

import java.util.ArrayList;

import alc.appnaranja.AppMediador;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PresentadorPedirCita1 implements IPresentadorPedirCita1 {
	private ArrayList<String> peluquerias;
	private BroadcastReceiver receptorPeluquerias = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			if (intent.getAction().equals(AppMediador.AVISO_LISTA_PELUQUERIAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					peluquerias = (ArrayList<String>)extras.getSerializable(AppMediador.DATOS_LISTA_PELUQUERIAS);	
					AppMediador.getInstance().getVistaPedirCita1().eliminarProgreso();
					presentarListaPeluquerias();
				}else{
					AppMediador.getInstance().getVistaPedirCita1().eliminarProgreso();
					AppMediador.getInstance().getVistaPedirCita1().mostrarAlerta("No ha sido posible cargar la lista de peluquerias vuelva a intentarlo");
				}
			}

				appMediador.unRegisterReceiver(this);
			

		}
	};
	
	
	@Override
	public void mostrarVistaPedirCita1() {
		cargarListaPeluquerias();
	}
	
	@Override
	public void cargarListaPeluquerias() {
		AppMediador.getInstance().getModelo().obtenerListaPeluquerias();
		AppMediador.getInstance().getVistaPedirCita1().mostrarProgreso("Cargando lista de peluquerias...");
		AppMediador.getInstance().registerReceiver(receptorPeluquerias, new String[]{AppMediador.AVISO_LISTA_PELUQUERIAS});

	}
	
	@Override
	public void presentarListaPeluquerias() {
		AppMediador.getInstance().getVistaPedirCita1().setListaPeluquerias(peluquerias);
		
		
	}
	

	@Override
	public void guardarDatos() {
		
		String nombre = AppMediador.getInstance().getVistaPedirCita1().getTextoNombre();
		String peluqueria = AppMediador.getInstance().getVistaPedirCita1().getPeluqueriaSeleccionada();
		String telefono = AppMediador.getInstance().getVistaPedirCita1().getTextoTelefono();
		String sexo = getSexo();
		AppMediador.getInstance().getModelo().setPeluqueriaSeleccionada(peluqueria);
		AppMediador.getInstance().getModelo().guardarDatos(nombre, telefono, sexo, peluqueria);
	}

	
	@Override
	public void lanzarPedirCita2() {
		guardarDatos();
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaPedirCita2(), appMediador.getVistaPedirCita1(), null);
	}
	
	private String getSexo(){
		if(AppMediador.getInstance().getVistaPedirCita1().getEstadoBotonHombre()){
			return "Hombre";
		}else {
			return "Mujer";
		}
	}
	
	

}
