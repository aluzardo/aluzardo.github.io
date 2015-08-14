package alc.appnaranja.presentador;

import alc.appnaranja.AppMediador;



public class PresentadorConfirmarCita implements IPresentadorConfirmarCita {
	
	private String infoCita;

	@Override
	public void mostrarVistaConfirmarCita() {
		cargarInfoCita();
		presentarInfoCita();
		
	}

	@Override
	public void confirmarCita() {
		AppMediador.getInstance().getModelo().confirmarCita();
		
	}
	@Override
	public void cargarInfoCita() {
		
		infoCita = AppMediador.getInstance().getModelo().obtenerInfoCita();
		
	}
	@Override
	public void presentarInfoCita() {
		
		AppMediador.getInstance().getVistaConfirmarCita().setTextoConfirmarCita(infoCita);
		
	}
	@Override
	public void lanzarPrincipal() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaPrincipal(), appMediador.getVistaConfirmarCita(), null);
		
	}

	
	

}
