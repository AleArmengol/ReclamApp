package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import entities.DuenioEntity;
import entities.EdificioEntity;
import entities.InquilinoEntity;
import entities.UnidadEntity;
import entities.UnidadSoloYSobreCargada;
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
		if (instance == null)
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
		for (UnidadEntity eu : unidadesE) {
			unidadesN.add(toNegocio(eu));
		}

		return unidadesN;

	}

	public Unidad findUnidad(int codigo, String piso, String numero) throws UnidadException {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UnidadEntity unidadE = (UnidadEntity) s
				.createQuery("from UnidadEntity ue where ue.edificioE.codigo = ? and ue.piso = ? and ue.numero = ?")
				.setInteger(0, codigo).setString(1, piso).setString(2, numero).uniqueResult();
		s.getTransaction().commit();
		s.close();
		if (unidadE == null) {
			throw new UnidadException("No existe la Unidad" + numero + "en el piso" + piso + "del edificio" + codigo);
		}
		return toNegocio(unidadE);
	}

	Unidad toNegocio(UnidadEntity ue) {
		Edificio aux = EdificioDAO.getInstance().toNegocio(ue.getEdificioE()); // Para construir un objeto Unidad
		return new Unidad(ue.getIdentificador(), ue.getPiso(), ue.getNumero(), aux); // necesitamos un Objecto de
																						// Negocio Edificio
	}

	UnidadEntity toEntity(Unidad unidadN) {
		String habitado;
		// Obtengo los edificios
		EdificioEntity edificioE = null;
		try {
			Edificio edificio = EdificioDAO.getInstance().findById(unidadN.getEdificio().getCodigo());
			edificioE = EdificioDAO.getInstance().toEntity(edificio);
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Obtengo los duenios
		List<DuenioEntity> dueniosE = DueniosDAO.getInstance().getDueniosEntityByUnidad(unidadN.getId());

		if (unidadN.isHabitado()) {
			habitado = "S";
		} else {
			habitado = "N";
		}

		// Obtengo los inquilinos
		List<InquilinoEntity> inquilinosE = InquilinosDAO.getInstance().getInquilinosEntityByUnidad(unidadN.getId());

		UnidadEntity unidadE = new UnidadEntity(unidadN.getNumero(), unidadN.getPiso(), habitado, unidadN.getId(),
				edificioE);
		unidadE.setDueniosE(dueniosE);
		unidadE.setInquilinosE(inquilinosE);
		return unidadE;

	}

	UnidadEntity toEntity(UnidadSoloYSobreCargada unidadS) {
		Unidad aux = (Unidad) unidadS;
		String habitado2;
		if (aux.isHabitado()) {
			habitado2 = "S";
		} else {
			habitado2 = "N";
		}
		return new UnidadEntity(aux.getNumero(), aux.getPiso(), habitado2, aux.getId());
	}

	public void update(Unidad unidad) {
		UnidadEntity unidadE = toEntity(unidad);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(unidadE);
		s.getTransaction().commit();
		s.close();
	}

	public Unidad getUnidadById(int identificador) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		UnidadEntity unidadE = (UnidadEntity) s.createQuery("From UnidadEntity ue where ue.identificador = ?")
				.setInteger(0, identificador).uniqueResult();
		s.getTransaction().commit();
		s.close();
		return toNegocio(unidadE);

	}
}
