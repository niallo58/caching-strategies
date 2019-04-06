package cachingstrategies.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import cachingstrategies.exceptions.ObjectNotFoundException;
import cachingstrategies.models.Person;
import cachingstrategies.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepo;
	
	@Resource(name="redisTemplate")
	private HashOperations<String, String, Person> hashOps;
	
	private static final String SINGLE_PERSON_KEY = "person";
	private static final String ALL_PEOPLE_KEY = "people";
	
	public List<Person> getAll() {
		List<Person> peopleList;
		
		Map<String, Person> peopleMap = hashOps.entries(ALL_PEOPLE_KEY);
		if(peopleMap.isEmpty()) {
			peopleList = personRepo.findAll();
			for(Person person : peopleList) {
				hashOps.put(ALL_PEOPLE_KEY, person.getId(), person);
			}
		} else {
			peopleList = peopleMap.values().stream().collect(Collectors.toList());
		}
		
		return peopleList;
	}
	
	public Person getById(String id) throws ObjectNotFoundException {
		Person person = hashOps.get(SINGLE_PERSON_KEY, id);
		if(person == null) {
			Optional<Person> optPerson = personRepo.findById(id);
			if(optPerson.isPresent()) {
				person = optPerson.get();
				hashOps.put(SINGLE_PERSON_KEY, person.getId(), person);
			} else {
				throw new ObjectNotFoundException("Could not find Person in Redis or Database with id : " + id) ;
			}
		}
		
		return person;
	}
	
	public Person save(Person person) {
		hashOps.put(SINGLE_PERSON_KEY, person.getId(), person);
		return personRepo.save(person);
	}
	
}
