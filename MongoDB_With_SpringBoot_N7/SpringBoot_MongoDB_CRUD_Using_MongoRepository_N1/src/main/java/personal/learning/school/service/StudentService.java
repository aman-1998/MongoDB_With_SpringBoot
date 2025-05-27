package personal.learning.school.service;

import java.util.List;

import personal.learning.school.entity.Student;

public interface StudentService {

	String save(Student student);

	List<Student> findByGenderAndAgeBetween(int minAge, int maxAge, String gender) throws Exception;

	List<Student> getStudentNameStartsWith(String initials) throws Exception;

}
