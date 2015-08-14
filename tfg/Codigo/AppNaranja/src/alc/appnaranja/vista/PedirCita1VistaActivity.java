package alc.appnaranja.vista;


import java.util.ArrayList;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorPedirCita1;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class PedirCita1VistaActivity extends Activity implements IVistaPedirCita1, OnClickListener {
	private AppMediador appMediador;
	private IPresentadorPedirCita1 presentadorPedirCita1;
	private Button siguiente;
	private EditText nombreEditable;
	private EditText telefono;
	private Spinner peluquerias;
	private String peluqueriaSeleccionada;
	private boolean hombre, mujer;
	private ProgressDialog barra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaPedirCita1(this);
		presentadorPedirCita1 = appMediador.getPresentadorPedirCita1();
		
		setContentView(R.layout.cita_nombre_vista);
		
		nombreEditable = (EditText) this.findViewById(R.id.nombre);
		telefono = (EditText) this.findViewById(R.id.telefono);

		peluquerias = (Spinner) this.findViewById(R.id.peluquerias);
		
		siguiente = (Button) this.findViewById(R.id.siguiente);
		siguiente.setOnClickListener(this);
		
		
		
	}
	@Override
	protected void onStart(){
		super.onStart();
		appMediador.getPresentadorPedirCita1().mostrarVistaPedirCita1();

	}
	

	
	public void onRadioButtonClicked(View view) {
	    
	    boolean checked = ((RadioButton) view).isChecked();
	    
	
	    switch(view.getId()) {
	        case R.id.hombre:
	            if (checked)
	            	mujer=false;
	                hombre=true;
	            break;
	        case R.id.mujer:
	            if (checked)
	            	hombre=false;
	                mujer=true;
	            break;
	    }
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.siguiente:
			presentadorPedirCita1.lanzarPedirCita2();
			break;
		}
		
	}
	@Override
	public String getTextoNombre() {
		return nombreEditable.getText().toString();
	}
	@Override
	public String getTextoTelefono() {
		return telefono.getText().toString();
	}
	@Override
	public boolean getEstadoBotonHombre() {
		return hombre;
	}
	@Override
	public boolean getEstadoBotonMujer() {
		return mujer;
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
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				peluqueriaSeleccionada = arg0.getItemAtPosition(0).toString();

			}
    		
    		
		});
		
	}
	@Override
	public String getPeluqueriaSeleccionada() {
		return peluqueriaSeleccionada;
	}
	
	@Override
	public void mostrarAlerta(String titulo) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(titulo);
		EditText telefono = new EditText(this);
		builder.setView(telefono);
		
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
