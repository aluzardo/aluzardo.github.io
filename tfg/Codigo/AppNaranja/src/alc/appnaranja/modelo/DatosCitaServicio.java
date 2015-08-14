package alc.appnaranja.modelo;

import java.io.Serializable;

public class DatosCitaServicio implements Serializable {

	private static final long serialVersionUID = -4189474193847706156L;
	private String id_cita;
	private String id_servicio;

	
	public DatosCitaServicio(String id_cita, String id_servicio) {
		super();
		this.id_cita = id_cita;
		this.id_servicio = id_servicio;
			
	}


	public String getId_cita() {
		return id_cita;
	}


	public void setId_cita(String id_cita) {
		this.id_cita = id_cita;
	}


	public String getId_servicio() {
		return id_servicio;
	}


	public void setId_servicio(String id_servicio) {
		this.id_servicio = id_servicio;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
}