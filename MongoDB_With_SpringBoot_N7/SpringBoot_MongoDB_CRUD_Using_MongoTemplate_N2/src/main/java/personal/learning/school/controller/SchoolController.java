package personal.learning.school.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.genric.exception.ErrorPayload;
import personal.learning.page.entity.PaginatedResponseDTO;
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
	
	@GetMapping("/student/v1/deptandage")
	public ResponseEntity<?> getStudentByDeptAndAgeGtVersion1(@RequestParam(value = "state", required = false) String state,
													   @RequestParam(value = "age", required = false) Integer age,
													   @RequestParam(value = "page") int page,
													   @RequestParam(value = "limit") int limit) {
		
		List<Student> listOfStudent = new ArrayList<>();
		try {
			listOfStudent = studentService.getStudentByDeptAndAgeGtVersion1(state, age, page, limit);
		} catch (Exception ex) {
			ErrorPayload error = new ErrorPayload();
			error.setHttpStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(ex.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
		
		return ResponseEntity.ok(listOfStudent);
	}
	
	@GetMapping("/student/v2/deptandage")
	public ResponseEntity<?> getStudentByDeptAndAgeGtVersion2(@RequestParam(value = "dept", required = false) String dept,
												      @RequestParam(value = "age", required = false) Integer age,
												      @RequestParam(value = "page") int page,
												      @RequestParam(value = "limit") int limit) {
		
		PaginatedResponseDTO<Student> paginatedResponseDTO = new PaginatedResponseDTO<>();
		try {
			Page<Student> displayPage = studentService.getStudentByDeptAndAgeGtVersion2(dept, age, page, limit);
			paginatedResponseDTO.setData(displayPage.getContent());
			paginatedResponseDTO.setCurrentPageNumber(displayPage.getNumber());
			paginatedResponseDTO.setLastPage(displayPage.isLast());
			paginatedResponseDTO.setMaxPageSize(displayPage.getSize());
			paginatedResponseDTO.setTotalNumberOfPages(displayPage.getTotalPages());
			paginatedResponseDTO.setTotalNumberOfElements(displayPage.getTotalElements());
			paginatedResponseDTO.setNumberOfElementsInCurrentPage(displayPage.getNumberOfElements());
			
		} catch (Exception ex) {
			ErrorPayload error = new ErrorPayload();
			error.setHttpStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(ex.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
		
		/*
		 * We should not directly return Page<Student> because there is no guarantee about the 
		 * stability of the resulting JSON structure. I didn't see any issue in returning Page<Student> directly
		 * but in order to avoid warning we Manually Convert to custom DTO.
		 */
		return ResponseEntity.ok(paginatedResponseDTO);
	}
	
	
	@GetMapping("/student/stateWiseCount")
	public ResponseEntity<?> getStateWiseCountOfStudentsAgeGt(@RequestParam("age") Integer age) {
		
		List<Document> stateWiseCountOfStudents = new ArrayList<>();
		try {
			stateWiseCountOfStudents = studentService.getStateWiseCountOfStudentsAgeGt(age);
		} catch (Exception ex) {
			ErrorPayload error = new ErrorPayload();
			error.setHttpStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(ex.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
		
		return ResponseEntity.ok(stateWiseCountOfStudents);
	}
	
	
	@GetMapping("/student/deptWiseCount")
	public ResponseEntity<?> getDeptWiseCountOfStudentsAgeGt(@RequestParam("age") Integer age) {
		
		List<Document> stateWiseCountOfStudents = new ArrayList<>();
		try {
			stateWiseCountOfStudents = studentService.getDeptWiseCountOfStudentsAgeGt(age);
		} catch (Exception ex) {
			ErrorPayload error = new ErrorPayload();
			error.setHttpStatus(HttpStatus.BAD_REQUEST);
			error.setMessage(ex.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
		
		return ResponseEntity.ok(stateWiseCountOfStudents);
	}
	
	@GetMapping("/student/subject")
	public ResponseEntity<?> getStudentsOfParticularSubject(@RequestParam(value = "sub") String subject) {
		
		List<Document> listOfStudents = new ArrayList<>();
		listOfStudents = studentService.getStudentsOfParticularSubject(subject);
		return ResponseEntity.ok(listOfStudents);
	}
	
	
}
