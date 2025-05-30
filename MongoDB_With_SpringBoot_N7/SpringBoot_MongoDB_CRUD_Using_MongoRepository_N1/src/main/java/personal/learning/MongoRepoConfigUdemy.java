package personal.learning;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = "personal.learning.udemy.repository",
    mongoTemplateRef = "mongoTemplateUdemy"
)
/*
 * This setup tells Spring:
 * ------------------------
 * For any repository in this package, use the given MongoTemplate (and 
 * therefore, the corresponding database).
 */
public class MongoRepoConfigUdemy {

}
