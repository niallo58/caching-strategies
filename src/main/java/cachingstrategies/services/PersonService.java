package cachingstrategies.services;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import cachingstrategies.models.Person;
import cachingstrategies.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Resource(name="redisTemplate")
	private HashOperations<String, Integer, Person> hashOps;
	
	private static final String KEY = "people";
	
	public List<Person> getAll() {
		List<Person> peopleList;
		
		Map<Integer, Person> peopleMap = hashOps.entries(KEY);
		if(peopleMap.isEmpty()) {
			peopleList = personRepo.findAll();
			for(Person person : peopleList) {
				hashOps.put(KEY, person.getId(), person);
			}
		} else {
			peopleList = (List<Person>) peopleMap.values();
		}
		
		return peopleList;
	}
	
	public Person getById(String id) {
		Person person = hashOps.get(KEY, id);
		
		if(person == null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where("id").is(id));
			person = mongoTemplate.findOne(query, Person.class);
			hashOps.put(KEY, person.getId(), person);
		}
		
		return person;
	}
	
	public Person save(Person person) {
		hashOps.put(KEY, person.getId(), person);
		return personRepo.save(person);
	}
	
}
