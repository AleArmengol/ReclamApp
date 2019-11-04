package entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "duenios")
public class DuenioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "identificador")
	private UnidadEntity unidadE;
	@ManyToOne
	@JoinColumn(name = "documento")
	private PersonaEntity personaE;
	
	@OneToOne
	private UsuarioEntity usuarioE;

	public DuenioEntity() {

	}

	public DuenioEntity(PersonaEntity personaEntity, UnidadEntity unidadEntity) {
		unidadE = unidadEntity;
		personaE = personaEntity;
	}

	public UnidadEntity getUnidadE() {
		return unidadE;
	}

	public void setUnidadE(UnidadEntity unidadE) {
		this.unidadE = unidadE;
	}

	public PersonaEntity getPersonaE() {
		return personaE;
	}

	public void setPersonaE(PersonaEntity duenioE) {
		this.personaE = duenioE;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
