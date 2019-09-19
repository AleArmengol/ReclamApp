package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.EdificioEntity;
import entities.PersonaEntity;
import entities.ReclamoEntity;
<<<<<<< HEAD
import entities.UnidadEntity;
=======
import exceptions.ReclamoException;
>>>>>>> bcfe01c3d87dc78078c73b2920cf6d03166c8cf4
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
	
	private Reclamo toNegocio(ReclamoEntity re) {
		Persona personaN = PersonaDAO.getInstance().toNegocio(re.getUsuarioE());
		Edificio edificioN = EdificioDAO.getInstance().toNegocio(re.getEdificioE());
		Unidad unidadN = UnidadDAO.getInstance().toNegocio(re.getUnidadE());
		
		return new Reclamo(personaN, edificioN, re.getUbicacion(), re.getDescripcion(), unidadN);
	}

	public Reclamo findById(int numero) throws ReclamoException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		ReclamoEntity reclamoE = (ReclamoEntity) s.createQuery("from ReclamoEntity re where re.idReclamo = ?").setInteger(0, numero).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if(reclamoE == null) {
			throw new ReclamoException("No existe el Reclamo " + numero);
		}
		return toNegocio(reclamoE);
	}
	
	public List<Reclamo> getReclamosByDoc(String documento) {
		List<Reclamo> reclamosN = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.usuarioE.documento = ?").list();
		s.getTransaction().commit();
		s.close();
		for (ReclamoEntity re : reclamosE){
			reclamosN.add(toNegocio(re));
		}
		return reclamosN;
	}

	public List<Reclamo> getReclamosByUnidad(int codigo) {
		List<Reclamo> reclamosN = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.UnidadE.identificador = ?").list();
		s.getTransaction().commit();
		s.close();
		for(ReclamoEntity re: reclamosE) {
			reclamosN.add(toNegocio(re));
		}
		return reclamosN;
	}
	
}
