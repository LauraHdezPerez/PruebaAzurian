package mx.estancias.azurian.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mx.estancias.azurian.entity.Person;

public interface PersonService {
	public Iterable<Person> findAll();
	public Page<Person> findAll(Pageable pageable);
	public Optional<Person> findById(Integer id);
	public Person save(Person person);
	public void deleteById(Integer id);
}
