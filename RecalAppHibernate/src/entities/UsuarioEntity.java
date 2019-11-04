package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
	
	@Id
	private String idUsuario;
	
	@OneToOne
	@JoinColumn(name = "documento")
	private PersonaEntity personaE;
	
	private String clave;
	
	public UsuarioEntity() {
		
	}
	
	public UsuarioEntity(String idUsuario, PersonaEntity personaE, String clave) {
		this.idUsuario = idUsuario;
		this.personaE = personaE;
		this.clave = clave;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public PersonaEntity getPersonaE() {
		return personaE;
	}

	public void setPersonaE(PersonaEntity personaE) {
		this.personaE = personaE;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	

}
