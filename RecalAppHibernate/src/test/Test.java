package test;

import java.util.List;

import controlador.Controlador;
import views.EdificioView;
import views.UnidadView;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		for(EdificioView e : edificios) {
			System.out.println(e.getNombre());
			
		}
//		List<UnidadView> unidades = Controlador.getInstancia().getUnidadesPorEdificio(codigo);
//		for(UnidadView u : unidades) {
//			System.out.println(u.getPiso());
//		}
		
		}

	}

