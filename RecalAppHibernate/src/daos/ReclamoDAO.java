package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.EdificioEntity;
import entities.PersonaEntity;
import entities.ReclamoEntity;
import entities.UnidadEntity;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;

public class ReclamoDAO {
	
	private static ReclamoDAO instance;
	private ReclamoDAO() {
		
	}
	
	public static ReclamoDAO getInstance() {
		if(instance == null)
			instance = new ReclamoDAO();
		return instance;
	}
	
	public List<Reclamo> getReclamosByEdificio(int codigo){
		List<Reclamo> reclamosN = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.codigo = ?").list();
		s.getTransaction().commit();
		s.close();
		for(ReclamoEntity re: reclamosE) {
			reclamosN.add(toNegocio(re));
		}
		return reclamosN;
	}
	
	public void save (Reclamo reclamo) {
		ReclamoEntity aGuardar= toEntity(reclamo);
		Session s =HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGuardar);
		s.getTransaction().commit();
		s.close();
	}
	
	ReclamoEntity toEntity(Reclamo reclamo) {
		PersonaEntity personaE = PersonaDAO.getInstance().toEntity(reclamo.getUsuario());
		EdificioEntity edificioE = EdificioDAO.getInstance().toEntity(reclamo.getEdificio());
		UnidadEntity UnidadE=UnidadDAO.getInstance().toEntity(reclamo.getUnidad());
		return new ReclamoEntity(personaE, edificioE, reclamo.getUbicación(), reclamo.getDescripcion(),UnidadE);
	}

	private Reclamo toNegocio(ReclamoEntity re) {
		Persona personaN = PersonaDAO.getInstance().toNegocio(re.getUsuarioE());
		Edificio edificioN = EdificioDAO.getInstance().toNegocio(re.getEdificioE());
		Unidad unidadN = UnidadDAO.getInstance().toNegocio(re.getUnidadE());
		
		return new Reclamo(personaN, edificioN, re.getUbicacion(), re.getDescripcion(), unidadN);
	}

}
