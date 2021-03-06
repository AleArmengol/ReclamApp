package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import daos.UnidadDAO;
import views.EdificioView;

public class Edificio implements Comparable<Edificio>{

	private int codigo;
	private String nombre;
	private String direccion;
	private List<Unidad> unidades;

	public Edificio(int codigo, String nombre, String direccion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		unidades = new ArrayList<Unidad>();
	}

	public void agregarUnidad(Unidad unidad) {
		unidades.add(unidad);
	}

	public Set<Persona> habilitados() {
		Set<Persona> habilitados = new HashSet<Persona>();
		this.unidades = this.getUnidades();
		for (Unidad unidad : unidades) {
			List<Persona> duenios = unidad.getDuenios();
			for (Persona p : duenios)
				habilitados.add(p);
			List<Persona> inquilinos = unidad.getInquilinos();
			for (Persona p : inquilinos)
				habilitados.add(p);
		}
		return habilitados;
	}

	public List<Unidad> getUnidades() {
		if (unidades.size() == 0) { // DUDOSO PREGUNTAR unidades == null??
			unidades = UnidadDAO.getInstance().getUnidades(this.codigo);
		}
		return unidades;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public Set<Persona> duenios() {
		Set<Persona> resultado = new HashSet<Persona>();
		unidades = getUnidades();
		for (Unidad unidad : unidades) {
			List<Persona> duenios = unidad.getDuenios();
			for (Persona p : duenios)
				resultado.add(p);
		}
		return resultado;
	}

	public Set<Persona> habitantes() {
		Set<Persona> resultado = new HashSet<Persona>();
		for (Unidad unidad : unidades) {
			if (unidad.estaHabitado()) {
				List<Persona> inquilinos = unidad.getInquilinos();
				if (inquilinos.size() > 0)
					for (Persona p : inquilinos)
						resultado.add(p);
				else {
					List<Persona> duenios = unidad.getDuenios();
					for (Persona p : duenios)
						resultado.add(p);
				}
			}
		}
		return resultado;
	}
	
	




	public EdificioView toView() {
		return new EdificioView(codigo, nombre, direccion);
	}

	@Override
	public int compareTo(Edificio o) {
		// TODO Auto-generated method stub
		if( this.codigo == o.getCodigo())
			return 0;
		else
			return 1;
	}
}
