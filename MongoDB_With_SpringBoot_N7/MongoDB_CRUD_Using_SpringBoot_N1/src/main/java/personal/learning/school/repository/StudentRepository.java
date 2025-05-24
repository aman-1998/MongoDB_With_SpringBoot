package personal.learning.school.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import personal.learning.school.entity.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {

}
