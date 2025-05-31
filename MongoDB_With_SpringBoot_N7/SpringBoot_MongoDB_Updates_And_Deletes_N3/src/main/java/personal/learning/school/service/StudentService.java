package personal.learning.school.service;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Page;

import personal.learning.school.entity.Student;

public interface StudentService {

	String save(Student student);

	List<Student> findByGenderAndAgeBetween(int minAge, int maxAge, String gender) throws Exception;

	List<Student> getStudentNameStartsWith(String initials) throws Exception;

	List<Student> getStudentByAgeGreaterThan(Integer age) throws Exception;

	List<Student> getStudentByStateAndAgeGt(String state, Integer age, int page, int limit) throws Exception;

	List<Student> getStudentByDeptAndAgeGtVersion1(String dept, Integer age, int page, int limit) throws Exception;
	
	Page<Student> getStudentByDeptAndAgeGtVersion2(String dept, Integer age, int page, int limit) throws Exception;

	List<Document> getStateWiseCountOfStudentsAgeGt(Integer age) throws Exception;

	List<Document> getDeptWiseCountOfStudentsAgeGt(Integer age) throws Exception;

	List<Document> getStudentsOfParticularSubject(String subject);

	void updateStudentNameByRoll(Integer age);

	void updateStudentNamePrefixByRoll(Integer roll);

}
