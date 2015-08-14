package alc.appnaranja.vista;


import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorMisCitasDetalle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MisCitasVistaDetalleActivity extends Activity implements IVistaMisCitasDetalle, OnClickListener {

	
	private AppMediador appMediador;
	private IPresentadorMisCitasDetalle presentadorMisCitasDetalle;
	private TextView textoCita;
	private Button cancelar;
	private ProgressDialog barra;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mis_citas_detalle_vista);
		appMediador = AppMediador.getInstance();
		presentadorMisCitasDetalle=appMediador.getPresentadorMisCitasDetalle();
		appMediador.setVistaMisCitasDetalle(this);
		
		textoCita = (TextView) findViewById(R.id.mi_cita);
		cancelar = (Button) this.findViewById(R.id.cancelar_reserva);
		cancelar.setOnClickListener(this);

		presentadorMisCitasDetalle.mostrarVistaMisCitasDetalle();
	}
	


	@Override
	public void setTextoMiCita(String texto) {
		textoCita.setText(texto);
		
	}



	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.cancelar_reserva:
			mostrarAlerta("¿Seguro que quieres cancelar la cita?");
			break;
		}

	}
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		appMediador.removePresentadorMisCitasDetalle();
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
	public void mostrarAlerta(String titulo) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(titulo);
		
		builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				presentadorMisCitasDetalle.cancelarCita();
				
		    }
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
		    }
		});
		builder.create();
		builder.show();
	}


	
}


