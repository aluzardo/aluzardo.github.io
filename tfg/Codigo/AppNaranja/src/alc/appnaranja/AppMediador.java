package alc.appnaranja;

import alc.appnaranja.modelo.IModelo;
import alc.appnaranja.presentador.IPresentadorAyuda;
import alc.appnaranja.presentador.IPresentadorConfiguracion;
import alc.appnaranja.presentador.IPresentadorConfirmarCita;
import alc.appnaranja.presentador.IPresentadorDondeEstamos;
import alc.appnaranja.presentador.IPresentadorInfo;
import alc.appnaranja.presentador.IPresentadorMapa;
import alc.appnaranja.presentador.IPresentadorMisCitasDetalle;
import alc.appnaranja.presentador.IPresentadorMisCitasMaestro;
import alc.appnaranja.presentador.IPresentadorPedirCita1;
import alc.appnaranja.presentador.IPresentadorPedirCita2;
import alc.appnaranja.presentador.IPresentadorPedirCita3;
import alc.appnaranja.presentador.IPresentadorPresentacion;
import alc.appnaranja.presentador.IPresentadorPrincipal;
import alc.appnaranja.presentador.PresentadorAyuda;
import alc.appnaranja.presentador.PresentadorConfiguracion;
import alc.appnaranja.presentador.PresentadorConfirmarCita;
import alc.appnaranja.presentador.PresentadorDondeEstamos;
import alc.appnaranja.presentador.PresentadorInfo;
import alc.appnaranja.presentador.PresentadorMapa;
import alc.appnaranja.presentador.PresentadorMisCitasDetalle;
import alc.appnaranja.presentador.PresentadorMisCitasMaestro;
import alc.appnaranja.presentador.PresentadorPedirCita1;
import alc.appnaranja.presentador.PresentadorPedirCita2;
import alc.appnaranja.presentador.PresentadorPedirCita3;
import alc.appnaranja.presentador.PresentadorPresentacion;
import alc.appnaranja.presentador.PresentadorPrincipal;
import alc.appnaranja.vista.AyudaVistaActivity;
import alc.appnaranja.vista.ConfirmarCitaVistaActivity;
import alc.appnaranja.vista.DondeEstamosVistaActivity;
import alc.appnaranja.vista.IVistaAyuda;
import alc.appnaranja.vista.IVistaConfirmarCita;
import alc.appnaranja.vista.IVistaDondeEstamos;
import alc.appnaranja.vista.IVistaInfo;
import alc.appnaranja.vista.IVistaMapa;
import alc.appnaranja.vista.IVistaMisCitasDetalle;
import alc.appnaranja.vista.IVistaMisCitasMaestro;
import alc.appnaranja.vista.IVistaPedirCita1;
import alc.appnaranja.vista.IVistaPedirCita2;
import alc.appnaranja.vista.IVistaPedirCita3;
import alc.appnaranja.vista.IVistaPresentacion;
import alc.appnaranja.vista.IVistaPrincipal;
import alc.appnaranja.vista.InfoVistaActivity;
import alc.appnaranja.vista.MapaVistaActivity;
import alc.appnaranja.vista.MisCitasVistaDetalleActivity;
import alc.appnaranja.vista.MisCitasVistaMaestroActivity;
import alc.appnaranja.vista.PedirCita1VistaActivity;
import alc.appnaranja.vista.PedirCita2VistaActivity;
import alc.appnaranja.vista.PedirCita3VistaActivity;
import alc.appnaranja.vista.Preferencias;
import alc.appnaranja.vista.PresentacionVistaActivity;
import alc.appnaranja.vista.PrincipalVistaActivity;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.parse.Parse;
import com.parse.ParseACL;

