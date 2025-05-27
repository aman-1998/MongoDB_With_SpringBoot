package personal.learning.school.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import personal.learning.school.entity.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {
	
	/*
	 *  In Spring Data MongoDB, the @Query annotation is not designed to 
	 *  support full aggregation pipelines directly. It's primarily meant 
	 *  for basic queries using MongoDB's find syntax, not for 
	 *  aggregations involving $group, $lookup, $project, etc.
	 */
	
	@Query(value="{$and: [{'age': {$gte: ?0}}, {'age': {$lte: ?1}}, {'gender': ?2}]}")
	List<Student> findByGenderAndAgeBetween(int minAge, int maxAge, String gender);
	
	// Projection
	@Query(value = "{'name': {$regex: '^?0'}}", 
		   fields = "{'_id': 0, 'firstname': 0, 'lastname': 0, 'bio': 0}")
	List<Student> findNameStartsWith(String initials);

}
