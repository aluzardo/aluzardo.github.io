package alc.appnaranja.presentador;
import alc.appnaranja.AppMediador;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PresentadorMisCitasDetalle implements IPresentadorMisCitasDetalle {

	private String infoCita;
	private BroadcastReceiver receptorDatosCita = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			if (intent.getAction().equals(AppMediador.AVISO_CITA)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					infoCita = (String)extras.getSerializable(AppMediador.DATOS_CITA);	
					AppMediador.getInstance().getVistaMisCitasDetalle().setTextoMiCita(infoCita);
					AppMediador.getInstance().getVistaMisCitasDetalle().eliminarProgreso();
					
				}else{
					AppMediador.getInstance().getVistaMisCitasDetalle().eliminarProgreso();
				}
				appMediador.unRegisterReceiver(this);
			}
			
			if (intent.getAction().equals(AppMediador.AVISO_ELIMINACION)) {
				AppMediador.getInstance().getVistaMisCitasDetalle().eliminarProgreso();
				lanzarMisCitasMaestro();
				appMediador.unRegisterReceiver(this);
			}

		}
	};

	@Override
	public void mostrarVistaMisCitasDetalle() {
		presentarInfoCita();
		
	}

	@Override
	public void cargarInfoCita() {
		
		AppMediador.getInstance().getModelo().obtenerInfoCita(AppMediador.getInstance().getModelo().getCitaSeleccionada());
		AppMediador.getInstance().getVistaMisCitasDetalle().mostrarProgreso("Cargando info de cita");
		AppMediador.getInstance().registerReceiver(receptorDatosCita, new String[]{AppMediador.AVISO_CITA});
		
		
	}

	@Override
	public void presentarInfoCita() {
		cargarInfoCita();
		AppMediador.getInstance().getVistaMisCitasDetalle().setTextoMiCita(infoCita);
		
	}

	@Override
	public void cancelarCita() {
		AppMediador.getInstance().getVistaMisCitasDetalle().mostrarProgreso("Eliminando cita de la base de datos externa...");
		AppMediador.getInstance().registerReceiver(receptorDatosCita, new String[]{AppMediador.AVISO_ELIMINACION});
		AppMediador.getInstance().getModelo().cancelarCita();

	}

	@Override
	public void lanzarMisCitasMaestro() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaMisCitasMaestro(), appMediador.getVistaMisCitasDetalle(), null);
		
	}
}


