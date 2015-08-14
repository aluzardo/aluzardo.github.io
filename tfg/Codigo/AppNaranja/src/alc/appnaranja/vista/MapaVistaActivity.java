package alc.appnaranja.vista;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorMapa;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MapaVistaActivity extends android.support.v4.app.FragmentActivity implements IVistaMapa, OnClickListener {
	private AppMediador appMediador;
	private IPresentadorMapa presentadorMapa;
	private TextView textoMapa;
	private GoogleMap mapa;
	private Button verTodasPeluquerias;
	private Button verPeluqueriaCercana;
	private ProgressDialog barra;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaMapa(this);
		setContentView(R.layout.mapa_vista);
		textoMapa = (TextView) this.findViewById(R.id.texto_mapa);
		verTodasPeluquerias = (Button) this.findViewById(R.id.ver_mapa);
		verTodasPeluquerias.setOnClickListener(this);
		verPeluqueriaCercana = (Button) this.findViewById(R.id.ver_cercana);
		verPeluqueriaCercana.setOnClickListener(this);
		mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMap();
		
	}
	@Override
	protected void onStart(){
		super.onStart();
		presentadorMapa = appMediador.getPresentadorMapa();
		presentadorMapa.mostrarVistaMapa();

	}



	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.ver_mapa:
			presentadorMapa.presentarMapaTodasPeluquerias();
			break;
		case R.id.ver_cercana:
			presentadorMapa.presentarMapaPeluqueriaCercana();
			break;
		}
		
	}



	@Override
	public void setTextoMapa(String texto) {
		textoMapa.setText(texto);
		
	}




	@Override
	public Object getMapa() {
		return mapa;
	}
	
	@Override
	public void mostrarAlerta(String titulo) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(titulo);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				
		    }
		});
		builder.create();
		builder.show();
	}

	@Override
	public void mostrarProgreso(String mensaje) {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(mensaje);
		barra.show();
	}
	
	@Override
	public void eliminarProgreso() {
		barra.dismiss();
	}


	

	

}
