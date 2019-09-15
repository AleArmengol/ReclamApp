package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import daos.EdificioDAO;
import daos.PersonaDAO;
import daos.ReclamoDAO;
import daos.UnidadDAO;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import modelo.Edificio;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import views.EdificioView;
import views.Estado;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

public class Controlador {

	private static Controlador instancia;
	
	private Controlador() {
		
	}
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	public List<EdificioView> getEdificios(){
		List<EdificioView> edificiosV = new ArrayList<EdificioView>();
		List<Edificio> edificiosN = EdificioDAO.getInstance().getEdificios();
		for(Edificio en : edificiosN) {
			edificiosV.add(en.toView());
		}
		return edificiosV;
	}
	
	public List<UnidadView> getUnidadesPorEdificio(int codigo) throws EdificioException{
		List<UnidadView> resultado = new ArrayList<UnidadView>();
		Edificio edificio = buscarEdificio(codigo);
		List<Unidad> unidades = edificio.getUnidades(); //revisar metodo getUnidades
		for(Unidad unidad : unidades)
			resultado.add(unidad.toView());
		return resultado;
	}
	
	public List<PersonaView> habilitadosPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habilitados = edificio.habilitados();
		for(Persona persona : habilitados)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> dueniosPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> duenios = edificio.duenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> habitantesPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habitantes = edificio.duenios();
		for(Persona persona : habitantes)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> duenios = unidad.getDuenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}

	public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> inquilinos = unidad.getInquilinos();
		for(Persona persona : inquilinos)
			resultado.add(persona.toView());
		return resultado;
	}
	
	public void transferirUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.transferir(persona);
	}

	public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarDuenio(persona);
	}

	public void alquilarUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.alquilar(persona);// hay que hacer el save de inquilino en la Entity inquilino TODO
	}

	public void agregarInquilinoUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarInquilino(persona);// hay que hacer el save de inquilino en la Entity inquilino TODO
	}

	public void liberarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.liberar();//falta hacer el update de la unidad TODO
	}
	
	public void habitarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.habitar();// hacer update en unidad TODO
	}
	
	public void agregarPersona(String documento, String nombre) {
		Persona persona = new Persona(documento, nombre);
		persona.save();
	}
	
	public void eliminarPersona(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		persona.delete();
	}
	
	public List<ReclamoView> reclamosPorEdificio(int codigo) throws EdificioException{
		List<ReclamoView> reclamosV = new ArrayList<ReclamoView>(); //TODO
		List<Reclamo> reclamosN = new ArrayList<Reclamo>();
		Edificio edificioN = buscarEdificio(codigo);
		reclamosN = ReclamoDAO.getInstance().getReclamosByEdificio(codigo);
		for(Reclamo rn: reclamosN) {
			reclamosV.add(rn.toView());
		}
		return reclamoV;
	}
	
	public List<ReclamoView> reclamosPorUnidad(int codigo, String piso, String numero) {
		List<ReclamoView> reclamosV = new ArrayList<ReclamoView>(); //TODO
		List<Reclamo> reclamosN = ReclamoDAO.getInstance().getReclamosByUnidad(codigo);
		for(Reclamo rn : reclamosN) {
			reclamosV.add(rn.toView());
		}
		return reclamosV;
	}
	
	public ReclamoView reclamosPorNumero(int numero) { //PREGUNTAR QUE ES? ES LO MISMO QUE FIND BY ID?
		ReclamoView resultado = null; //TODO
		return resultado;
	}
	
	public List<ReclamoView> reclamosPorPersona(String documento) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>(); //TODO
		return resultado;
	}
 
	public int agregarReclamo(int codigo, String piso, String numero, String documento, String ubicación, String descripcion) throws EdificioException, UnidadException, PersonaException {
		Edificio edificio = buscarEdificio(codigo);
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		Reclamo reclamo = new Reclamo(persona, edificio, ubicación, descripcion, unidad);
		reclamo.save(); //TODO
		return reclamo.getNumero();
	}
	
	public void agregarImagenAReclamo(int numero, String direccion, String tipo) throws ReclamoException {
		Reclamo reclamo = buscarReclamo(numero); 
		reclamo.agregarImagen(direccion, tipo); //TODO
	}
	
	public void cambiarEstado(int numero, Estado estado) throws ReclamoException { //TODO
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.cambiarEstado(estado);
		reclamo.update();
	}
	
	private Edificio buscarEdificio(int codigo) throws EdificioException {
		return EdificioDAO.getInstance().findById(codigo);
	}

	private Unidad buscarUnidad(int codigo, String piso, String numero) throws UnidadException{
		return UnidadDAO.getInstance().findUnidad(codigo, piso, numero);
	}	
	
	private Persona buscarPersona(String documento) throws PersonaException {
		return PersonaDAO.getInstance().findPersonaById(documento);
	}
	
	private Reclamo buscarReclamo(int numero) throws ReclamoException {
		return ReclamoDAO.getInstance().findById(numero);
	}
}
