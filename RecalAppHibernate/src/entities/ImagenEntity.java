package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="imagenes")

public class ImagenEntity {
	
	//TODO anotaciones para ImagenEntity
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int numero;
	
	@ManyToOne
	@JoinColumn(name="idReclamo")
	private ReclamoEntity reclamoE;
	
	public ImagenEntity() {}
	
	public ImagenEntity(String path, String tipo, ReclamoEntity reclamoE2) {
		this.path = path;
		this.tipo = tipo;
		reclamoE = reclamoE2;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	private String path;
	private String tipo;
	


}
