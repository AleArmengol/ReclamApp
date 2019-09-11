package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.DuenioEntity;
import entities.PersonaEntity;
import entities.UnidadEntity;
import hibernate.HibernateUtil;
import modelo.Persona;
import modelo.Unidad;

public class DueniosDAO {
	
	private static DueniosDAO instance;
	
	
	private DueniosDAO() {
		
	}
	public static DueniosDAO getInstance() {
		if(instance == null) 
			instance = new DueniosDAO();
		return instance;
	}
	
	public List<Persona> getDueniosByUnidad(int id){
		List<Persona> dueniosN = new ArrayList<Persona>();
		List<DuenioEntity> dueniosE = new ArrayList<DuenioEntity>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		dueniosE = s.createQuery("FROM DuenioEntity de WHERE de.unidadE.identificador = ?").setInteger(0, id).list();
		s.getTransaction().commit();
		s.close();
		for(DuenioEntity de: dueniosE) {
			dueniosN.add(toNegocio(de));
		}
		
		return dueniosN;
	}
	
	public void save(Persona duenioN, Unidad unidadN) {
		PersonaEntity personaE = PersonaDAO.getInstance().toEntity(duenioN);
		UnidadEntity unidadE = UnidadDAO.getInstance().toEntity(unidadN);
		DuenioEntity duenioE = new DuenioEntity(personaE, unidadE);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(duenioE);
		s.getTransaction().commit();
		s.close();
	}
	
	Persona toNegocio(DuenioEntity duenioE) {
		return new Persona(duenioE.getPersonaE().getDocumento(), duenioE.getPersonaE().getNombre());
	}
	
}
