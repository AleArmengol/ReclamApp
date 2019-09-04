package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.DuenioEntity;
import hibernate.HibernateUtil;
import modelo.Persona;

public class DueniosDAO {
	
	private static DueniosDAO instancia;
	
	
	private DueniosDAO() {
		
	}
	public static DueniosDAO getInstance() {
		if(instancia == null) 
			instancia = new DueniosDAO();
		return instancia;
	}
	
	public List<Persona> getDueniosByUnidad(int id){
		List<Persona> dueniosN = new ArrayList<Persona>();
		List<DuenioEntity> dueniosE = new ArrayList<DuenioEntity>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		dueniosE = s.createQuery("FROM UnidadEntity ue WHERE ue.dueniosE.identificador = ?").setInteger(0, id).list(); //TODO CONSULTAR
		s.getTransaction().commit();
		s.close();
		for(DuenioEntity de: dueniosE) {
			dueniosN.add(PersonaDAO.getInstance().findById(de.getDocumento())); //TODO preguntar
		}
		
		return dueniosN;
	}

}
