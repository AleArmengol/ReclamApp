package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import modelo.Unidad;

@Entity
@Table(name = "edificios")

public class EdificioEntity {

	@Id
	private int codigo;

	private String nombre;

	private String direccion;

	@OneToMany
	@JoinColumn(name = "codigo")
	private List<UnidadEntity> unidades;

	public EdificioEntity() {
	}

	public EdificioEntity(int codigo, String nombre, String direccion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
