package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.ImagenDAO;
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
	private static final String PATH_FTP = "C:\\Users\\aearm\\Documents\\ReclamAppFTP";

	public Reclamo(int numero, Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad,
			Estado estado) {
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

	public Reclamo(Persona usuario, Edificio edificio2, String descripcion2, Unidad unidad2) {
		this.usuario = usuario;
		this.edificio = edificio2;
		this.ubicacion = null;
		this.descripcion = descripcion2;
		this.unidad = unidad2;
		this.estado = Estado.nuevo;
		this.imagenes = new ArrayList<Imagen>();
	}

	public Reclamo(Persona usuario, Edificio edificio2, String descripcion2) {
		this.usuario = usuario;
		this.edificio = edificio2;
		this.ubicacion = null;
		this.descripcion = descripcion2;
		this.unidad = null;
		this.estado = Estado.nuevo;
		this.imagenes = new ArrayList<Imagen>();
	}

	public void agregarImagen(String nombre, String tipo) {
		String pathImagen = PATH_FTP + "\\" + this.numero + "-" + nombre;
		Imagen imagen = new Imagen(pathImagen, tipo);
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
		if (imagenes != null && imagenes.size() == 0) {
			imagenes = ImagenDAO.getInstance().getImagenesByReclamo(this.numero);
		}
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
		if (estado == Estado.abierto)
			return "abierto";
		else if (estado == Estado.anulado)
			return "anulado";
		else if (estado == Estado.desestimado)
			return "desestimado";
		else if (estado == Estado.enProceso)
			return "en proceso";
		else if (estado == Estado.nuevo)
			return "nuevo";
		else
			return "terminado";
	}

	public ReclamoView toView() {
		PersonaView auxPersona = usuario.toView();
		EdificioView auxEdificio = edificio.toView();
		UnidadView auxUnidad = null;
		if(unidad!= null) {
			auxUnidad = unidad.toView();
		}
		List<Imagen> imagenes = this.getImagenes();
		List<String> pathImagenes = new ArrayList<String>();
		for (Imagen im : imagenes) {
			pathImagenes.add(im.getPath());
		}
		return new ReclamoView(numero, auxPersona, auxEdificio, ubicacion, descripcion, auxUnidad, estado,
				pathImagenes);
	}
}
