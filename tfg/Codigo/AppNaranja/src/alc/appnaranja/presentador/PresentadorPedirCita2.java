package alc.appnaranja.presentador;

import java.util.ArrayList;

import alc.appnaranja.AppMediador;
import alc.appnaranja.vista.IVistaPedirCita2;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class PresentadorPedirCita2 implements IPresentadorPedirCita2 {
	private ArrayList<String> servicios;
	private String precioTotal;
	private BroadcastReceiver receptorServicios = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			if (intent.getAction().equals(AppMediador.AVISO_SERVICIOS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					servicios  = (ArrayList<String>)extras.getSerializable(AppMediador.DATOS_SERVICIOS);	
					presentarServicios();
					cambiaPrecioVista();
					AppMediador.getInstance().getVistaPedirCita2().eliminarProgreso();

				}else{
					AppMediador.getInstance().getVistaPedirCita2().eliminarProgreso();
					AppMediador.getInstance().getVistaPedirCita2().mostrarAlerta("Error en el servidor");
				}

			}

				appMediador.unRegisterReceiver(this);
			

		}
	};

	@Override
	public void mostrarVistaPedirCita2() {
		cargarServicios();
		

		
	}

	@Override
	public void cargarServicios() {
		AppMediador.getInstance().getModelo().obtenerServicios();
		AppMediador.getInstance().getVistaPedirCita2().mostrarProgreso("Cargando servicios...");
		AppMediador.getInstance().registerReceiver(receptorServicios, new String[]{AppMediador.AVISO_SERVICIOS});

	}

	@Override
	public void presentarServicios() {

		String precioCorteDeFlecos = servicios.get(0);
		String precioCorteDePelo = servicios.get(1);
		String precioManicura = servicios.get(2);
		String precioMechas =servicios.get(3);
		String precioPedicura = servicios.get(4);
		String precioTinte = servicios.get(5);
		
		IVistaPedirCita2 vistaPedirCita2 = AppMediador.getInstance().getVistaPedirCita2();
		
		vistaPedirCita2.setPrecioCorteDeFlecos(precioCorteDeFlecos);
		vistaPedirCita2.setPrecioCorteDePelo(precioCorteDePelo);
		vistaPedirCita2.setPrecioManicura(precioManicura);
		vistaPedirCita2.setPrecioMechas(precioMechas);
		vistaPedirCita2.setPrecioPedicura(precioPedicura);
		vistaPedirCita2.setPrecioTinte(precioTinte);
	}

	@Override
	public void calculaPrecioTotal() {
		
		if(getServiciosSeleccionados().size() == 0){
			precioTotal = "Total: 0 €";
		} else{
			precioTotal = AppMediador.getInstance().getModelo().calculaPrecioTotal(getServiciosSeleccionados());
		}
		
		
	}

	@Override
	public void cambiaPrecioVista() {
		calculaPrecioTotal();
		AppMediador.getInstance().getVistaPedirCita2().setPrecioTotal(precioTotal);
		
	}

	@Override
	public void guardarDatos() {
		AppMediador.getInstance().getModelo().guardarDatos(getServiciosSeleccionados());
		
	}

	@Override
	public void lanzarPedirCita3() {
		guardarDatos();
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaPedirCita3(), appMediador.getVistaPedirCita2(), null);
	}

	private ArrayList<String> getServiciosSeleccionados(){
		IVistaPedirCita2 vistaPedirCita2 = AppMediador.getInstance().getVistaPedirCita2();
		ArrayList<String> serviciosSeleccionados = new ArrayList<String>();

		if(vistaPedirCita2.getEstadoCorteDeFlecos()){
			serviciosSeleccionados.add(servicios.get(0));

		}
		if(vistaPedirCita2.getEstadoCorteDePelo()){
			serviciosSeleccionados.add(servicios.get(1));
		
		}
		if(vistaPedirCita2.getEstadoManicura()){
			serviciosSeleccionados.add(servicios.get(2));
		
		}
		if(vistaPedirCita2.getEstadoMechas()){
			serviciosSeleccionados.add(servicios.get(3));
		
		}
		
		if(vistaPedirCita2.getEstadoPedicura()){
			serviciosSeleccionados.add(servicios.get(4));
		
		}
		
		if(vistaPedirCita2.getEstadoTinte()){
			serviciosSeleccionados.add(servicios.get(5));
		
		}
		
		return serviciosSeleccionados;
	}

	
	

}
