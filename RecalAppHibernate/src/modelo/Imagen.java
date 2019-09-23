package modelo;

import daos.ImagenDAO;

public class Imagen {

	private int numero;
	private String path;
	private String tipo;
	
	public Imagen(String path, String tipo) {
		this.path = path;
		this.tipo = tipo;
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

	public void save(int numeroReclamo) {
		ImagenDAO.getInstance().save(this, numeroReclamo);
	}

}
