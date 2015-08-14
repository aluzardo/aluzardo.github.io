package alc.appnaranja.vista;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorConfirmarCita;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarCitaVistaActivity extends Activity implements IVistaConfirmarCita, OnClickListener {
	private AppMediador appMediador;
	private IPresentadorConfirmarCita presentadorConfirmarCita;
	private Button confirmar;
	private TextView texto;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaConfirmarCita(this);
		presentadorConfirmarCita = appMediador.getPresentadorConfirmarCita();
		
		setContentView(R.layout.confirmar_cita_vista);
		
		texto = (TextView) this.findViewById(R.id.mi_cita);
		
		confirmar = (Button) this.findViewById(R.id.confirmar_reserva);
		confirmar.setOnClickListener(this);
		
		
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		presentadorConfirmarCita.mostrarVistaConfirmarCita();

	}



	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.confirmar_reserva:
			presentadorConfirmarCita.confirmarCita();
			presentadorConfirmarCita.lanzarPrincipal();
			break;
		}
		
	}



	@Override
	public void setTextoConfirmarCita(String texto) {
		this.texto.setText(texto);
		
	}

}
