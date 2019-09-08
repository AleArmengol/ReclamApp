package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.EdificioEntity;
import entities.UnidadEntity;
import exceptions.EdificioException;
import exceptions.UnidadException;
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
	public Unidad findUnidad(int codigo, String piso, String numero) throws UnidadException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UnidadEntity unidadE = (UnidadEntity) s.createQuery("from UnidadEntity ue where ue.edificioE.codigo = ? and ue.piso = ? and ue.numero = ?")
				.setInteger(0, codigo).setString(1, piso).setString(2, numero).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if(unidadE == null) {
			throw new UnidadException("No existe la Unidad" + numero + "en el piso" + piso +"del edificio" + codigo ); 
		}
		return toNegocio(unidadE);
	}

 Unidad toNegocio(UnidadEntity ue) {
	Edificio aux = EdificioDAO.getInstance().toNegocio(ue.getEdificioE()); //Para construir un objeto Unidad 
	return new Unidad(ue.getIdentificador(), ue.getPiso(), ue.getNumero(), aux);     //necesitamos un Objecto de Negocio Edificio
	}
}
