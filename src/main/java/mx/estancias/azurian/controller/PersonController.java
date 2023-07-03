package mx.estancias.azurian.controller;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.estancias.azurian.entity.Person;
import mx.estancias.azurian.repository.PersonRepository;
import mx.estancias.azurian.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepository _personRepository;
	@Autowired
	private PersonService personService;
	
	
	@GetMapping("/app")
	public String list(Model model) {
		Iterable<Person> persons = personService.findAll();
		model.addAttribute("persons", persons);
		return "app.html";
		
	}
	

	@GetMapping("/fnd")
	public String read(Person person, Model model) {
		String url = "";
		
		if(person.getId() != null) {
			LinkedList<Person> persons = new LinkedList<Person>();
			
			Optional<Person> oPeron = personService.findById(person.getId());
			persons.add(((oPeron.get() != null) ? oPeron.get() : null));
			
			model.addAttribute("persons", persons);
			url = "qry.html";
		
		}
		else {
			url = "redirect:/";
		}
		
		return url;
	}
	
	// Create a new user
	@GetMapping("/add")
	public String add(Person person) {
		return "add.html";
	}
	
	@PostMapping("/create")
	public String create(Person person) {		
		personService.save(person);
		return "redirect:/";
	}
	
	// Update an user
	@GetMapping("/upd/{id}")
	// public String upd(@PathVariable(value = "id") Integer personId, Model model) {
	public String upd(Person person, Model model) {
		Optional<Person> oPerson = personService.findById(person.getId());
		model.addAttribute("person", oPerson);
		return "upd.html";
	}
	
	@PostMapping("/save")
	public String save(Person person) {		
		if (person != null) {		
			personService.save(person);
			}
		return "redirect:/";
	}
	
	// Delete an User
	@GetMapping("/del/{id}")
	public String delete(@PathVariable(value = "id") Integer personId) {		
		personService.deleteById(personId);
		return "redirect:/";
	}
}
