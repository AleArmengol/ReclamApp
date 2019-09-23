package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.ReclamoDAO;
import views.EdificioView;
import views.Estado;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

public class Reclamo {

	private int numero;
	private Persona usuario;
	private Edificio edificio;
	private String ubicacion;
	private String descripcion;
	private Unidad unidad;
	private Estado estado;
	private List<Imagen> imagenes;
	

	public Reclamo(int numero, Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad, Estado estado) {
		this.numero = numero;
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = estado;
		this.imagenes = new ArrayList<Imagen>();
	}
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = Estado.nuevo;
		this.imagenes = new ArrayList<Imagen>();
	}

	public void agregarImagen(String path, String tipo) {
		Imagen imagen = new Imagen(path, tipo);
		imagenes.add(imagen);
		imagen.save(numero);
	}
	
	public void cambiarEstado(Estado estado) {
		this.estado = estado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Persona getUsuario() {
		return usuario;
	}

	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public void save() {
		ReclamoDAO.getInstance().save(this);
	}
	
	public void update() {
		ReclamoDAO.getInstance().update(this);

	}
	
	public String getEstadoToString() {
		if(estado == Estado.abierto)
			return "abierto";
		else if(estado == Estado.anulado)
			return "anulado";
		else if(estado == Estado.desestimado)
			return "desestimado";
		else if(estado == Estado.enProceso)
			return "en proceso";
		else if(estado == Estado.nuevo)
			return "nuevo";
		else
			return "terminado";
	}
	

	public ReclamoView toView() {
		PersonaView auxPersona = usuario.toView();
		EdificioView auxEdificio = edificio.toView();
		UnidadView auxUnidad = unidad.toView();
		List<Imagen> imagenes = this.getImagenes();
		List<String> pathImagenes = new ArrayList<String>();
		for(Imagen im: imagenes) {
			pathImagenes.add(im.getPath());
		}
		return new ReclamoView(numero, auxPersona, auxEdificio, ubicacion, descripcion, auxUnidad, estado, pathImagenes);
	}
}
