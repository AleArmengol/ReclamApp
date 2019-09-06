package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.UnidadEntity;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Unidad;

public class UnidadDAO {
	
	private static UnidadDAO instance;
	private UnidadDAO() {
		
	}
	
	public static UnidadDAO getInstance() {
		if(instance == null)
			instance = new UnidadDAO();
		return instance;
	}
	

//	private Unidad toNegocio(UnidadEntity entity) {
//		// FALTAN ATRIBUTOS DE LA ENTITY UNIDAD
//	}

	public List<Unidad> getUnidades(int codigo) {
		List<Unidad> unidadesN = new ArrayList<Unidad>();
		List<UnidadEntity> unidadesE = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		unidadesE = s.createQuery("FROM UnidadEntity ue WHERE ue.edificioE.codigo = ?").setInteger(0, codigo).list();
		s.getTransaction().commit();
		s.close();
		for(UnidadEntity eu: unidadesE) {
			unidadesN.add(toNegocio(eu));
		}
		
		return unidadesN;
		
	}

 Unidad toNegocio(UnidadEntity eu) {
	Edificio aux = EdificioDAO.getInstance().toNegocio(eu.getEdificioE()); //Para construir un objeto Unidad 
	return new Unidad(eu.getIdentificador(), eu.getPiso(), eu.getNumero(), aux);     //necesitamos un Objecto de Negocio Edificio
	}
}
