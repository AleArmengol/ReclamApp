package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import entities.DuenioEntity;
import entities.InquilinoEntity;
import entities.PersonaEntity;
import entities.UnidadEntity;
import hibernate.HibernateUtil;
import modelo.Persona;
import modelo.Unidad;

public class InquilinosDAO {
	private static InquilinosDAO instance;
	
	private InquilinosDAO() {
		
	}
	
	public static InquilinosDAO getInstance() {
		if(instance == null) {
			instance = new InquilinosDAO();
		}
		return instance;
	}
	
	public List<Persona> getInquilinosByUnidad(int id){
		List<Persona> inquilinosN = new ArrayList<Persona>();
		List<InquilinoEntity> inquilinosE = new ArrayList<InquilinoEntity>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		inquilinosE = s.createQuery("FROM InquilinoEntity ie WHERE ie.unidadE.identificador = ?").setInteger(0, id).list();
		s.getTransaction().commit();
		s.close();
		for(InquilinoEntity ie: inquilinosE) {
			inquilinosN.add(toNegocio(ie));
		}
		return inquilinosN;
	}
	
	Persona toNegocio(InquilinoEntity inquilinoE) {
		return new Persona(inquilinoE.getPersonaE().getDocumento(), inquilinoE.getPersonaE().getDocumento());
	}

	public void save(Persona inquilino, Unidad unidad) {
		PersonaEntity personaE = PersonaDAO.getInstance().toEntity(inquilino);
		UnidadEntity unidadE = UnidadDAO.getInstance().toEntity(unidad);
		InquilinoEntity inquilinoE = new InquilinoEntity(personaE, unidadE);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(inquilinoE);
		s.getTransaction().commit();
		s.close();
		
	}

	public void delete(String documento) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		InquilinoEntity inquilinoE = (InquilinoEntity) s.createQuery("from inquilinoEntity ie where ie.personaE.documento = ?").setString(0, documento).uniqueResult();
		if(inquilinoE != null) {
			s.delete(inquilinoE);
		}
		s.getTransaction().commit();
		s.close();
		
	}

	public Unidad getUnidadInquilino(String documento) {
		UnidadEntity unidadE;
		Unidad unidadN;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		InquilinoEntity inquilinoE = (InquilinoEntity) s.createQuery("from inquilinoEntity ie where ie.personaE.documento = ?").setString(0, documento).uniqueResult();
		if(inquilinoE != null) {
			unidadE = inquilinoE.getUnidadE();
			unidadN =  UnidadDAO.getInstance().toNegocio(unidadE);
		} else {
			unidadN = null;
		}
		return unidadN;
		
	}

	public boolean isInquilino(String documento) {
		Session s= HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		InquilinoEntity inquilinoE= new InquilinoEntity();
		inquilinoE=(InquilinoEntity) s.createQuery("FROM InquilinoEntity de WHERE de.documento=?").setString(0,documento).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if(inquilinoE != null) {
			return true;
		} else {
			return false;
		}
	}	
}
