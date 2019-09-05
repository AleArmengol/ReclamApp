package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="duenios")
public class DuenioEntity {
	
	@Id
	private int id;
	
	private int identificador; //identificador de unidad
	
	private String documento;
	
	//DEBE ESTAR unidadE, que @ debe tener?
	//private UnidadEntity unidadE;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	
	

}
