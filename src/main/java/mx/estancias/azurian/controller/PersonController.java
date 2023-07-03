package mx.estancias.azurian.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public @ResponseBody Iterable<Person> list(){
		Iterable<Person> persons = personService.findAll();
		return persons;
	}
	

	@GetMapping("/all")
	public @ResponseBody Iterable<Person> read(){
		Iterable<Person> persons = personService.findAll();
		return persons;
	}
	
	//Read an user
	@GetMapping("/{id}")
	public @ResponseBody Optional<Person> read(@PathVariable(value = "id") Integer personId){
		Optional<Person> oPerson = personService.findById(personId);
		return oPerson;
	}
	
	// Create a new user
	@PostMapping
	public @ResponseBody Person create(@RequestBody Person person) {
		return personService.save(person);
	}
	
	//Update an user
		@PutMapping("/{id}")
		public @ResponseBody Person update(@RequestBody Person personDetails, @PathVariable(value = "id") Integer personId) {
			Optional<Person> oPerson = personService.findById(personId);
			
			if(oPerson.isPresent()) {
				oPerson.get().setNombre(personDetails.getNombre());
				oPerson.get().setApellidoPaterno(personDetails.getApellidoPaterno());
				oPerson.get().setApellidoMaterno(personDetails.getApellidoMaterno());
				
			}
			return personService.save(oPerson.get());
			
		}
		
		@GetMapping("/add")
		public String add(Person person) {
			return "views/add.html";
		}
		
		@PostMapping("/save")
		public String save(Person person) {
			personService.save(person);
			return "redirect:/";
		}
	
	//Delete an user
		@DeleteMapping("/{id}")
		public @ResponseBody void delete(@PathVariable(value = "id")Integer personId) {
			personService.deleteById(personId);
		}
}
