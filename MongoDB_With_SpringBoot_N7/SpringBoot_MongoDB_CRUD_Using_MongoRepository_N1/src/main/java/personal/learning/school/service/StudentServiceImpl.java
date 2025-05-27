package personal.learning.school.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.learning.school.entity.Student;
import personal.learning.school.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public String save(Student student) {
		return studentRepository.save(student).getId().toHexString();
	}
	
	@Override
	public List<Student> getStudentNameStartsWith(String initials) throws Exception {
		
		List<Student> listOfStudent = new ArrayList<>();
		return studentRepository.findNameStartsWith(initials);
	}

	@Override
	public List<Student> findByGenderAndAgeBetween(int minAge, int maxAge, String gender) throws Exception {
		
		if(minAge < 0 && minAge > 125) {
			throw new Exception("Invalid minAge");
		} 
		
		if(maxAge < 0 && maxAge > 125) {
			throw new Exception("Invalid maxAge");
		}
		
		if(!StringUtils.equals(gender, "Male") 
				&& !StringUtils.equals(gender, "Female")) {
			throw new Exception("Invalid gender");
		}
		
		return studentRepository.findByGenderAndAgeBetween(minAge, maxAge, gender);
	}

}
