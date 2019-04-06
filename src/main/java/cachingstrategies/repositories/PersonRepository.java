package cachingstrategies.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import cachingstrategies.models.Person;

public interface PersonRepository extends MongoRepository<Person, String> {
	
}
