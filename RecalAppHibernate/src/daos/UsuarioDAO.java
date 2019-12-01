package daos;

import org.hibernate.Session;

import controlador.Controlador;
import entities.PersonaEntity;
import entities.UsuarioEntity;
import hibernate.HibernateUtil;
import modelo.Persona;
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

	public void save(Usuario usuario) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		PersonaEntity personaE = (PersonaEntity) s.createQuery("From PersonaEntity pe WHERE pe.documento = ?").setString(0, usuario.getDocumento()).uniqueResult();
		UsuarioEntity toSave = new UsuarioEntity(usuario.getIdUsuario(), personaE, usuario.getClave());
		s.save(toSave);
		s.getTransaction().commit();
		}

	public Usuario logIn(String idUsuario, String password) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UsuarioEntity usuarioE = null;
		usuarioE = (UsuarioEntity) s.createQuery("From UsuarioEntity ue WHERE ue.idUsuario = ? AND ue.clave = ?").setString(0, idUsuario).setString(1, password).uniqueResult();
		if(usuarioE == null) {
			return null;
		} else {
			return new Usuario(usuarioE.getIdUsuario(), usuarioE.getClave(), usuarioE.getPersonaE().getDocumento());
		}
	}

}
