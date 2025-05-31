package personal.learning.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	@Qualifier("mongoTemplateShop")
	private MongoTemplate mongoTemplateShop;

	@Override
	public void deleteProductByNameAndPrice(String name, Integer price) {
		
		/*
		 	db.products.deleteMany({ "$and" : [{ "name" : "Balls to the Wall"}, { "price" : 948}]});
		 
		 */
		
		Query query = new Query();
		List<Criteria> criteriaList = new ArrayList<>();
		criteriaList.add(Criteria.where("name").is(name));
		criteriaList.add(Criteria.where("price").is(price));
		
		query.addCriteria(new Criteria().andOperator(criteriaList));
		
		System.out.println("Mongo Query:" + query);
		
		mongoTemplateShop.remove(query, "products");
	}
	
}
