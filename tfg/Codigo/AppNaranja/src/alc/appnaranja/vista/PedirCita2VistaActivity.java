package alc.appnaranja.vista;

import alc.appnaranja.AppMediador;
import alc.appnaranja.presentador.IPresentadorPedirCita2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class PedirCita2VistaActivity extends Activity implements IVistaPedirCita2, OnClickListener {
	private AppMediador appMediador;
	private IPresentadorPedirCita2 presentadorPedirCita2;
	private Button siguiente;
	private CheckBox corteDePelo, manicura, pedicura, tinte, mechas, corteDeFlecos;
	private TextView total;
	private ProgressDialog barra;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaPedirCita2(this);
		presentadorPedirCita2 = appMediador.getPresentadorPedirCita2();
		
		setContentView(R.layout.cita_servicios_vista);

		corteDePelo = (CheckBox) this.findViewById(R.id.corte_de_pelo);
		manicura = (CheckBox) this.findViewById(R.id.manicura);
		pedicura = (CheckBox) this.findViewById(R.id.pedicura);
		corteDeFlecos = (CheckBox) this.findViewById(R.id.corte_de_flecos);
		tinte = (CheckBox) this.findViewById(R.id.tinte);
		mechas = (CheckBox) this.findViewById(R.id.mechas);
		total = (TextView) this.findViewById(R.id.total);
		
		corteDeFlecos.setOnClickListener(this);
		corteDePelo.setOnClickListener(this);
		manicura.setOnClickListener(this);
		pedicura.setOnClickListener(this);
		tinte.setOnClickListener(this);
		mechas.setOnClickListener(this);
		
		
		siguiente = (Button) this.findViewById(R.id.siguiente);
		siguiente.setOnClickListener(this);
		
		
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		appMediador.getPresentadorPedirCita2().mostrarVistaPedirCita2();

	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.siguiente:
			presentadorPedirCita2.lanzarPedirCita3();
			break;
		case R.id.corte_de_flecos:
			presentadorPedirCita2.cambiaPrecioVista();
			break;
		case R.id.corte_de_pelo:
			presentadorPedirCita2.cambiaPrecioVista();
			break;
		case R.id.manicura:
			presentadorPedirCita2.cambiaPrecioVista();
			break;
		case R.id.tinte:
			presentadorPedirCita2.cambiaPrecioVista();
			break;
		case R.id.pedicura:
			presentadorPedirCita2.cambiaPrecioVista();
			break;
		case R.id.mechas:
			presentadorPedirCita2.cambiaPrecioVista();
			break;
		}
		
	}

	@Override
	public boolean getEstadoCorteDePelo() {
		return corteDePelo.isChecked();
	}

	@Override
	public boolean getEstadoManicura() {
		return manicura.isChecked();
	}

	@Override
	public boolean getEstadoPedicura() {
		return pedicura.isChecked();
	}

	@Override
	public boolean getEstadoMechas() {
		return mechas.isChecked();
	}

	@Override
	public boolean getEstadoTinte() {
		return tinte.isChecked();
	}

	@Override
	public boolean getEstadoCorteDeFlecos() {
		return corteDeFlecos.isChecked();
	}

	@Override
	public void setPrecioTotal(String precio) {
		total.setText(precio);
		
	}

	@Override
	public void setPrecioCorteDePelo(String precio) {
		corteDePelo.setText(precio);
		
	}

	@Override
	public void setPrecioManicura(String precio) {
		manicura.setText(precio);
		
	}

	@Override
	public void setPrecioPedicura(String precio) {
		pedicura.setText(precio);
		
	}

	@Override
	public void setPrecioMechas(String precio) {
		mechas.setText(precio);
		
	}

	@Override
	public void setPrecioTinte(String precio) {
		tinte.setText(precio);
		
	}

	@Override
	public void setPrecioCorteDeFlecos(String precio) {
		corteDeFlecos.setText(precio);
		
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
