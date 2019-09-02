package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.EdificioEntity;
import exceptions.EdificioException;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Unidad;

public class EdificioDAO {
	
	private static EdificioDAO instance;
	private EdificioDAO() {
		
	}
	
	public static EdificioDAO getInstance() {
		if(instance == null)
			instance = new EdificioDAO();
		return instance;
	}
	
	public List<Edificio> getEdificios(){
		List<Edificio> edificiosN = new ArrayList<Edificio>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<EdificioEntity> edificiosE = s.createQuery("from EdificioEntity").list();
		s.getTransaction().commit();
		s.close();
		for(EdificioEntity ee : edificiosE) {
			edificiosN.add(toNegocio(ee));
		}
		return edificiosN;
	}
	
	public Edificio findById(int codigo) throws EdificioException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		EdificioEntity edificioE = (EdificioEntity) s.createQuery("from EdificioEntity ee where ee.codigo = ?")
				.setInteger(0, codigo).uniqueResult();
		if(edificioE == null) {
			throw new EdificioException("No existe el Edificio " + codigo); 
		}
		return toNegocio(edificioE);
	}
	
	private Edificio toNegocio(EdificioEntity entity) {
		return new Edificio(entity.getCodigo(),entity.getNombre(), entity.getDireccion());
	}

	

}
