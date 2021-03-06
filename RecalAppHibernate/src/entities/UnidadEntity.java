package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unidades")

public class UnidadEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identificador;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "identificador")
	private List<DuenioEntity> dueniosE;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "identificador")
	private List<InquilinoEntity> inquilinosE;

	@ManyToOne
	@JoinColumn(name = "codigoEdificio")
	private EdificioEntity edificioE;

	private String numero;
	private String piso;
	private String habitado;

	public UnidadEntity(String numero, String piso, String habitado, int id, EdificioEntity edificioE) {
		this.numero = numero;
		this.piso = piso;
		this.habitado = habitado;
		this.identificador = id;
		this.edificioE = edificioE;
	}

	public UnidadEntity() {

	}

	public UnidadEntity(String numero2, String piso2, String habitado2, int id) {
		this.numero = numero2;
		this.piso = piso2;
		this.habitado = habitado2;
		this.identificador = id;
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

	public List<InquilinoEntity> getInquilinosE() {
		return inquilinosE;
	}

	public void setInquilinosE(List<InquilinoEntity> inquilinosE) {
		this.inquilinosE = inquilinosE;
	}

	public List<DuenioEntity> getDueniosE() {
		return dueniosE;
	}

	public void setDueniosE(List<DuenioEntity> dueniosE) {
		this.dueniosE = dueniosE;
	}

}
