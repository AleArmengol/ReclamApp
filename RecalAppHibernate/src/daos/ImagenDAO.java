package daos;

import org.hibernate.Session;

import entities.ImagenEntity;
import entities.ReclamoEntity;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Imagen;
import modelo.Reclamo;
public class ImagenDAO {
	
	private static ImagenDAO instance;
	private ImagenDAO() {}

	public static ImagenDAO getInstance() {
		if (instance == null)
			instance = new ImagenDAO();
		return instance;
	}
	
	
	public void save(Imagen imagen, int numeroReclamo) {
		ImagenEntity aGuardar = toEntity(imagen, numeroReclamo);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGuardar);
		s.getTransaction().commit();
		s.close();
	}

	private ImagenEntity toEntity(Imagen imagen, int numeroReclamo) {
		Reclamo reclamoN = null;
		try {
			reclamoN = ReclamoDAO.getInstance().findById(numeroReclamo);
		} catch (ReclamoException e) {
			e.printStackTrace();
		}
		ReclamoEntity reclamoE = ReclamoDAO.getInstance().toEntity(reclamoN);
		return new ImagenEntity(imagen.getPath(), imagen.getTipo(), reclamoE);
	}
	
	
	
	
	
	
	
	
	
}
