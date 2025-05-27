package personal.learning.school.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.genric.exception.ErrorPayload;
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
	
	
	@GetMapping("/student/start")
	public ResponseEntity<?> getStudentNameStartsWith(@RequestParam(value = "initials", required = false) String initials) {
		
		List<Student> listOfStudent = new ArrayList<>();
		try {
			listOfStudent = studentService.getStudentNameStartsWith(initials);
		} catch(Exception ex) {
			ErrorPayload error = new ErrorPayload();
			error.setHttpStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(ex.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
		return ResponseEntity.ok(listOfStudent);
	}
	
	
	@GetMapping("/student/ageandgender")
	public ResponseEntity<?> getStudentGenderAndAgeBetween(@RequestParam("minAge") int minAge,
																	   @RequestParam("maxAge") int maxAge,
												                       @RequestParam("gender") String gender) {
		
		List<Student> listOfStudent = new ArrayList<>();
		try {
			listOfStudent = studentService.findByGenderAndAgeBetween(minAge, maxAge, gender);
		} catch(Exception ex) {
			ErrorPayload error = new ErrorPayload();
			error.setHttpStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(ex.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
		return ResponseEntity.ok(listOfStudent);
	}
	
	@GetMapping("/student/age")
	public ResponseEntity<?> getStudentByAgeGreaterThan(@RequestParam(value = "age") Integer age) {
		
		List<Student> listOfStudent = new ArrayList<>();
		try {
			listOfStudent = studentService.getStudentByAgeGreaterThan(age);
		} catch (Exception ex) {
			ErrorPayload error = new ErrorPayload();
			error.setHttpStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(ex.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
		
		return ResponseEntity.ok(listOfStudent);
	}
	
	@GetMapping("/student/stateandage")
	public ResponseEntity<?> getStudentByStateAndAgeGt(@RequestParam(value = "state", required = false) String state,
													 @RequestParam(value = "age", required = false) Integer age,
													 @RequestParam(value = "page") int page,
													 @RequestParam(value = "limit") int limit) {
		
		List<Student> listOfStudent = new ArrayList<>();
		try {
			listOfStudent = studentService.getStudentByStateAndAgeGt(state, age, page, limit);
		} catch (Exception ex) {
			ErrorPayload error = new ErrorPayload();
			error.setHttpStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(ex.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
		
		return ResponseEntity.ok(listOfStudent);
	}
	
}
