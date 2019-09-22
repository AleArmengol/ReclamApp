package modelo;

import controlador.Controlador;
import daos.DueniosDAO;
import daos.InquilinosDAO;
import daos.PersonaDAO;
import daos.UnidadDAO;
import entities.DuenioEntity;
import exceptions.UnidadException;
import views.PersonaView;

public class Persona {

	private String documento;
	private String nombre;
	
	public Persona(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public PersonaView toView() {
		return new PersonaView(documento, nombre);
	}
	
	public void save() {
		PersonaDAO.getInstance().save(this);
	}

	
	/*Primero preguntar si es duenio, si es duenio no lo puedo borrar, si no es duenio y no es inquilino, lo borro directamente
	 * si es inquilino, le pido la lista de unidades de las que es inquilino.
	 * De cada unidada de esa lista pregunto si la lista que tiene de inquilinos es igual a 1, si es asi hago el update de la unidad a liberada
	 * una vez que hice todo esto elimino el inquilino
	 * */
	public void delete() {
		//es duenio?
		if(!DueniosDAO.getInstance().isDuenio(this.documento)) {
			if(InquilinosDAO.getInstance().isInquilino(this.documento)) {
				Unidad unidadN = InquilinosDAO.getInstance().getUnidadInquilino(documento);
				try {
					if(Controlador.getInstancia().inquilinosPorUnidad(unidadN.getEdificio().getCodigo(), unidadN.getPiso(), unidadN.getNumero()).size() == 1) {
						unidadN.setHabitado(false);
						UnidadDAO.getInstance().update(unidadN);
					}
				} catch (UnidadException e) {
					e.printStackTrace();
				}	
			}
			PersonaDAO.getInstance().delete(this);
			
		}		
	}	

}
