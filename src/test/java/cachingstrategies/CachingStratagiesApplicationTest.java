package cachingstrategies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cachingstrategies.CachingStratagiesApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = CachingStratagiesApplication.class)
public class CachingStratagiesApplicationTest {
	
	@Test
	public void testContextLoads() {
		// given
		// check if context was loaded without exception
	}

}
