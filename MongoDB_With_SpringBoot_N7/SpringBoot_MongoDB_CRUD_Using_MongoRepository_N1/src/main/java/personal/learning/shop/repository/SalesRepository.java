package personal.learning.shop.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import personal.learning.shop.entity.Sales;

@Repository
public interface SalesRepository extends MongoRepository<Sales, ObjectId> {

	void deleteBySeller(String seller);

}
