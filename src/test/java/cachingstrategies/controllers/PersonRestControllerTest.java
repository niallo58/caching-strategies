package cachingstrategies.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cachingstrategies.exceptions.ObjectNotFoundException;
import cachingstrategies.models.Person;
import cachingstrategies.services.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = PersonRestController.class)
public class PersonRestControllerTest {

	@Autowired
	private PersonRestController underTest;
	
	@MockBean
	private PersonService personService;
	
	private Person person;
	
	@Before
	public void setup() {
		person = new Person(); 
	}
	
	@Test
	public void testgetAll() {
		// given
		List<Person> personList = new ArrayList<>();
		personList.add(person);
		when(personService.getAll()).thenReturn(personList);
		
		// when
		List<Person> actual = underTest.getAll();
		
		// then
		assertEquals(personList, actual);
		verify(personService, times(1)).getAll();
	}
	
	@Test
	public void testGetById() throws ObjectNotFoundException {
		// given
		String id = "1";
		when(personService.getById(id)).thenReturn(person);
		
		// when
		Person actual = underTest.getById(id);
		
		// then
		assertEquals(person, actual);
		verify(personService, times(1)).getById(id);
	}
	
	@Test(expected=ObjectNotFoundException.class)
	public void testGetById_throwsException() throws ObjectNotFoundException {
		// given
		String id = "1";
		when(personService.getById(id)).thenThrow(ObjectNotFoundException.class);
		
		// when
		underTest.getById(id);
	}
	
	@Test
	public void testSave() {
		// given
		when(personService.save(person)).thenReturn(person);
		
		// when
		Person actual = underTest.save(person);
		
		// then
		assertEquals(person, actual);
		verify(personService, times(1)).save(person);
	}
	
}
