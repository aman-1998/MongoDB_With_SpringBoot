package personal.learning.shop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import personal.learning.shop.entity.Sales;
import personal.learning.shop.repository.SalesRepository;

@Service
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	private SalesRepository salesRepository;
	
	@Autowired
	@Qualifier("mongoTemplateShop")
	private MongoTemplate mongoTemplateShop;

	@Override
	public String save(Sales sales) {
		return salesRepository.save(sales).getId().toHexString();
	}

	@Override
	public void delete(String id, String seller) {
		if(StringUtils.isNotBlank(id)) {
			salesRepository.deleteById(new ObjectId(id));
		} else if(StringUtils.isNotBlank(seller)) {
			salesRepository.deleteBySeller(seller);
		} else {
			salesRepository.deleteAll();
		}
	}

	@Override
	public List<Document> getSellersWhoSellParticularProductType(String productType) {
		
		/*
		 		
		 		=> Find sellers who sell Footwears
				db.sales.aggregate([
					{
						"$unwind": "$varietyOfProducts"
					},
					{
						$match: {
							"varietyOfProducts": "Footwears"
						}
					},
					{
						$group: {
							"_id": {"subject": "$varietyOfProducts"},
							"seller": {$addToSet: "$seller"} 
						}
					}
				]);
		 		
		 */
		
		Document unwindStage = new Document("$unwind", "$varietyOfProducts");
		
		Document matchStage = new Document("$match", new Document("varietyOfProducts", "Footwears"));
		
		Document groupStage = new Document("$group", 
							  new Document("_id", new Document("subject", "$varietyOfProducts"))
							  .append("seller", new Document("$addToSet", "$seller")));
		
		// Aggregate pipeline
		List<Document> pipeline = Arrays.asList(unwindStage, matchStage, groupStage);
		
		MongoDatabase database = mongoTemplateShop.getDb();
        MongoCollection<Document> salesCollection = database.getCollection("sales");
        List<Document> results = salesCollection.aggregate(pipeline).into(new ArrayList<>());
		
		return results;
	}

	@Override
	public void deleteSalesWhichExceed(String sale) {
		
		/*
			db.sales.deleteOne({ 
				  $expr: { 
				    $gt: [{ $multiply: ["$price", "$quantity"] }, 1000] 
				  } 
				});
		*/
		
		Document exprFilterDoc = new Document("$expr", 
								 new Document("$gt", List.of(new Document("$multiply", 
										 			 	     List.of("$price", "$quantity")), sale)));
		
		MongoDatabase database = mongoTemplateShop.getDb();
		MongoCollection<Document> salesCollection = database.getCollection("sales");
		
		salesCollection.deleteOne(exprFilterDoc);
	}

}
