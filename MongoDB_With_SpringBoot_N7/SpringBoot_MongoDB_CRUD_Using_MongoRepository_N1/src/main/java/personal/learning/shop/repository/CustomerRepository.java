package personal.learning.shop.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import personal.learning.shop.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {

}
