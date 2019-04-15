package cachingstrategies.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import cachingstrategies.configuration.JsonConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = JsonConfiguration.class)
public class JsonConfigurationTest {

	@Autowired
	private JsonConfiguration underTest;
	
	@Test
	public void testMappingJacksonHttpMessageConverter() {
		// given
		// when
		MappingJackson2HttpMessageConverter actual = underTest.mappingJacksonHttpMessageConverter();
		//then
		assertNotNull(actual);
		assertNotNull(actual.getObjectMapper());
		assertEquals(PropertyNamingStrategy.UPPER_CAMEL_CASE, actual.getObjectMapper().getPropertyNamingStrategy());
	}
	
	@Test
	public void testMyObjectMapper() {
		// given
		// when
		ObjectMapper actual = underTest.myObjectMapper();
		//then
		assertNotNull(actual);
		assertEquals(PropertyNamingStrategy.UPPER_CAMEL_CASE, actual.getPropertyNamingStrategy());
	}
	
}
