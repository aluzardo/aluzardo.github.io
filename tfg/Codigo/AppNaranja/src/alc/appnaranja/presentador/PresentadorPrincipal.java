package alc.appnaranja.presentador;

import java.util.Locale;

import alc.appnaranja.AppMediador;
import alc.appnaranja.modelo.Modelo;
import alc.appnaranja.modelo.Peluquerias;
import alc.appnaranja.modelo.Servicios;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.preference.PreferenceManager;



public class PresentadorPrincipal implements IPresentadorPrincipal {
	
	public PresentadorPrincipal() {
		AppMediador.getInstance().setModelo(Modelo.getInstance());
	}
	

	@Override
	public void lanzarAyuda() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaAyuda(), appMediador.getVistaPrincipal(), null);
		
	}
	@Override
	public void lanzarInfo() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaInfo(), appMediador.getVistaPrincipal(), null);
		
	}
	
	@Override
	public void lanzarPresentacion() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaPresentacion(), appMediador.getVistaPrincipal(), null);
		
	}
	@Override
	public void lanzarDondeEstamos() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaDondeEstamos(), appMediador.getVistaPrincipal(), null);
	}
	@Override
	public void lanzarMisCitas() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaMisCitasMaestro(), appMediador.getVistaPrincipal(), null);
	}
	@Override
	public void lanzarPedirCita() {
		//Obtengo los servicios en background y los guardo en el modelo porque son necesarios para pedir cita
		Peluquerias.obtenerTodasPeluquerias();
		Servicios.obtenerTodosServicios();
		
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaPedirCita1(), appMediador.getVistaPrincipal(), null);
		
	}
	//¿¿Cómo puedo hacer este método utilizando el APPMEDIADOR??
	@Override
	public void lanzarURL(String url, Activity a) {
		 Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		 i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		a.startActivity(Intent.createChooser(i, "Seleccione la aplicacion deseada"));
		
	}

	@Override
	public void lanzarConfiguracion() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaConfiguracion(), appMediador.getVistaPrincipal(), null);

	}
	@Override
	public void actualizaPreferencias() {
		SharedPreferences ajustes = PreferenceManager.getDefaultSharedPreferences(
				AppMediador.getInstance().getApplicationContext());
		String idioma = ajustes.getString("idioma", "NULL");
		Locale locale;
		if (idioma.equals("EN"))	
			locale = new Locale("en_EN");
		else
			locale = new Locale("es_ES");
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		AppMediador.getInstance().getApplicationContext().getResources().updateConfiguration(config, null);		
	}

}
