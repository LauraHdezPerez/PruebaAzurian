package mx.estancias.azurian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mx.estancias.azurian.entity.Person;
import mx.estancias.azurian.repository.PersonRepository;
import mx.estancias.azurian.service.PersonService;

import java.util.*;



@Controller
public class HomeController {
	// OBTENER -> Ejecuta este método para encontrar la vista home.html. Responder
		// al navegador
	@Autowired
	private PersonRepository _personRepository;
	@Autowired
	private PersonService personService;
	
	@GetMapping(path = "/")
	public String index(Person person,Model model) {
		Iterable<Person> persons = personService.findAll();
		model.addAttribute("persons", persons);
		return "app.html";
	}

}
