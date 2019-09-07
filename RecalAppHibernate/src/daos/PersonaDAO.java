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
	
	public Persona findById(String documento) {
		PersonaEntity personaE = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		personaE = (PersonaEntity) s.createQuery("FROM PersonaEntity pe WHERE pe.documento = ?").setString(0, documento).uniqueResult();
		Persona personaN = new Persona(personaE.getDocumento(), personaE.getNombre());
		return personaN;
	}
}