package test;

import java.util.List;
import java.util.Set;

import controlador.Controlador;
import daos.PersonaDAO;
import modelo.Persona;
import views.EdificioView;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Trae todos los edificios
//		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
//		for (EdificioView e : edificios) {
//			System.out.println(e.getNombre());
//
//		}
		// busca una unidad de un edificio
//		try {
////		UnidadView unidadV = null;
//			unidadV = Controlador.getInstancia().buscarUnidad(5, "6", "5");
//			System.out.println(unidadV.getId());
//		}
//	} 	catch (UnidadException u) {
//		System.out.println(u.getMessage());
//	}

		// trae las unidades por edificio
//		try {
//			List<UnidadView> unidades = null;
//			unidades = Controlador.getInstancia().getUnidadesPorEdificio(2);
//			for(UnidadView u : unidades) {
//				System.out.println(u.getId());
//			}
//		} catch (EdificioException e) {
//			System.out.println(e.getMessage());
//		}
		// trae las personas habilitadas por edificio
//		try {
//			List<PersonaView> personas = null;
//			personas = Controlador.getInstancia().habilitadosPorEdificio(1);
//			for(PersonaView p : personas) {
//				System.out.println(p.getDocumento());
//			}
//		} catch (EdificioException e) {
//			System.out.println(e.getMessage());
//		}

		// Agrega una persona como duenio a una unidad
//		try {
//			Controlador.getInstancia().agregarDuenioUnidad(1, "10", "6", "DNI30616697");
//		} catch (UnidadException | PersonaException e1) {
//			
//			System.out.println(e1.getMessage());
//		}

		// Alquila una unidad a una persona
//		try {
//			Controlador.getInstancia().alquilarUnidad(1, "10", "6", "DNI41614393");
//		} catch (UnidadException | PersonaException e) {
//			System.out.println(e.getMessage());
//		}

//		try {
//			Controlador.getInstancia().habitarUnidad(1, "10", "6");
//		} catch (UnidadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// Genera un nuevo reclamo
//		int codigo = 1;
//		String piso = "3";
//		String numero = "5";
//		String documento = "DNI30834066";
//		String ubicacion = "Cocina";
//		String descripcion = "Perdida de gas";
//		try {
//			Controlador.getInstancia().agregarReclamo(codigo, piso, numero, documento, ubicacion, descripcion);
//		} catch (EdificioException | UnidadException | PersonaException e) {
//			// TODO Auto-generated catch block
//			e.getMessage();
//		}

		// Cambia el estado de un reclamo
//		Estado estado = Estado.abierto;
//		try {
//			Controlador.getInstancia().cambiarEstado(2, estado);
//		} catch (ReclamoException e) {
//			// TODO Auto-generated catch block
//			e.getMessage();
//		}

		// Devuelve los reclamos por edificio
//		List<ReclamoView> reclamosporEdif = new ArrayList<ReclamoView>();
//		try {
//			reclamosporEdif = Controlador.getInstancia().reclamosPorEdificio(1);
//			System.out.println(reclamosporEdif.get(1).getDescripcion());
//			System.out.println(reclamosporEdif.get(0).getDescripcion());
//		} catch (EdificioException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		Devuelve los reclamos por unidad
//		List<ReclamoView> reclamosXunid = new ArrayList<ReclamoView>();
//		try {
//			reclamosXunid = Controlador.getInstancia().reclamosPorUnidad(1, "3", "5");
//			System.out.println(reclamosXunid.get(1).getDescripcion());
//			System.out.println(reclamosXunid.get(0).getDescripcion());
//		} catch (UnidadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// Obtiene un reclamo dado un codigo
//		ReclamoView reclamoV = null;
//		try {
//			reclamoV = Controlador.getInstancia().reclamosPorNumero(2);
//			System.out.println(reclamoV.getDescripcion());
//		} catch (ReclamoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		Devuelve los reclamos por persona
//		List<ReclamoView> reclamosXpersona = new ArrayList<ReclamoView>();
//		try {
//			reclamosXpersona = Controlador.getInstancia().reclamosPorPersona("DNI30834066");
//			System.out.println(reclamosXpersona.get(0).getDescripcion());
//			System.out.println(reclamosXpersona.get(1).getDescripcion());
//
//		} catch (PersonaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// agregaImagen a un reclamo
//		try {
//			Controlador.getInstancia().agregarImagenAReclamo(1, "C:\\Users\\aearm\\Documents\\ReclamAppFTP\\idReclamo1.jpg", "jpg");
//		} catch (ReclamoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//
//		try {
//			List<PersonaView> duenios = Controlador.getInstancia().dueniosPorEdificio(1);
//			for(PersonaView d: duenios) {
//				System.out.println(d.getNombre());
//			}
//		} catch (EdificioException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// Transferir unidad
//		try {
//			Controlador.getInstancia().transferirUnidad(1, "10", "6", "DNI41614393");
//		} catch (UnidadException | PersonaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//Agrega imagen reclamo
//		try {
//			Controlador.getInstancia().agregarImagenAReclamo(1, "humedadPared.jpg", "jpg");
//		} catch (ReclamoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//Busca persona
//		Persona p = PersonaDAO.getInstance().findPersonaById("DNI11223344");
//		System.out.println(p);

		//Eliminar una persona
//		try {
//			Controlador.getInstancia().eliminarPersona("DNI41065529");
//		} catch (PersonaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Set<String> edificios = Controlador.getInstancia().edificiosByPersona("DNI29988738");
		for(String ev : edificios) {
			System.out.println(ev);
		}

	}
}
