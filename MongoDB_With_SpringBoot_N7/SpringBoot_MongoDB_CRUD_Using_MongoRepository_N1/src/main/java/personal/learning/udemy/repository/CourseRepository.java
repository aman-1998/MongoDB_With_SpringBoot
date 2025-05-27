package personal.learning.udemy.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import personal.learning.udemy.entity.Course;

public interface  CourseRepository extends MongoRepository<Course, ObjectId> {

}