@SuppressWarnings("rawtypes")
public class AppMediador extends Application {
	private static AppMediador singleton;
	// presentadores
	private IPresentadorPrincipal presentadorPrincipal;
	private IPresentadorAyuda presentadorAyuda;
	private IPresentadorInfo presentadorInfo;
	private IPresentadorConfiguracion presentadorConfiguracion;
	private IPresentadorPresentacion presentadorPresentacion;
	private IPresentadorDondeEstamos presentadorDondeEstamos;
	private IPresentadorMapa presentadorMapa;
	private IPresentadorPedirCita1 presentadorPedirCita1;
	private IPresentadorPedirCita2 presentadorPedirCita2;
	private IPresentadorPedirCita3 presentadorPedirCita3;
	private IPresentadorConfirmarCita presentadorConfirmarCita;
	private IPresentadorMisCitasMaestro presentadorMisCitasMaestro;
	private IPresentadorMisCitasDetalle presentadorMisCitasDetalle;
	// vistas
	private IVistaPrincipal vistaPrincipal;
	private IVistaAyuda vistaAyuda;
	private IVistaInfo vistaInfo;
	private IVistaPresentacion vistaPresentacion;
	private IVistaDondeEstamos vistaDondeEstamos;
	private IVistaMapa vistaMapa;
	private IVistaPedirCita1 vistaPedirCita1;
	private IVistaPedirCita2 vistaPedirCita2;
	private IVistaPedirCita3 vistaPedirCita3;
	private IVistaConfirmarCita vistaConfirmarCita;
	private IVistaMisCitasDetalle vistaMisCitasDetalle;
	private IVistaMisCitasMaestro vistaMisCitasMaestro;
	// modelo
	private IModelo modelo;
	// constantes de petición y notificación
	public static String AVISO_LISTA_CITAS = "android.parse.LISTA_CITAS_RECUPERADA";
	public static String DATOS_LISTA_CITAS = "DatosListaCitas";
	public static String AVISO_CITA_MODELO = "android.parse.CITA_RECUPERADA_EN_MODELO";
	public static String DATOS_CITA_MODELO = "DatosCitaParaModelo";
	public static String AVISO_HORARIO = "alc.appnaranja.HORARIO_RECUPERADO";
	public static String DATOS_HORARIO = "DatosHorario";
	public static String AVISO_SERVICIOSCITA = "alc.appnaranja.SERVICIOSCITA_RECUPERAD0S";
	public static String DATOS_SERVICIOSCITA = "DatosServiciosCita";
	public static String AVISO_CITA = "android.parse.CITA_RECUPERADA";
	public static String DATOS_CITA = "DatosCita";
	public static String AVISO_SERVICIOS_MODELO = "alc.appnaranja.SERVICIOS_RECUPERAD0S_EN_MODELO";
	public static String DATOS_SERVICIOS_MODELO = "DatosServiciosParaModelo";
	public static String AVISO_SERVICIOS = "alc.appnaranja.SERVICIOS_RECUPERAD0S";
	public static String DATOS_SERVICIOS = "DatosServicios";
	public static String AVISO_PELUQUERIAS = "alc.appnaranja.PELUQUERIAS_RECUPERADAS";
	public static String DATOS_PELUQUERIAS = "DatosPeluquerias";
	public static String AVISO_LISTA_PELUQUERIAS = "alc.appnaranja.LISTA_PELUQUERIAS_RECUPERADAS";
	public static String DATOS_LISTA_PELUQUERIAS = "DatosListaPeluquerias";
	public static String AVISO_ELIMINACION = "android.parse.CITA_ELIMINADA";
	
	public static String AVISO_PELUQUERIA = "alc.appnaranja.PELUQUERIA_RECUPERADA";
	public static String DATOS_PELUQUERIA = "DatosPeluqueria";
	public static String AVISO_HORARIOS = "alc.appnaranja.HORARIOS_RECUPERADOS";
	public static String AVISO_FESTIVOS = "alc.appnaranja.FESTIVOS_RECUPERADOS";
	public static String AVISO_INSERCION = "android.parse.CITA_INSERTADA";
	public static String DATOS_HORARIOS = "DatosHorarios";
	public static String DATOS_FESTIVOS = "DatosFestivos";
	public static String IDENTIFICADOR = "Identificador";
	public static String TITULO = "titulo";

