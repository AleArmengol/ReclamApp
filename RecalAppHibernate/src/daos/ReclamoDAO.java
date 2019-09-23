package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.sun.xml.internal.ws.server.sei.ValueGetter;

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
import views.Estado;

public class ReclamoDAO {

	private static ReclamoDAO instance;

	private ReclamoDAO() {

	}

	public static ReclamoDAO getInstance() {
		if (instance == null)
			instance = new ReclamoDAO();
		return instance;
	}

	public List<Reclamo> getReclamosByEdificio(int codigo) {
		List<Reclamo> reclamosN = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.edificioE.codigo = ?").setInteger(0, codigo)
				.list();
		s.getTransaction().commit();
		s.close();
		for (ReclamoEntity re : reclamosE) {
			reclamosN.add(toNegocio(re));
		}
		return reclamosN;
	}

	private Reclamo toNegocio(ReclamoEntity re) {
		Persona personaN = PersonaDAO.getInstance().toNegocio(re.getUsuarioE());
		Edificio edificioN = EdificioDAO.getInstance().toNegocio(re.getEdificioE());
		Unidad unidadN = UnidadDAO.getInstance().toNegocio(re.getUnidadE());
		return new Reclamo(re.getIdReclamo(), personaN, edificioN, re.getUbicacion(), re.getDescripcion(), unidadN,
				re.stringToEstado(re.getEstado()));
	}

	public Reclamo findById(int numero) throws ReclamoException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		ReclamoEntity reclamoE = (ReclamoEntity) s.createQuery("from ReclamoEntity re where re.idReclamo = ?")
				.setInteger(0, numero).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (reclamoE == null) {
			throw new ReclamoException("No existe el Reclamo " + numero);
		}
		return toNegocio(reclamoE);
	}



	public List<Reclamo> getReclamosByPersona(String documento) {
		List<Reclamo> reclamosN = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.usuarioE.documento = ?")
				.setString(0, documento).list();
		s.getTransaction().commit();
		s.close();
		for (ReclamoEntity re : reclamosE) {
			reclamosN.add(toNegocio(re));
		}
		return reclamosN;
	}

	public List<Reclamo> getReclamosByUnidad(int codigo) {
		List<Reclamo> reclamosN = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.unidadE.identificador = ?")
				.setInteger(0, codigo).list();
		s.getTransaction().commit();
		s.close();
		for (ReclamoEntity re : reclamosE) {
			reclamosN.add(toNegocio(re));
		}
		return reclamosN;
	}

	public void save(Reclamo reclamo) {
		ReclamoEntity aGuardar = toEntity(reclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGuardar);
		s.getTransaction().commit();
		s.close();
	}

	ReclamoEntity toEntity(Reclamo reclamo) {
		PersonaEntity personaE = PersonaDAO.getInstance().toEntity(reclamo.getUsuario());
		EdificioEntity edificioE = EdificioDAO.getInstance().toEntity(reclamo.getEdificio());
		UnidadEntity unidadE = UnidadDAO.getInstance().toEntity(reclamo.getUnidad());
		return new ReclamoEntity(reclamo.getNumero(), personaE, edificioE, reclamo.getUbicacion(), reclamo.getDescripcion(), unidadE, reclamo.getEstadoToString());
	}

	public void update(Reclamo reclamo) {
		ReclamoEntity toUpdate = toEntity(reclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(toUpdate);
		s.getTransaction().commit();
		s.close();
	}

}
