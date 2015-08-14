package alc.appnaranja.vista;


import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorPresentacion;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PresentacionVistaActivity extends Activity implements IVistaPresentacion {
	private AppMediador appMediador;
	private IPresentadorPresentacion presentadorPresentacion;
	private TextView textoPresentacion;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaPresentacion(this);
		presentadorPresentacion = appMediador.getPresentadorPresentacion();
		setContentView(R.layout.principal_vista);
		textoPresentacion = (TextView) this.findViewById(R.id.descripcion_principal);
		presentadorPresentacion.mostrarVistaPresentacion();
		
	}



	@Override
	public void setTextoPresentacion(String texto) {
		textoPresentacion.setText(texto);	
	}


	

}