	public static AppMediador getInstance() {
		return singleton;
	}

	// Métodos de creación y eliminación de presentadores
	public IPresentadorPrincipal getPresentadorPrincipal() {
		if (presentadorPrincipal == null)
			presentadorPrincipal = new PresentadorPrincipal();
		return presentadorPrincipal;
	}

	public void removePresentadorPrincipal() {
		presentadorPrincipal = null;
	}

	public IPresentadorAyuda getPresentadorAyuda() {
		if (presentadorAyuda == null)
			presentadorAyuda = new PresentadorAyuda();
		return presentadorAyuda;
	}

	public void removePresentadorAyuda() {
		presentadorAyuda = null;
	}

	public IPresentadorInfo getPresentadorInfo() {
		if (presentadorInfo == null)
			presentadorInfo = new PresentadorInfo();
		return presentadorInfo;
	}

	public void removePresentadorInfo() {
		presentadorInfo = null;
	}

	public IPresentadorConfiguracion getPresentadorConfiguracion() {
		if (presentadorConfiguracion == null)
			presentadorConfiguracion = new PresentadorConfiguracion();
		return presentadorConfiguracion;
	}

	public void removePresentadorConfiguracion() {
		presentadorConfiguracion = null;
	}

	public IPresentadorPresentacion getPresentadorPresentacion() {
		if (presentadorPresentacion == null)
			presentadorPresentacion = new PresentadorPresentacion();
		return presentadorPresentacion;
	}

	public void removePresentadorPresentacion() {
		presentadorPresentacion = null;
	}

	public IPresentadorDondeEstamos getPresentadorDondeEstamos() {
		if (presentadorDondeEstamos == null)
			presentadorDondeEstamos = new PresentadorDondeEstamos();
		return presentadorDondeEstamos;
	}

	public void removePresentadorDondeEstamos() {
		presentadorPresentacion = null;
	}
	
	public IPresentadorMapa getPresentadorMapa() {
		if (presentadorMapa == null)
			presentadorMapa = new PresentadorMapa();
		return presentadorMapa;
	}

	public void removePresentadorMapa() {
		presentadorInfo = null;
	}
	
	public IPresentadorPedirCita1 getPresentadorPedirCita1() {
		if (presentadorPedirCita1 == null)
			presentadorPedirCita1 = new PresentadorPedirCita1();
		return presentadorPedirCita1;
	}

	public void removePresentadorPedirCita1() {
		presentadorPedirCita1 = null;
	}
	public IPresentadorPedirCita2 getPresentadorPedirCita2() {
		if (presentadorPedirCita2 == null)
			presentadorPedirCita2 = new PresentadorPedirCita2();
		return presentadorPedirCita2;
	}

	public void removePresentadorPedirCita2() {
		presentadorPedirCita2 = null;
	}

	public IPresentadorPedirCita3 getPresentadorPedirCita3() {
		if (presentadorPedirCita3 == null)
			presentadorPedirCita3 = new PresentadorPedirCita3();
		return presentadorPedirCita3;
	}

	public void removePresentadorPedirCita3() {
		presentadorPedirCita3 = null;
	}
	
	public IPresentadorConfirmarCita getPresentadorConfirmarCita() {
		if (presentadorConfirmarCita == null)
			presentadorConfirmarCita = new PresentadorConfirmarCita();
		return presentadorConfirmarCita;
	}

	public void removePresentadorConfirmarCita() {
		presentadorConfirmarCita = null;
	}
	public IPresentadorMisCitasMaestro getPresentadorMisCitasMaestro() {
		if (presentadorMisCitasMaestro == null)
			presentadorMisCitasMaestro = new PresentadorMisCitasMaestro();
		return presentadorMisCitasMaestro;
	}

