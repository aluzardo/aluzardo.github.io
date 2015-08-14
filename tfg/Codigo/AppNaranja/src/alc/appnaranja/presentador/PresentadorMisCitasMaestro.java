package alc.appnaranja.presentador;

import java.util.ArrayList;

import alc.appnaranja.AppMediador;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class PresentadorMisCitasMaestro implements IPresentadorMisCitasMaestro {

	private static ArrayList<String> listaCitas = new ArrayList<String>();
	private static BroadcastReceiver receptorDatosCitas = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			if (intent.getAction().equals(AppMediador.AVISO_LISTA_CITAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					listaCitas = (ArrayList<String>)extras.getSerializable(AppMediador.DATOS_LISTA_CITAS);	
					AppMediador.getInstance().getVistaMisCitasMaestro().setListaCitas(listaCitas);
					AppMediador.getInstance().getVistaMisCitasMaestro().eliminarProgreso();
					
				}else{
					AppMediador.getInstance().getVistaMisCitasMaestro().eliminarProgreso();
					AppMediador.getInstance().getVistaMisCitasMaestro().mostrarAlerta("No hemos encontrado citas con ese numero de telefono, introduce otro:");
				}
			}

				appMediador.unRegisterReceiver(this);
			

		}
	};

	@Override
	public void mostrarVistaMisCitasMaestro() {
		if(AppMediador.getInstance().getModelo().getTelefono() == null){
			AppMediador.getInstance().getVistaMisCitasMaestro().mostrarAlerta("Introduce tu nº de teléfono:");
		}else{
			cargarListaCitas();
		}
		
	}

	@Override
	public void cargarListaCitas() {
		
		if(AppMediador.getInstance().getVistaMisCitasMaestro().getTelefono() != null){
			String telefono = AppMediador.getInstance().getVistaMisCitasMaestro().getTelefono();
			AppMediador.getInstance().getModelo().setTelefono(telefono);
		}
		AppMediador.getInstance().getVistaMisCitasMaestro().mostrarProgreso("Cargando lista de citas");
		AppMediador.getInstance().registerReceiver(receptorDatosCitas, new String[]{AppMediador.AVISO_LISTA_CITAS});
		AppMediador.getInstance().getModelo().obtenerListaCitas(AppMediador.getInstance().getModelo().getTelefono());
	

	}

	@Override
	public void presentarListaCitas() {
		AppMediador.getInstance().getVistaMisCitasMaestro().setListaCitas(listaCitas);
	}

	@Override
	public void lanzarMisCitasDetalle() {
		AppMediador.getInstance().getModelo().setCitaSeleccionada(AppMediador.getInstance().getVistaMisCitasMaestro().getCitaSeleccionada());
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaMisCitasDetalle(),
				appMediador.getVistaMisCitasMaestro(), null);

	}
	
	@Override
	public void lanzarPrincipal() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaPrincipal(),
				appMediador.getVistaMisCitasMaestro(), null);

	}

}
