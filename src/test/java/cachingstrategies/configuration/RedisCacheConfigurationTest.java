package cachingstrategies.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cachingstrategies.configuration.RedisCacheConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = RedisCacheConfiguration.class)
public class RedisCacheConfigurationTest {

	@Autowired
	private RedisCacheConfiguration underTest;
	
	@Value("${redis.hostname}")
	private String redisHostname;

	@Value("${redis.port}")
	private int redisPort;

	@Value("${redis.ttl}")
	private int ttl;
	
	@Test
	public void testJedisConnectionFactory() {
		// given
		// when
		JedisConnectionFactory actual = underTest.jedisConnectionFactory();
		// then
		assertNotNull(actual);
		assertEquals(redisHostname, actual.getHostName());
		assertEquals(redisPort, actual.getPort());
		assertEquals(ttl, actual.getClientConfiguration().getConnectTimeout().getSeconds());
	}
	
	@Test 
	public void testRedisTemplate() {
		// given
		// when
		RedisTemplate<String, Object> actual = underTest.redisTemplate();
		// then
		assertNotNull(actual);
		assertNotNull(actual.getConnectionFactory());
	}
	
}