	public void removePresentadorMisCitasMaestro() {
		presentadorMisCitasMaestro = null;
	}
	public IPresentadorMisCitasDetalle getPresentadorMisCitasDetalle() {
		if (presentadorMisCitasDetalle == null)
			presentadorMisCitasDetalle = new PresentadorMisCitasDetalle();
		return presentadorMisCitasDetalle;
	}

	public void removePresentadorMisCitasDetalle() {
		presentadorPedirCita3 = null;
	}


	
	// Métodos accessor de las vistas
	public IVistaPrincipal getVistaPrincipal() {
		return vistaPrincipal;
	}

	public void setVistaPrincipal(IVistaPrincipal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
	}

	public IVistaAyuda getVistaAyuda() {
		return vistaAyuda;
	}

	public void setVistaAyuda(IVistaAyuda vistaAyuda) {
		this.vistaAyuda = vistaAyuda;
	}

	public IVistaInfo getVistaInfo() {
		return vistaInfo;
	}

	public void setVistaInfo(IVistaInfo vistaInfo) {
		this.vistaInfo = vistaInfo;
	}

	public IVistaPresentacion getVistaPresentacion() {
		return vistaPresentacion;
	}

	public void setVistaPresentacion(IVistaPresentacion vistaPresentacion) {
		this.vistaPresentacion = vistaPresentacion;
	}
	
	public IVistaDondeEstamos getVistaDondeEstamos() {
		return vistaDondeEstamos;
	}

	public void setVistaDondeEstamos(IVistaDondeEstamos vistaDondeEstamos) {
		this.vistaDondeEstamos = vistaDondeEstamos;
	}
	
	public IVistaMapa getVistaMapa() {
		return vistaMapa;
	}

	public void setVistaMapa(IVistaMapa vistaMapa) {
		this.vistaMapa = vistaMapa;
	}

	public IVistaPedirCita1 getVistaPedirCita1() {
		return vistaPedirCita1;
	}

	public void setVistaPedirCita1(IVistaPedirCita1 vistaPedirCita1) {
		this.vistaPedirCita1 = vistaPedirCita1;
	}

	public IVistaPedirCita2 getVistaPedirCita2() {
		return vistaPedirCita2;
	}

	public void setVistaPedirCita2(IVistaPedirCita2 vistaPedirCita2) {
		this.vistaPedirCita2 = vistaPedirCita2;
	}

	public IVistaPedirCita3 getVistaPedirCita3() {
		return vistaPedirCita3;
	}

	public void setVistaPedirCita3(IVistaPedirCita3 vistaPedirCita3) {
		this.vistaPedirCita3 = vistaPedirCita3;
	}

	public IVistaConfirmarCita getVistaConfirmarCita() {
		return vistaConfirmarCita;
	}

	public void setVistaConfirmarCita(IVistaConfirmarCita vistaConfirmarCita) {
		this.vistaConfirmarCita = vistaConfirmarCita;
	}

	public IVistaMisCitasDetalle getVistaMisCitasDetalle() {
		return vistaMisCitasDetalle;
	}

	public void setVistaMisCitasDetalle(IVistaMisCitasDetalle vistaMisCitasDetalle) {
		this.vistaMisCitasDetalle = vistaMisCitasDetalle;
	}

	public IVistaMisCitasMaestro getVistaMisCitasMaestro() {
		return vistaMisCitasMaestro;
	}

	public void setVistaMisCitasMaestro(IVistaMisCitasMaestro vistaMisCitasMaestro) {
		this.vistaMisCitasMaestro = vistaMisCitasMaestro;
	}


	// Métodos accessor del modelo
	public IModelo getModelo() {
		return modelo;
	}

	public void setModelo(IModelo modelo) {
		this.modelo = modelo;
	}

	// Métodos de lanzamiento de actividades, servicios, receptores
	// broadcast,...
	public void launchActivity(Class actividadInvocada, Object invocador,
			Bundle extras) {
		Intent i = new Intent(this, actividadInvocada);
		if (extras != null)
			i.putExtras(extras);
		if (!invocador.getClass().equals(Activity.class)){
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			//con esta linea limpio la pila de actividades
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		}
			
		startActivity(i);

	}

