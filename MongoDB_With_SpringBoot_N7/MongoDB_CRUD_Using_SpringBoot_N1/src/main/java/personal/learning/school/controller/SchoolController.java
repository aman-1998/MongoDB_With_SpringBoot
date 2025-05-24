package personal.learning.school.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.school.entity.Student;
import personal.learning.school.service.StudentService;

@RestController
@RequestMapping("/school")
public class SchoolController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student")
	public String insertStudent(@RequestBody Student student) {
		student.setId(new ObjectId());
		return studentService.save(student);
	}
	
	
}
