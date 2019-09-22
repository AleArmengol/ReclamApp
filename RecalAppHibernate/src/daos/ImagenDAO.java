package daos;

import org.hibernate.Session;

import hibernate.HibernateUtil;
public class ImagenDAO {
	
	private static ImagenDAO instance;
	private ImagenDAO() {}

	public static ImagenDAO getInstance() {
		if (instance == null)
			instance = new ImagenDAO();
		return instance;
	}
	
	
	public void save(Imagen imagen) {
		
		ImagenEntity aGuardar = toEntity(imagen);
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(aGuardar);
		s.getTransaction().commit();
		s.close();
	}
	
	
	
	
	
	
	
	
	
}
