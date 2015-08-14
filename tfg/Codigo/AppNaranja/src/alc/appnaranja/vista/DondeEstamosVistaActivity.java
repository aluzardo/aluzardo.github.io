package alc.appnaranja.vista;


import java.util.ArrayList;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorDondeEstamos;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class DondeEstamosVistaActivity extends Activity implements IVistaDondeEstamos, OnClickListener {
	private AppMediador appMediador;
	private IPresentadorDondeEstamos presentadorDondeEstamos;
	private Spinner peluquerias;
	private ImageView imagenPeluqueria;
	private TextView descripcion;
	private Button mapa;
	private String peluqueriaSeleccionada;
	private ProgressDialog barra;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaDondeEstamos(this);
		presentadorDondeEstamos = appMediador.getPresentadorDondeEstamos();
		
		setContentView(R.layout.donde_estamos_vista);
		peluquerias = (Spinner) this.findViewById(R.id.peluquerias);
		imagenPeluqueria = (ImageView) this.findViewById(R.id.imagen_peluqueria);
		descripcion = (TextView) this.findViewById(R.id.descripcion_peluqueria);
		mapa = (Button) this.findViewById(R.id.ver_mapa);
		mapa.setOnClickListener(this);
		presentadorDondeEstamos.mostrarVistaDondeEstamos();

	}
	
	@Override
	protected void onStart(){
		super.onStart();

	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.ver_mapa:
			presentadorDondeEstamos.lanzarMapa();
			break;
		}
	}

	@Override
	public String getPeluqueriaSeleccionada() {
		return peluqueriaSeleccionada;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setListaPeluquerias(Object lista) {
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(appMediador.getApplicationContext(), R.layout.spinner_item );
		adaptador.addAll((ArrayList<String>) lista);
    	adaptador.setDropDownViewResource(R.layout.spinner_item);
    	peluquerias.setAdapter(adaptador);
    	peluquerias.setSelection(0);
    	peluquerias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int posicion, long arg3) {
				peluqueriaSeleccionada = arg0.getItemAtPosition(posicion).toString();
				presentadorDondeEstamos.presentarDatosPeluqueria();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				peluqueriaSeleccionada = arg0.getItemAtPosition(0).toString();
			}
		});
	}

	@Override
	public void setImagenPeluqueria(Object imagen) {		
		imagenPeluqueria.setImageBitmap((Bitmap) imagen);		
	}

	@Override
	public void setTextoDescripcionPeluqueria(String texto) {
		descripcion.setText(texto);	
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
