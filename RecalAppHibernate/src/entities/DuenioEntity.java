package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="duenios")
public class DuenioEntity {
	
	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidadE;
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaEntity personaE;
	

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
