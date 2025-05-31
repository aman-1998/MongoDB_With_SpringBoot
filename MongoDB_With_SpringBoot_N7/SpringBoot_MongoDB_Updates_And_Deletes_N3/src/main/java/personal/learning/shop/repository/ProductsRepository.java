package personal.learning.shop.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import personal.learning.shop.entity.Products;

public interface ProductsRepository extends MongoRepository<Products, ObjectId> {

}
