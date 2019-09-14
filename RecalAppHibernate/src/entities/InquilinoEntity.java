package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.Persona;

@Entity
@Table(name = "inquilinos")
public class InquilinoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidadE;
	
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaEntity personaE;
	
	public InquilinoEntity() {
		
	}

	public InquilinoEntity(PersonaEntity personaE2, UnidadEntity unidadE2) {
		personaE = personaE2;
		unidadE = unidadE2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setPersonaE(PersonaEntity personaE) {
		this.personaE = personaE;
	}
	
	
	

}
