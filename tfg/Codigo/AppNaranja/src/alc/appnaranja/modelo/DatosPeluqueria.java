package alc.appnaranja.modelo;

import java.io.Serializable;

import com.google.android.gms.maps.model.LatLng;

public class DatosPeluqueria implements Serializable {
	
	private static final long serialVersionUID = -4189474193847706156L;
	private String id_peluqueria;
	private String direccion;
	private String telefono;
	private String descripcion;
	private byte[] imagen;
	private LatLng localizacion;
	
	public DatosPeluqueria(String id_peluqueria, String direccion, String telefono, String descripcion, byte[] imagen, LatLng localizacion) {
		super();
		this.id_peluqueria = id_peluqueria;
		this.direccion= direccion;
		this.telefono = telefono;
		this.descripcion= descripcion;
		this.imagen = imagen;
		this.localizacion= localizacion;
		
	}

	public String getId_peluqueria() {
		return id_peluqueria;
	}

	public void setId_peluqueria(String id_peluqueria) {
		this.id_peluqueria = id_peluqueria;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public LatLng getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(LatLng localizacion) {
		this.localizacion = localizacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
	
	
	

	
}