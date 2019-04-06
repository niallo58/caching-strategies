package cachingstrategies.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories("cachingstrategies.repositories")
public class DatabaseConfiguration extends AbstractMongoConfiguration {
	
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

	@Override
	public String getDatabaseName() {
		return database;
	}
	
	@Override
	@Bean
	public MongoClient mongoClient() {
		List<ServerAddress> seeds = new ArrayList<>();
		String[] servers = host.split(",");
		for (String ip : servers) {
            seeds.add(new ServerAddress(ip.trim(), port));
		}
		if(!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
			MongoCredential userCredential = MongoCredential.createCredential(username, authenticationDatabase,
					password.toCharArray());
			return new MongoClient(seeds, userCredential, new MongoClientOptions.Builder().build());
		} else {
			return new MongoClient(seeds);
		}
	}
}
