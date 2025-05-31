package personal.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;

@Configuration
public class MongoTemplateConfig {
	
	@Autowired
	private MongoClient mongoClient;
	
	@Primary
    @Bean
    public MongoTemplate mongoTemplateShop() {
        return new MongoTemplate(mongoClient, "shop");
    }

    @Bean
    public MongoTemplate mongoTemplateSchool() {
        return new MongoTemplate(mongoClient, "school");
    }
    
    @Bean
    public MongoTemplate mongoTemplateUdemy() {
    	return new MongoTemplate(mongoClient, "udemy");
    }
}
