package personal.learning;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoClientConfig {
	
	@Value("${my.mongodb.uri}")
	private String mongoURI;
	
	@Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoURI);
    }
}
