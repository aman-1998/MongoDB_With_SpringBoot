package personal.learning.school.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import personal.learning.school.entity.Student;
import personal.learning.school.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	@Qualifier("mongoTemplateSchool")
	private MongoTemplate mongoTemplateSchool;

	@Override
	public String save(Student student) {
		return studentRepository.save(student).getId().toHexString();
	}
	
	@Override
	public List<Student> getStudentNameStartsWith(String initials) throws Exception {
		
		List<Student> listOfStudent = new ArrayList<>();
		listOfStudent =  studentRepository.findNameStartsWith(initials);
		return listOfStudent;
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

	@Override
	public List<Student> getStudentByAgeGreaterThan(Integer age) throws Exception {
		
		if(age < 0 && age > 125) {
			throw new Exception("Invalid age");
		} 
		
		Query query = new Query();
		
		List<Criteria> criteriaList = new ArrayList<>();
		criteriaList.add(Criteria.where("age").gt(age));
		
		query.addCriteria(criteriaList.get(0));
		System.out.println("Mongo Query: " + query);
		List<Student> listOfStudent = mongoTemplateSchool.find(query, Student.class);
		
		return listOfStudent;
	}

	@Override
	public List<Student> getStudentByStateAndAgeGt(String state, Integer age, int page, int limit) throws Exception {
		
		Query query = new Query();
		List<Criteria> criteriaList = new ArrayList<>();
		
		if(StringUtils.isNotBlank(state)) {
			criteriaList.add(Criteria.where("contactinfo.state").is(state));
		}
		
		if(age!= null) {
			if(age >= 10 && age <= 50) {
				criteriaList.add(Criteria.where("age").gt(age));
			} else {
				throw new Exception("Invalid age");
			}
		}
		
		if(!criteriaList.isEmpty()) {
			//query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
			query.addCriteria(new Criteria().andOperator(criteriaList));
		}
		
		System.out.println("Mongo Query: " + query);
        
		/*
		 * First page: 1
		 * Second page: 2
		 * Third page: 3
		 * ...
		 * 
		 */
		List<Student> listOfStudent = mongoTemplateSchool.find(query.skip((page-1)*limit).limit(limit), Student.class);
		
		return listOfStudent;
	}

}
