package alc.appnaranja.modelo;

import java.io.Serializable;


public class DatosHorario implements Serializable {
	
	private static final long serialVersionUID = -4189474193847706156L;
	private String id_horario;
	private String id_peluqueria;
	private String fecha;
	private String hora;
	
	public DatosHorario(String id_horario, String id_peluqueria, String fecha, String hora) {
		super();
		this.id_horario= id_horario;
		this.id_peluqueria= id_peluqueria;
		this.fecha= fecha;
		this.hora = hora;
	}

	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getId_horario() {
		return id_horario;
	}



	public void setId_horario(String id_horario) {
		this.id_horario = id_horario;
	}



	public String getId_peluqueria() {
		return id_peluqueria;
	}



	public void setId_peluqueria(String id_peluqueria) {
		this.id_peluqueria = id_peluqueria;
	}
	
	
	


}