package cachingstrategies.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cachingstrategies.exceptions.ObjectNotFoundException;
import cachingstrategies.models.Person;
import cachingstrategies.services.PersonService;

@RestController
public class PersonRestController {
	static final Logger log = LoggerFactory.getLogger(PersonRestController.class);

	@Autowired
	private PersonService personService;
	
	@GetMapping(value = "/getAll")
	public List<Person> getAll() {
		log.info("Hit getAll successfully");
		return personService.getAll();
	}
	
	@GetMapping(value = "/getById/{id}")
	public Person getById(@PathVariable String id) throws ObjectNotFoundException {
		log.info("Hit getById successfully");
		return personService.getById(id);
	}
	
	@PostMapping(value = "/save")
	public Person save(@RequestBody Person person) {
		log.info("Hit save successfully");
		return personService.save(person);
	}
	
}
