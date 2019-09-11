package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="unidades")

public class UnidadEntity {
	
	
	@Id
	private int identificador;
	
	@OneToMany //TODO porque no es many to many si un duenio puede tener mas de una unidad 
	@JoinColumn(name="identificador")
	private List<DuenioEntity> dueniosE;
	
	@OneToMany
	@JoinColumn(name="identificador")
	private List<InquilinoEntity> inquilinosE; // puede ser inquilino de mas de una unidad? TODO
	
	@ManyToOne
	@JoinColumn(name="codigoEdificio")
	private EdificioEntity edificioE;
	
	private String numero;
	private String piso;
	private String habitado;
	
	public UnidadEntity(String numero, String piso, String habitado, int id) {
		this.numero = numero;
		this.piso = piso;
		this.habitado = habitado;
		this.identificador = id;
		
	}
	
	public UnidadEntity() {
		
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setId(int identificador) {
		this.identificador = identificador;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getHabitado() {
		return habitado;
	}

	public void setHabitado(String habitado) {
		this.habitado = habitado;
	}

	public EdificioEntity getEdificioE() {
		return edificioE;
	}

	public void setEdificioE(EdificioEntity edificioE) {
		this.edificioE = edificioE;
	}
	
	
	
}
