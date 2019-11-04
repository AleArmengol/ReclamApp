package modelo;

public class Usuario {
	
	private String idUsuario;
	private String clave;
	private String documento;
	
	public Usuario(String idUsuario, String clave, String documento) {
		this.idUsuario = idUsuario;
		this.clave = clave;
		this.documento = documento;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	

}
