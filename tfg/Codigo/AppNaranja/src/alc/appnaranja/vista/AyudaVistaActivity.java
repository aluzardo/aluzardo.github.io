package alc.appnaranja.vista;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorAyuda;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AyudaVistaActivity extends Activity implements IVistaAyuda {
	private AppMediador appMediador;
	private IPresentadorAyuda presentadorAyuda;
	private TextView resultado;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaAyuda(this);
		presentadorAyuda = appMediador.getPresentadorAyuda();
		setContentView(R.layout.solo_texto_vista);
		resultado = (TextView) this.findViewById(R.id.mi_cita);
		resultado.setText(R.string.menu_ayuda);
		
		presentadorAyuda.mostrarVistaAyuda();


	}

	

}
