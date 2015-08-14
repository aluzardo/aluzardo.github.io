package alc.appnaranja.modelo;

import java.io.Serializable;


public class DatosServicios implements Serializable {
	
	private static final long serialVersionUID = -4189474193847706156L;
	private String id_servicio;
	private String nombre;
	private String precio;
	
	public DatosServicios(String id_servicio, String nombre, String precio) {
		super();
		this.id_servicio = id_servicio;
		this.nombre= nombre;
		this.precio = precio;
	}

	public String getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(String id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	


}