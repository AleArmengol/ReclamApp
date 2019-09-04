package entities;

import java.util.List;

import javax.persistence.Column;
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
	@Column(name="identificador")
	private int id;
	
	@OneToMany
	@JoinColumn(name="identificador")
	private List<DuenioEntity> dueniosE;
//	@OneToMany
//	private List<InquilinoEntity> inquilinosE;
	private String piso;
	private String numero;
	private String habitado;
	@ManyToOne
	@JoinColumn(name="codigoEdificio")
	private EdificioEntity edificioE;
	
	public UnidadEntity() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
