package test;

import java.util.List;

import controlador.Controlador;
import daos.UnidadDAO;
import exceptions.EdificioException;
import exceptions.UnidadException;
import views.EdificioView;
import views.PersonaView;
import views.UnidadView;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		for(EdificioView e : edificios) {
			System.out.println(e.getNombre());
			
		}
		
//		try {
//			List<UnidadView> unidades = null;
//			unidades = Controlador.getInstancia().getUnidadesPorEdificio(2);
//			for(UnidadView u : unidades) {
//				System.out.println(u.getId());
//			}
//		} catch (EdificioException e) {
//			System.out.println(e.getMessage());
//		}
		
		try {
			List<PersonaView> personas = null;
			personas = Controlador.getInstancia().habilitadosPorEdificio(1);
			for(PersonaView p : personas) {
				System.out.println(p.getNombre());
			}
		} catch (EdificioException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	
}

