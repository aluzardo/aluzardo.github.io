package alc.appnaranja.vista;

import java.util.Locale;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorPrincipal;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PrincipalVistaActivity extends Activity implements IVistaPrincipal, OnClickListener {
	private AppMediador appMediador;
	
	private IPresentadorPrincipal presentadorPrincipal;
	private Button presentacion;
	private Button dondeEstamos;
	private Button pedirCita;
	private Button misCitas;
	@SuppressWarnings("unused")
	private ImageView logo;
	private ImageButton patrocinadores;
	private ImageButton facebook;
	private ImageButton twitter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
				
	}
	@Override
	protected void onStart(){
		super.onStart();
		SharedPreferences ajustes = PreferenceManager.getDefaultSharedPreferences(
				AppMediador.getInstance().getApplicationContext());
		String idioma = ajustes.getString("idioma", "NULL");
		Locale locale;
		if (idioma.equals("EN"))	
			locale = new Locale("en");
		else
			locale = new Locale("es");
		
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		AppMediador.getInstance().getApplicationContext().getResources().updateConfiguration(config, null);
	
		setContentView(R.layout.activity_principal_vista);
		appMediador = (AppMediador) getApplication();
		appMediador.setVistaPrincipal(this);	
		presentacion = (Button) findViewById(R.id.presentacion);
	    dondeEstamos = (Button) findViewById(R.id.donde_estamos);
	    pedirCita = (Button) findViewById(R.id.pedir_cita);
	    misCitas = (Button) findViewById(R.id.mis_citas);
	    presentacion.setOnClickListener(this);
	    dondeEstamos.setOnClickListener(this);
	    pedirCita.setOnClickListener(this);
	    misCitas.setOnClickListener(this);
	    logo = (ImageView) this.findViewById(R.id.logo);
	    patrocinadores= (ImageButton) this.findViewById(R.id.patrocinadores);
	    facebook = (ImageButton) this.findViewById(R.id.facebook);
	    twitter = (ImageButton) this.findViewById(R.id.twitter);
	    patrocinadores.setOnClickListener(this);
	    twitter.setOnClickListener(this);
	    facebook.setOnClickListener(this);
	    presentadorPrincipal = appMediador.getPresentadorPrincipal();


		    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_vista, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()){
		case R.id.menu_ayuda:
			presentadorPrincipal.lanzarAyuda();
			break;
		case R.id.menu_configuracion:
			presentadorPrincipal.lanzarConfiguracion();
			break;
		case R.id.menu_info:
			presentadorPrincipal.lanzarInfo();
			break;
		case R.id.menu_salir:
			appMediador.setModelo(null);
			appMediador.removePresentadorPrincipal();
			int id = android.os.Process.myPid();
			android.os.Process.killProcess(id);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.presentacion:
			presentadorPrincipal.lanzarPresentacion();
			break;
		case R.id.donde_estamos:
			presentadorPrincipal.lanzarDondeEstamos();
			break;
		case R.id.mis_citas:
			presentadorPrincipal.lanzarMisCitas();
			finish();
			break;
		case R.id.pedir_cita:
			presentadorPrincipal.lanzarPedirCita();
			break;	
		case R.id.patrocinadores:
			presentadorPrincipal.lanzarURL("http://cortedepelo5euros.com/index.asp", this);
			break;
		case R.id.facebook:
			presentadorPrincipal.lanzarURL("https://www.facebook.com/peluquerianaranja.naranja", this);
			break;
		case R.id.twitter:
			presentadorPrincipal.lanzarURL("https://twitter.com/peluquerianara", this);
			break;
		case R.id.logo:
			
			break;
		}	
	}
	
	
}
