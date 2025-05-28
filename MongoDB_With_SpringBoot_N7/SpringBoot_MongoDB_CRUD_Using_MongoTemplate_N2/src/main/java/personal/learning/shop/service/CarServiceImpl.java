package personal.learning.shop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import personal.learning.shop.entity.Car;
import personal.learning.shop.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	@Qualifier("mongoTemplateShop")
	private MongoTemplate mongoTemplateShop;

	@Override
	public List<Car> findCar(String fuel, String transmission) {
		
		List<Car> listOfCar = new ArrayList<>();
		if(StringUtils.isNotBlank(fuel) && StringUtils.isNotBlank(transmission)) {
			listOfCar = carRepository.findByFuelTypeAndTransmission(fuel, transmission);
		} else if(StringUtils.isNotBlank(fuel)) {
			listOfCar = carRepository.findByFuelType(fuel);
		} else if(StringUtils.isNotBlank(transmission)) {
			listOfCar = carRepository.findByTransmission(transmission);
		} else {
			listOfCar = carRepository.findAll();
		}
		
		return listOfCar;
	}

	@Override
	public List<Document> getCarsWithTypeClassification() {
		
		/*
			 
			 	db.car.aggregate([
					{
						$project: {
							"_id": 0,
							"maker": 1,
							"model": 1,
							"type": {
								$switch: {
									"branches": [
										{"case": {$lt: ["$price", 600000]}, "then": "Budget"},
										{"case": {$and: [{$gte: ["$price", 600000]}, {$lt: ["$price", 1500000]}]}, "then": "Midrange"},
										{"case": {$and: [{$gte: ["$price", 1500000]}, {$lte: ["$price", 3500000]}]}, "then": "Premium"},
										{"case": {$gt: ["$price", 3500000]}, "then": "Luxary"}
									],
									"default": "Unknown"
								}
							}
						}
					}
				]);
		 */
		
		Document switchDoc = new Document("$switch", 
				new Document("branches", List.of(new Document("case", new Document("$lt", List.of("$price", 600000)))
											 					  .append("then", "Budget"),
											     new Document("case", new Document("$and", List.of(new Document("$gte", List.of("$price", 600000)),
													 								               new Document("$lt", List.of("$price", 1500000)))))
													              .append("then", "Midrange"),
											     new Document("case", new Document("$and", List.of(new Document("$gte", List.of("$price", 1500000)),
					 								       								           new Document("$lte", List.of("$price", 3500000)))))
													              .append("then", "Premium"),
											     new Document("case", new Document("$gt", List.of("$price", 3500000)))
								 					  			  .append("then", "Luxary"))).append("default", "Unknown"));
		
		
		Document projectStage = new Document("$project", new Document("_id", 0)
														 .append("maker", 1)
														 .append("model", 1)
														 .append("type", switchDoc));
		
		// Aggregate pipeline
        List<Document> pipeline = Arrays.asList(projectStage);
		
		MongoDatabase database = mongoTemplateShop.getDb();
        MongoCollection<Document> carCollection = database.getCollection("car");
        List<Document> results = carCollection.aggregate(pipeline).into(new ArrayList<>());
        return results;
	}

}
