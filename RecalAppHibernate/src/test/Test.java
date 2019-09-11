package test;

import java.util.List;

import controlador.Controlador;
import daos.PersonaDAO;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.UnidadException;
import views.EdificioView;
import views.PersonaView;
import views.UnidadView;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		for (EdificioView e : edificios) {
			System.out.println(e.getNombre());

		}
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
		try {
			Controlador.getInstancia().agregarDuenioUnidad(1, "10", "6", "DNI30616697");
		} catch (UnidadException | PersonaException e1) {
			
			System.out.println(e1.getMessage());
		}

	}
}
