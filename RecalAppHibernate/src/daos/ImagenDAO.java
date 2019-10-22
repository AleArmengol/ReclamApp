package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import entities.ImagenEntity;
import entities.ReclamoEntity;
import exceptions.ReclamoException;
import hibernate.HibernateUtil;
import modelo.Imagen;
import modelo.Reclamo;

public class ImagenDAO {

	private static ImagenDAO instance;

	private ImagenDAO() {
	}

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

	public List<Imagen> getImagenesByReclamo(int numero) {
		List<Imagen> imagenesN = new ArrayList<Imagen>();
		List<ImagenEntity> imagenesE = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		imagenesE = s.createQuery("FROM ImagenEntity ie WHERE ie.reclamoE.idReclamo = ?").setInteger(0, numero).list();
		s.getTransaction().commit();
		s.close();
		for (ImagenEntity imagenE : imagenesE) {
			imagenesN.add(toNegocio(imagenE));
		}
		return imagenesN;
	}

	private Imagen toNegocio(ImagenEntity imagenE) {
		return new Imagen(imagenE.getPath(), imagenE.getTipo());
	}

}
