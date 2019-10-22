package com.api.reclamapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import views.EdificioView;
import views.Estado;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/getEdificios", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getEdificios() throws JsonProcessingException {
		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(edificios);
	}

	@RequestMapping(value = "/getUnidadesPorEdificio", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getUnidadesPorEdificio(
			@RequestParam(value = "codigo", required = true) int codigo) throws JsonProcessingException {
		try {
			List<UnidadView> unidades = Controlador.getInstancia().getUnidadesPorEdificio(codigo);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(unidades);
		} catch (EdificioException e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getDueniosPorEdificio", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getDueniosPorEdificio(
			@RequestParam(value = "codigo", required = true) int codigo) throws JsonProcessingException {
		try {
			List<PersonaView> duenios = Controlador.getInstancia().dueniosPorEdificio(codigo);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(duenios);
		} catch (EdificioException e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getHabilitadosPorEdificio", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getHabilidatosPorEdificio(
			@RequestParam(value = "codigo", required = true) int codigo) throws JsonProcessingException {
		try {
			List<PersonaView> habilitados = Controlador.getInstancia().habilitadosPorEdificio(codigo);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(habilitados);
		} catch (EdificioException e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getDueniosPorUnidad", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getDueniosPorUnidad(@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero) throws JsonProcessingException {

		try {
			List<PersonaView> duenios = Controlador.getInstancia().dueniosPorUnidad(codigo, piso, numero);
			System.out.println(duenios.get(0).getDocumento());
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(duenios);
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getInquilinosPorUnidad", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getInquilinosPorUnidad(
			@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero) throws JsonProcessingException {

		try {
			List<PersonaView> inquilinos = Controlador.getInstancia().inquilinosPorUnidad(codigo, piso, numero);
			System.out.println(inquilinos.get(0).getDocumento());
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(inquilinos);
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/transferirUnidad", method = RequestMethod.PUT)
	public @ResponseBody <json> void transferirUnidad(@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero,
			@RequestParam(value = "documento", required = true) String documento) {
		try {
			Controlador.getInstancia().transferirUnidad(codigo, piso, numero, documento);
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/agregarDuenioUnidad", method = RequestMethod.POST)
	public @ResponseBody <json> void agregarDuenioUnidad(@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero,
			@RequestParam(value = "documento", required = true) String documento) {
		try {
			Controlador.getInstancia().agregarDuenioUnidad(codigo, piso, numero, documento);
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/alquilarUnidad", method = RequestMethod.POST)
	public @ResponseBody <json> void alquilarUnidad(@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero,
			@RequestParam(value = "documento", required = true) String documento) {
		try {
			Controlador.getInstancia().alquilarUnidad(codigo, piso, numero, documento);
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/agregarInquilinoUnidad", method = RequestMethod.POST)
	public @ResponseBody <json> void agregarInquilinoUnidad(@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero,
			@RequestParam(value = "documento", required = true) String documento) {
		try {
			Controlador.getInstancia().agregarInquilinoUnidad(codigo, piso, numero, documento);
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/liberarUnidad", method = RequestMethod.PUT)
	public @ResponseBody <json> void liberarUnidad(@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero) {
		try {
			Controlador.getInstancia().liberarUnidad(codigo, piso, numero);
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}

	}

	@RequestMapping(value = "/habitarUnidad", method = RequestMethod.PUT)
	public @ResponseBody <json> void habitarUnidad(@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero) {
		try {
			Controlador.getInstancia().habitarUnidad(codigo, piso, numero);
		} catch (UnidadException e) {
			e.getMessage();
		}
	}

	@RequestMapping(value = "/agregarPersona", method = RequestMethod.POST)
	public @ResponseBody <json> void agregarPersona(
			@RequestParam(value = "documento", required = true) String documento,
			@RequestParam(value = "nombre", required = true) String nombre) {
		Controlador.getInstancia().agregarPersona(documento, nombre);
	}

	@RequestMapping(value = "/eliminarPersona", method = RequestMethod.DELETE)
	public @ResponseBody <json> void eliminarPersona(
			@RequestParam(value = "documento", required = true) String documento) throws PersonaException {
		try {
			Controlador.getInstancia().eliminarPersona(documento);
		} catch (PersonaException e) {
			e.getMessage();
		}
	}

	@RequestMapping(value = "/reclamosPorEdificio", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String reclamosPorEdificio(@RequestParam(value = "codigo", required = true) int codigo)
			throws JsonProcessingException, EdificioException {
		try {
			List<ReclamoView> reclamos = Controlador.getInstancia().reclamosPorEdificio(codigo);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(reclamos);
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/reclamosPorUnidad", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String reclamosPorUnidad(@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero)
			throws JsonProcessingException, UnidadException {
		try {
			List<ReclamoView> reclamos = Controlador.getInstancia().reclamosPorUnidad(codigo, piso, numero);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(reclamos);
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/reclamosPorNumero", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String reclamosPorNumero(@RequestParam(value = "numero", required = true) int numero)
			throws JsonProcessingException, ReclamoException {
		try {
			ReclamoView reclamo = Controlador.getInstancia().reclamosPorNumero(numero);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(reclamo);
		} catch (ReclamoException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/reclamosPorPersona", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String reclamosPorPersona(
			@RequestParam(value = "documento", required = true) String documento)
			throws JsonProcessingException, PersonaException {
		try {
			List<ReclamoView> reclamos = Controlador.getInstancia().reclamosPorPersona(documento);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(reclamos);
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/agregarReclamo", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String agregarReclamo(@RequestParam(value = "codigo", required = true) int codigo,
			@RequestParam(value = "piso", required = true) String piso,
			@RequestParam(value = "numero", required = true) String numero,
			@RequestParam(value = "documento", required = true) String documento,
			@RequestParam(value = "ubicación", required = true) String ubicación,
			@RequestParam(value = "descripcion", required = true) String descripcion)
			throws EdificioException, UnidadException, PersonaException {

		int numeroReclamo = Controlador.getInstancia().agregarReclamo(codigo, piso, numero, documento, ubicación,
				descripcion);
		String mensaje = "Tu reclamo se agrego correctamente. Tu numero de reclamo es: " + numeroReclamo;
		return mensaje;
	}

	@RequestMapping(value = "/agregarImagenAReclamo", method = RequestMethod.POST)
	public @ResponseBody <json> void agregarImagenAReclamo(@RequestParam(value = "numero", required = true) int numero,
			@RequestParam(value = "nombreImagen", required = true) String nombreImagen,
			@RequestParam(value = "tipo", required = true) String tipo) throws ReclamoException {

		Controlador.getInstancia().agregarImagenAReclamo(numero, nombreImagen, tipo);
	}

	@RequestMapping(value = "/cambiarEstado", method = RequestMethod.PUT)
	public @ResponseBody <json> void cambiarEstado(@RequestParam(value = "numero", required = true) int numero,
			@RequestParam(value = "estado", required = true) String estado) throws ReclamoException {
		try {
			Estado est = null;
			if (estado.equals("nuevo"))
				est = Estado.nuevo;
			else if (estado.equals("abierto"))
				est = Estado.abierto;
			else if (estado.equals("enProceso"))
				est = Estado.enProceso;
			else if (estado.equals("desestimado"))
				est = Estado.desestimado;
			else if (estado.equals("anulado"))
				est = Estado.anulado;
			else
				est = Estado.terminado;
			Controlador.getInstancia().cambiarEstado(numero, est);
		} catch (ReclamoException e) {
			e.getMessage();
		}
	}

}
