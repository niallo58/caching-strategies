package cachingstrategies.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.mongodb.MongoClient;

import cachingstrategies.configuration.DatabaseConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = DatabaseConfiguration.class)
public class DatabaseConfigurationTest {

	@Autowired
	private DatabaseConfiguration underTest;

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.port}")
	private Integer port;
	
	@Value("${spring.data.mongodb.username}")
	private String username;
	
	@Value("${spring.data.mongodb.password}")
	private String password;
	
	@Value("${spring.data.mongodb.authentication-database}")
	private String authenticationDatabase;
	
	@Test
	public void testGetDatabaseName() throws Exception {
		// given
		// when
		String actual = underTest.getDatabaseName();
		// then
		assertEquals("personal", actual);
	}
	
	@Test
	public void testMongoClient() throws Exception {
		// given
		// when
		MongoClient actual = underTest.mongoClient();
		// then
		assertNotNull(actual);
	}
	
	@Test
	public void testMongoClient_withoutCredentials() throws Exception {
		// given
		// when
		MongoClient actual = underTest.mongoClient();
		// then
		assertNotNull(actual);
		assertTrue(actual.getCredentialsList().isEmpty());
		assertEquals("localhost", actual.getAddress().getHost());
		assertEquals(27017, actual.getAddress().getPort());
		assertTrue(actual.getCredentialsList().isEmpty());
	}
	
	@Test
	public void testMongoClient_withCredentials() throws Exception {
		// given
		DatabaseConfiguration spyUnderTest = Mockito.spy(new DatabaseConfiguration());
		ReflectionTestUtils.setField(spyUnderTest, "host", "localhost");
		ReflectionTestUtils.setField(spyUnderTest, "port", 27017);
		ReflectionTestUtils.setField(spyUnderTest, "username", "user");
		ReflectionTestUtils.setField(spyUnderTest, "password", "pwd");
		ReflectionTestUtils.setField(spyUnderTest, "authenticationDatabase", "auth");
		// when
		MongoClient actual = spyUnderTest.mongoClient();
		// then
		assertNotNull(actual);
		assertEquals("localhost", actual.getAddress().getHost());
		assertEquals(27017, actual.getAddress().getPort());
		assertEquals("user", actual.getCredentialsList().get(0).getUserName());
		assertNotNull(actual.getCredentialsList().get(0).getPassword());
	}
	
}
