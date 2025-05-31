package personal.learning.shop.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import personal.learning.shop.entity.Car;

@Repository
public interface CarRepository extends MongoRepository<Car, ObjectId> {
	
	List<Car> findByFuelTypeAndTransmission(String fuel, String transmission);
	
	List<Car> findByFuelType(String fuel);
	
	List<Car> findByTransmission(String transmission);
	
}
