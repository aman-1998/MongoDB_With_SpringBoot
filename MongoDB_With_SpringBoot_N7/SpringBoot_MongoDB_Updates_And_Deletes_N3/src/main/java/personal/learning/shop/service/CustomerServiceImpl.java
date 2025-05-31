package personal.learning.shop.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	@Qualifier("mongoTemplateShop")
	private MongoTemplate mongoTemplateShop;

	@Override
	public void updateContactInfoForCustomer(Integer cusId) {
		
		/*
				db.customer.updateOne(
						{"cus_id": 1},
						[
							{
								$set: {
									"contactinfo": {
										"email": "$email",
										"phone": {
											$concat: [
												"9", 
												{
													$toString: {
														$toInt: {
															$multiply: [100000000, {$rand: {}}]
														}
													}
												}
											]
										}
									}
								}
							},
							{
								$unset: ["email"]
							}
						]
					);
			*/
		
		Document filterDoc = new Document("cus_id", cusId);
		
		Document setStage = new Document("$set", 
							    new Document("contactinfo", 
							    	new Document("email", "$email")
								    .append("phone", new Document("$concat", 
								    				 List.of("9", new Document("$toString", 
								    						 	  	  new Document("$toInt", 
								    						 	  		  new Document("$multiply", 
								    						 	  			  List.of(100000000, 
								    						 	  				      new Document("$rand", 
								    						 	  				    	  new Document()))))))))));
		
		Document unsetStage = new Document("$unset", List.of("email"));
		
		List<Document> pipeline = List.of(setStage, unsetStage);
		
		MongoDatabase database = mongoTemplateShop.getDb();
		MongoCollection<Document> customerCollection = database.getCollection("customer");
		
		customerCollection.updateOne(filterDoc, pipeline);
	}
	
	
}
