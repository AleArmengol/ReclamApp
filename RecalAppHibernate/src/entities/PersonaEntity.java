package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personas")
public class PersonaEntity {

	// TODO anotaciones para ReclamoEntity

	@Id
	private String documento;
	private String nombre;

	public PersonaEntity() {
	}

	public PersonaEntity(String documento2, String nombre2) {
		documento = documento2;
		nombre = nombre2;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
