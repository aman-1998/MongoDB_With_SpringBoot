package personal.learning.school.service;

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

}
