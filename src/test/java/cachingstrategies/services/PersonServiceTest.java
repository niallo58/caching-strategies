package cachingstrategies.services;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cachingstrategies.models.Person;
import cachingstrategies.repositories.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = PersonService.class)
public class PersonServiceTest {

	@Autowired
	private PersonService underTest;
	
	@MockBean
	private PersonRepository personRepo;
	
	@MockBean
	private HashOperations<String, String, Person> hashOps;
	
	@Ignore
	@Test
	public void testGetAll() {
		//TODO : Need to finish off unit tests
		// given
		// when
		List<Person> actual = underTest.getAll();
		// then
		assertNotNull(actual);
	}
}
