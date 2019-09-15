package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reclamos")
public class ReclamoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReclamo;
	
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaEntity usuarioE;
	
	@ManyToOne
	@JoinColumn(name="codigo")
	private EdificioEntity edificioE;
	
	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidadE;
	
	@Column(name="Estado")
	String estado;
	
	String ubicacion;
	String descripcion;
	
	public ReclamoEntity() {
		
	}
	
	public ReclamoEntity(PersonaEntity personaE, EdificioEntity edificioE2, String ubicación, String descripcion2,
			UnidadEntity unidadE2) {
		
		this.usuarioE=personaE;
		this.edificioE=edificioE2;
		this.ubicacion=ubicación;
		this.descripcion=descripcion2;
		this.unidadE=unidadE2;		
	}

	public int getIdReclamo() {
		return idReclamo;
	}

	public void setIdReclamo(int idReclamo) {
		this.idReclamo = idReclamo;
	}

	public PersonaEntity getUsuarioE() {
		return usuarioE;
	}

	public void setUsuarioE(PersonaEntity usuarioE) {
		this.usuarioE = usuarioE;
	}

	public EdificioEntity getEdificioE() {
		return edificioE;
	}

	public void setEdificioE(EdificioEntity edificioE) {
		this.edificioE = edificioE;
	}

	public UnidadEntity getUnidadE() {
		return unidadE;
	}

	public void setUnidadE(UnidadEntity unidadE) {
		this.unidadE = unidadE;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
	
	

}
