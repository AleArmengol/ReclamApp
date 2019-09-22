package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.DueniosDAO;
import daos.InquilinosDAO;
import daos.UnidadDAO;
import entities.UnidadSoloYSobreCargada;
import exceptions.UnidadException;
import views.EdificioView;
import views.UnidadView;

public class Unidad implements UnidadSoloYSobreCargada{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String piso;
	private String numero;
	private boolean habitado;
	private Edificio edificio;
	private List<Persona> duenios;
	private List<Persona> inquilinos;
	
	public Unidad(int id, String piso, String numero, Edificio edificio) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = false;
		this.edificio = edificio;
		this.duenios = new ArrayList<Persona>();
		this.inquilinos = new ArrayList<Persona>();
	}

	public void transferir(Persona nuevoDuenio) {
		duenios = new ArrayList<Persona>();
		duenios.add(nuevoDuenio);
	}
	
	public void agregarDuenio(Persona duenio) {
		duenios.add(duenio);
		DueniosDAO.getInstance().save(duenio, this);
	}
	
	public void alquilar(Persona inquilino) throws UnidadException {
		if(!this.habitado) {
			this.habitado = true;
			inquilinos = new ArrayList<Persona>();
			inquilinos.add(inquilino);
			InquilinosDAO.getInstance().save(inquilino, this);
			UnidadDAO.getInstance().update(this); //BUG TODO
		}
		else
			throw new UnidadException("La unidad esta ocupada");
	}

	public void agregarInquilino(Persona inquilino) {
		inquilinos.add(inquilino);
		InquilinosDAO.getInstance().save(inquilino, this);
	}
	
	public boolean estaHabitado() {
		return habitado;
	}
	
	public void liberar() {
		this.inquilinos = new ArrayList<Persona>();
		this.habitado = false;
		UnidadDAO.getInstance().update(this);
	}
	//si la lista de inquilinos esta vacia y la unidad esta habitada, quiere decir que el duenio la esta ocupando
	public void habitar() throws UnidadException {
		if(this.habitado)
			throw new UnidadException("La unidad ya esta habitada");
		else {
			this.habitado = true;
			UnidadDAO.getInstance().update(this);
		}
		
	}
	
	public int getId() {
		return id;
	}

	public String getPiso() {
		return piso;
	}

	public String getNumero() {
		return numero;
	}

	
	public Edificio getEdificio() {
		return edificio;
	}

	public List<Persona> getDuenios() {
		return DueniosDAO.getInstance().getDueniosByUnidad(this.id);
	}

	public List<Persona> getInquilinos() {
		return InquilinosDAO.getInstance().getInquilinosByUnidad(this.id);
	}
	

	public boolean isHabitado() {
		return habitado;
	}

	public void setHabitado(boolean habitado) {
		this.habitado = habitado;
	}

	public UnidadView toView() {
		EdificioView auxEdificio = edificio.toView();
		return new UnidadView(id, piso, numero, habitado, auxEdificio);
	}
}
