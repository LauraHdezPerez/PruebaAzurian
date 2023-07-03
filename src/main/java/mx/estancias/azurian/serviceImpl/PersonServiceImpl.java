package mx.estancias.azurian.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.estancias.azurian.entity.Person;
import mx.estancias.azurian.repository.PersonRepository;
import mx.estancias.azurian.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired		
	private PersonRepository personRepository;

	@Override
	@Transactional(readOnly=true)
	public Iterable<Person> findAll() {
		// TODO Auto-generated method stub
		//return null;
		return personRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Person> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		//return null;
		return personRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Person> findById(Integer id) {
		// TODO Auto-generated method stub
		//return Optional.empty();
		return personRepository.findById(id);
	}

	@Override
	@Transactional
	public Person save(Person person) {
		// TODO Auto-generated method stub
		return personRepository.save(person);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		personRepository.deleteById(id);
	}

}