	public void launchActivityForResult(Class actividadInvocada,
			Activity actividadInvocadora, int requestCode, Bundle extras) {
		Intent i = new Intent(actividadInvocadora, actividadInvocada);
		if (extras != null)
			i.putExtras(extras);
		actividadInvocadora.startActivityForResult(i, requestCode);
	}

	public void launchService(Class servicioInvocado, Bundle extras) {
		Intent i = new Intent(this, servicioInvocado);
		if (extras != null)
			i.putExtras(extras);
		startService(i);
	}

	public void stopService(Class servicioInvocado) {
		Intent i = new Intent(this, servicioInvocado);
		stopService(i);
	}

	public void registerReceiver(BroadcastReceiver receptor, String[] acciones) {
		for (int i = 0; i < acciones.length; i++)
			LocalBroadcastManager.getInstance(this).registerReceiver(receptor,
					new IntentFilter(acciones[i]));
	}

	public void unRegisterReceiver(BroadcastReceiver receptor) {
		LocalBroadcastManager.getInstance(this).unregisterReceiver(receptor);
	}

	public void sendBroadcast(String accion, Bundle extras) {
		Intent intent = new Intent();
		intent.setAction(accion);
		if (extras != null)
			intent.putExtras(extras);
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}

	// Métodos de navegación
	public Class getVistaParaPrincipal() {
		return PrincipalVistaActivity.class;
	}
	
	public Class getVistaParaAyuda() {
		return AyudaVistaActivity.class;
	}
	
	public Class getVistaParaInfo() {
		return InfoVistaActivity.class;
	}
	
	public Class getVistaParaPresentacion() {
		return PresentacionVistaActivity.class;
	}
	
	public Class getVistaParaDondeEstamos() {
		return DondeEstamosVistaActivity.class;
	}
	
	public Class getVistaParaMapa() {
		return MapaVistaActivity.class;
	}
	
	public Class getVistaParaPedirCita1() {
		return PedirCita1VistaActivity.class;
	}
	
	public Class getVistaParaPedirCita2() {
		return PedirCita2VistaActivity.class;
	}
	
	public Class getVistaParaPedirCita3() {
		return PedirCita3VistaActivity.class;
	}
	
	public Class getVistaParaConfirmarCita() {
		return ConfirmarCitaVistaActivity.class;
	}
	
	public Class getVistaParaMisCitasDetalle() {
		return MisCitasVistaDetalleActivity.class;
	}
	
	public Class getVistaParaMisCitasMaestro() {
		return MisCitasVistaMaestroActivity.class;
	}
	
	public Class getVistaParaConfiguracion() {
		return Preferencias.class;
	}
	

	// Métodos de iniciación de Parse
	private void iniciarParse() {
		String PARSE_APPLICATION_ID = "bmFXyDLjT6MkNzlHyCmcwqnwOWuxNyljDnf9PNPJ";
		String PARSE_CLIENT_KEY = "Bo2SsW37OHvNaxyh0E0Fb5ClL7n5SdVjPwfgHT3N";
		Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);
		ParseACL defaultACL = new ParseACL();
		// se permite la lectura de todos los objetos creados. ¡No hay control
		// de seguridad!
		defaultACL.setPublicReadAccess(true);
		defaultACL.setPublicWriteAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
	}

	// Método que crea la aplicación
	@Override
	public void onCreate() {
		super.onCreate();
		iniciarParse();
		presentadorPrincipal = null;
		presentadorAyuda = null;
		presentadorInfo = null;
		presentadorConfiguracion = null;
		presentadorPresentacion = null;
		presentadorDondeEstamos = null;
		presentadorMapa = null;
		presentadorPedirCita1 = null;
		presentadorPedirCita2 = null;
		presentadorPedirCita3 = null;
		presentadorConfirmarCita = null;
		presentadorMisCitasMaestro = null;
		presentadorMisCitasDetalle = null;
		singleton = this;
	}



	

}
