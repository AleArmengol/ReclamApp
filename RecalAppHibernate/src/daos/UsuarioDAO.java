package daos;

import org.hibernate.Session;

import entities.UsuarioEntity;
import hibernate.HibernateUtil;
import modelo.Usuario;

public class UsuarioDAO {

	private static UsuarioDAO instance;

	private UsuarioDAO() {

	}

	public static UsuarioDAO getInstance() {
		if (instance == null)
			instance = new UsuarioDAO();
		return instance;
	}

	public Usuario findUsuarioById(String idUsuario) {
		UsuarioEntity usuarioE = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		usuarioE = (UsuarioEntity) s.createQuery("FROM UsuarioEntity ue WHERE ue.idUsuario = ?").setString(0, idUsuario).uniqueResult();
		if(usuarioE != null) {
			Usuario usuarioN = new Usuario(usuarioE.getIdUsuario(), usuarioE.getClave(), usuarioE.getPersonaE().getDocumento());
			s.getTransaction().commit();
			s.clear();
			return usuarioN;
			
		} else {
			return null;
		}
	}
	
	public Usuario findUsuarioByDocumento(String documento) {
		UsuarioEntity usuarioE = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		usuarioE = (UsuarioEntity) s.createQuery("FROM UsuarioEntity ue WHERE ue.personaE.documento = ?").setString(0, documento).uniqueResult();
		if(usuarioE != null) {
			Usuario usuarioN = new Usuario(usuarioE.getIdUsuario(), usuarioE.getClave(), usuarioE.getPersonaE().getDocumento());
			s.getTransaction().commit();
			s.clear();
			return usuarioN;
			
		} else {
			return null;
		}
	}

}
