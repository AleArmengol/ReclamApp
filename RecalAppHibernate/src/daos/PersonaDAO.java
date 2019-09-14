package daos;

import org.hibernate.Session;

import entities.PersonaEntity;
import hibernate.HibernateUtil;
import modelo.Persona;

public class PersonaDAO {

	private static PersonaDAO instance;
	private PersonaDAO() {
		
	}
	
	public static PersonaDAO getInstance() {
		if(instance == null)
			instance = new PersonaDAO();
		return instance;
	}
	
	public Persona findPersonaById(String documento) {
		PersonaEntity personaE = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		personaE = (PersonaEntity) s.createQuery("FROM PersonaEntity pe WHERE pe.documento = ?").setString(0, documento).uniqueResult();
		Persona personaN = new Persona(personaE.getDocumento(), personaE.getNombre());
		s.getTransaction().commit();
		s.close();
		return personaN;
	}
	public void save(Persona persona){
		PersonaEntity aGuardar = toEntity(persona);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGuardar);
		s.getTransaction().commit();
		s.close();
	}
	PersonaEntity toEntity(Persona persona){
		return new PersonaEntity(persona.getDocumento(), persona.getNombre());
	}

	public Persona toNegocio(PersonaEntity usuarioE) {
		return new Persona(usuarioE.getDocumento(), usuarioE.getNombre());
	} 
	
}
