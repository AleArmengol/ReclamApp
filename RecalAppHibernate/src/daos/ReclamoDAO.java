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
		List<ReclamoEntity> reclamosE = s.createQuery("FROM ReclamoEntity re WHERE re.edificioE.codigo = ?")
				.setInteger(0, codigo).list();
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
		Unidad unidadN = null;
		if(re.getUnidadE() != null) {
			unidadN = UnidadDAO.getInstance().toNegocio(re.getUnidadE());
		}
		System.out.println("ESTADO DENTRO DE RECLAMO DAO LINEA 57 " + re.getEstado());
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
		reclamo.setNumero(aGuardar.getIdReclamo());
		s.close();
	}

	ReclamoEntity toEntity(Reclamo reclamo) {
		UnidadEntity unidadE = null;
		PersonaEntity personaE = PersonaDAO.getInstance().toEntity(reclamo.getUsuario());
		EdificioEntity edificioE = EdificioDAO.getInstance().toEntity(reclamo.getEdificio());
		if(reclamo.getUnidad() != null) {
			unidadE = UnidadDAO.getInstance().toEntity(reclamo.getUnidad());
		}
		return new ReclamoEntity(reclamo.getNumero(), personaE, edificioE, reclamo.getUbicacion(),
				reclamo.getDescripcion(), unidadE, reclamo.getEstadoToString());
	}

	public void update(Reclamo reclamo) {
		ReclamoEntity toUpdate = toEntity(reclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(toUpdate);
		s.getTransaction().commit();
		s.close();
	}

	public int getNumeroReclamoRecienCreado(String documento, int codigo, String ubicación, String descripcion,
			int id) {
		ReclamoEntity reclamoE = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		reclamoE = (ReclamoEntity) s.createQuery(
				"FROM ReclamoEntity re WHERE re.usuarioE.documento = ? AND re.edificioE.codigo = ? AND re.ubicacion = ? AND re.descripcion = ? AND re.unidadE.identificador = ?")
				.setString(0, documento).setInteger(1, codigo).setString(2, ubicación).setString(3, descripcion)
				.setInteger(4, id); // error cannot be cast to entities.ReclamoEntity BUG
		return reclamoE.getIdReclamo();
	}

	public List<Reclamo> getAllReclamos() {
		List<Reclamo> reclamosN = new ArrayList<Reclamo>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction().commit();
		List<ReclamoEntity> reclamosE = s.createQuery("from ReclamoEntity").list();
		s.close();
		for (ReclamoEntity re : reclamosE) {
			reclamosN.add(toNegocio(re));
		}
		return reclamosN;
	}

}
