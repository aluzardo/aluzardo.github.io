package alc.appnaranja.presentador;

import java.util.ArrayList;

import alc.appnaranja.AppMediador;
import alc.appnaranja.modelo.Horarios;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



public class PresentadorPedirCita3 implements IPresentadorPedirCita3 {
	private ArrayList<String> dias, horas;
	private BroadcastReceiver receptorCalendario = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			if (intent.getAction().equals(AppMediador.AVISO_HORARIOS)) {
				dias = AppMediador.getInstance().getModelo().obtenerFechas(AppMediador.getInstance().getModelo().getPeluqueriaSeleccionada());
				AppMediador.getInstance().getVistaPedirCita3().eliminarProgreso();
				presentarFechas();

			}

				appMediador.unRegisterReceiver(this);
			

		}
	};

	@Override
	public void mostrarVistaPedirCita3() {
		cargarFechas();

	}

	@Override
	public void cargarFechas() {
		
		Horarios.obtenerHorarios(AppMediador.getInstance().getModelo().getPeluqueriaSeleccionada());	
		AppMediador.getInstance().getVistaPedirCita3().mostrarProgreso("Cargando lista de peluquerias");
		AppMediador.getInstance().registerReceiver(receptorCalendario, new String[]{AppMediador.AVISO_HORARIOS});
		
			}

	@Override
	public void presentarFechas() {
		AppMediador.getInstance().getVistaPedirCita3().setDiasDisponibles(dias);
	}

	@Override
	public void cargarHoras() {
		String fechaSeleccionada = AppMediador.getInstance().getVistaPedirCita3().getDia();
		horas = AppMediador.getInstance().getModelo().obtenerHoras(fechaSeleccionada);
		
	}

	@Override
	public void presentarHoras() {
		cargarHoras();
		AppMediador.getInstance().getVistaPedirCita3().setHorasDisponibles(horas);
		
	}

	@Override
	public void guardarDatos() {
		String fechaSeleccionada = AppMediador.getInstance().getVistaPedirCita3().getDia();
		String horaSeleccionada = AppMediador.getInstance().getVistaPedirCita3().getHora();
		AppMediador.getInstance().getModelo().guardarDatos(fechaSeleccionada, horaSeleccionada);
	}

	@Override
	public void lanzarConfirmarCita() {
		guardarDatos();
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaConfirmarCita(), appMediador.getVistaPedirCita3(), null);
	}

}
