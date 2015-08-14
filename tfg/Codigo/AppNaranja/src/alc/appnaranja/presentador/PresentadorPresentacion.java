package alc.appnaranja.presentador;

import alc.appnaranja.AppMediador;

public class PresentadorPresentacion implements IPresentadorPresentacion {

	@Override
	public void mostrarVistaPresentacion() {
		AppMediador.getInstance().getVistaPresentacion().setTextoPresentacion("Desde que inauguramos nuestro primer salón Naranja en Lanzarote a principios de 2011 nos convertimos, automáticamente, en la peluquería unisex más económica de España. Hoy, dos años después, contamos con 6 salones distribuidos entre Las Palmas y Lanzarote y más de 35 empleados dispuestos a atenderle.");
	}

	
	

}
