package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.EdificioEntity;
import entities.PersonaEntity;
import entities.ReclamoEntity;
import entities.UnidadEntity;
import exceptions.ReclamoException;
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
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.codigo = ?").setInteger(0, codigo).list();
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
<<<<<<< HEAD
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.identificador = ?").setInteger(0, codigo).list();
=======
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.UnidadE.identificador = ?").list();
>>>>>>> d3b550f607760f7ec2e5a6e6b753d16a1799679f
		s.getTransaction().commit();
		s.close();
		for(ReclamoEntity re: reclamosE) {
			reclamosN.add(toNegocio(re));
		}
		return reclamosN;
	}
<<<<<<< HEAD

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
		return new ReclamoEntity(personaE, edificioE, reclamo.getUbicacion(), reclamo.getDescripcion(),UnidadE);
	}

	public List<Reclamo> getReclamosByPersona(String documento) {
		List<Reclamo> reclamosN = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.documento = ?").setString(0, documento).list();
		s.getTransaction().commit();
		s.close();
		for(ReclamoEntity re: reclamosE) {
			reclamosN.add(toNegocio(re));
		}
		return reclamosN;
	}

	public void update(Reclamo reclamo) {
		ReclamoEntity toUpdate = toEntity(reclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(toUpdate);
		s.getTransaction().commit();
		s.close();
	}

=======
	
>>>>>>> d3b550f607760f7ec2e5a6e6b753d16a1799679f
}
