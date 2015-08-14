package alc.appnaranja.vista;

import java.util.ArrayList;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorPedirCita3;
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
import android.widget.Spinner;

public class PedirCita3VistaActivity extends Activity implements IVistaPedirCita3, OnClickListener {
	private AppMediador appMediador;
	private IPresentadorPedirCita3 presentadorPedirCita3;
	private Button siguiente;
	private Spinner dia, hora;
	private String diaSeleccionado, horaSeleccionada;
	private ProgressDialog barra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaPedirCita3(this);
		presentadorPedirCita3 = appMediador.getPresentadorPedirCita3();
		setContentView(R.layout.cita_fecha_vista);
		
		dia = (Spinner)this.findViewById(R.id.dias_disponibles);
		hora = (Spinner) this.findViewById(R.id.horas_disponibles);
		
		siguiente = (Button) this.findViewById(R.id.siguiente);
		siguiente.setOnClickListener(this);
		
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		appMediador.getPresentadorPedirCita3().mostrarVistaPedirCita3();

	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.siguiente:
			presentadorPedirCita3.lanzarConfirmarCita();
			break;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setDiasDisponibles(Object dias) {
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(appMediador.getApplicationContext(), R.layout.spinner_item );
    	adaptador.addAll((ArrayList<String>) dias);
    	adaptador.setDropDownViewResource(R.layout.spinner_item);
    	dia.setAdapter(adaptador);
    	dia.setSelection(0);
    	
    	dia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int posicion, long arg3) {
				diaSeleccionado = arg0.getItemAtPosition(posicion).toString();
				presentadorPedirCita3.presentarHoras();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				presentadorPedirCita3.presentarHoras();
			}
    		
    		
		});
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setHorasDisponibles(Object horas) {
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(appMediador.getApplicationContext(), R.layout.spinner_item );
		adaptador.addAll((ArrayList<String>) horas);
    	adaptador.setDropDownViewResource(R.layout.spinner_item);
    	hora.setAdapter(adaptador);
    	hora.setSelection(0);
    	
    	hora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int posicion, long arg3) {
				horaSeleccionada = arg0.getItemAtPosition(posicion).toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				horaSeleccionada = arg0.getItemAtPosition(0).toString();

			}
    		
    		
		});
		
	}

	@Override
	public String getDia() {
		return diaSeleccionado;
	}

	@Override
	public String getHora() {
		return horaSeleccionada;
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
