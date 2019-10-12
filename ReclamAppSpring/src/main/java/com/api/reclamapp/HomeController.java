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
import exceptions.UnidadException;
import views.EdificioView;
import views.PersonaView;
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
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/getEdificios", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getEdificios() throws JsonProcessingException {
		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(edificios);
	}
	
	@RequestMapping(value = "/getUnidadesPorEdificio", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getUnidadesPorEdificio(@RequestParam(value="codigo", required=true) int codigo) throws JsonProcessingException {
		try {
			List<UnidadView> unidades = Controlador.getInstancia().getUnidadesPorEdificio(codigo);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(unidades);
		}
		catch (EdificioException e) { 
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/getDueniosPorEdificio", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getDueniosPorEdificio(@RequestParam(value="codigo", required=true) int codigo) throws JsonProcessingException {
		try {
			List<PersonaView> duenios = Controlador.getInstancia().dueniosPorEdificio(codigo);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(duenios);
		}
		catch (EdificioException e) {	
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/getHabilitadosPorEdificio", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getHabilidatosPorEdificio(@RequestParam(value="codigo", required=true) int codigo) throws JsonProcessingException {
		try {
			List<PersonaView> habilitados = Controlador.getInstancia().habilitadosPorEdificio(codigo);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(habilitados);
		}
		catch (EdificioException e) {	
			return e.getMessage();
		}
	}
	
	
	@RequestMapping(value = "/getDueniosPorUnidad", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getDueniosPorUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero ) throws JsonProcessingException {
		
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
	
	
	
	
	
	
}
