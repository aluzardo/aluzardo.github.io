package alc.appnaranja.vista;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorInfo;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoVistaActivity extends Activity implements IVistaInfo {
	private AppMediador appMediador;
	private IPresentadorInfo presentadorInfo;
	private TextView resultado;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaInfo(this);
		presentadorInfo = appMediador.getPresentadorInfo();
		
		setContentView(R.layout.solo_texto_vista);
		resultado = (TextView) this.findViewById(R.id.mi_cita);
		resultado.setText(R.string.menu_info);
		
		presentadorInfo.mostrarVistaInfo();
		
	}


	

	

}
