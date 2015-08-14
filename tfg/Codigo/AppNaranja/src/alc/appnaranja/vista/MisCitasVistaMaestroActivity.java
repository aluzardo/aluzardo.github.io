package alc.appnaranja.vista;

import java.util.ArrayList;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorMisCitasMaestro;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MisCitasVistaMaestroActivity extends Activity implements IVistaMisCitasMaestro, OnItemClickListener {
	private AppMediador appMediador;
	private IPresentadorMisCitasMaestro presentadorMisCitasMaestro;
	private ListView listaCitas;
	private String citaSeleccionada;
	private ProgressDialog barra;
	private String numero_telefono;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mis_citas_maestro_vista);
		appMediador = AppMediador.getInstance();
		presentadorMisCitasMaestro = appMediador.getPresentadorMisCitasMaestro();
		appMediador.setVistaMisCitasMaestro(this);
		listaCitas = (ListView)findViewById(R.id.lista_citas);
		
		listaCitas.setOnItemClickListener(this);

	}
	
	@Override
	protected void onStart(){
		super.onStart();
		presentadorMisCitasMaestro.mostrarVistaMisCitasMaestro();

	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int posicion, long arg3) {
		citaSeleccionada = arg0.getItemAtPosition(posicion).toString();
		presentadorMisCitasMaestro.lanzarMisCitasDetalle();
		
	}


	@Override
	public String getCitaSeleccionada() {
		return citaSeleccionada;
	}
	
	@Override
	public String getTelefono() {
		return numero_telefono;
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public void setListaCitas(Object lista) {
		// crea un adaptador
				ArrayAdapter<String> adaptador = null;
				if (lista != null) {
					adaptador = new ArrayAdapter<String>(this,R.layout.list_item);
					for (int i = 0; i < ((ArrayList<String>) lista).size(); i++) {
						adaptador.add(((ArrayList<String>) lista).get(i));
					}
					
				} else {
					adaptador = new ArrayAdapter<String>(this,R.layout.list_item);
				}
				listaCitas.setAdapter(adaptador);
		
	}
	@Override
	public void mostrarAlerta(String titulo) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(titulo);
		final EditText telefono = new EditText(this);
		telefono.setInputType(InputType.TYPE_CLASS_PHONE);
		builder.setView(telefono);
		
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				numero_telefono = telefono.getText().toString();
				presentadorMisCitasMaestro.cargarListaCitas();
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
	

	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		appMediador.removePresentadorMisCitasMaestro();
		presentadorMisCitasMaestro.lanzarPrincipal();
		
	}

}

