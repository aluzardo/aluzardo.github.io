package alc.appnaranja.modelo;

import java.io.Serializable;

public class DatosCita implements Serializable {
	

	private static final long serialVersionUID = -4189474193847706156L;
	private String id_cita;
	private String id_horario;
	private String nombre;
	private String telefono;
	private String sexo;

	
	public DatosCita(String id_cita, String id_horario, String nombre, String telefono, String sexo) {
		super();
		this.id_cita = id_cita;
		this.id_horario = id_horario;
		this.nombre = nombre;
		this.telefono = telefono;
		this.sexo = sexo;

		
	}


	public String getId_cita() {
		return id_cita;
	}


	public void setId_cita(String id_cita) {
		this.id_cita = id_cita;
	}


	public String getId_horario() {
		return id_horario;
	}


	public void setId_horario(String id_horario) {
		this.id_horario = id_horario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	

	
}