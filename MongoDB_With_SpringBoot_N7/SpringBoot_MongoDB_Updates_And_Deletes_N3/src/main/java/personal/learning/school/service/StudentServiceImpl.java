package personal.learning.school.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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

	/*
	 * Manual Pagination where we manually set skip() and limit() values
	 */
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

	
	/*
	 * It internally sets:
	 * query.skip(page * size).limit(size);
     * query.with(Sort) // if applicable
     * 
	 * It is equivalent to Manual Pagination but cleaner and more standardized.
	 */
	@Override
	public List<Student> getStudentByDeptAndAgeGtVersion1(String dept, Integer age, int page, int limit) throws Exception {
		
		/*
		 * First page: 0
		 * Second page: 1
		 * Third page: 2
		 * ...
		 * 
		 */
		Pageable pageable = PageRequest.of(page, limit);
		
		Query query = new Query();
		query.with(pageable);
		List<Criteria> criteriaList = new ArrayList<>();
		
		if(StringUtils.isNotBlank(dept)) {
			criteriaList.add(Criteria.where("department").is(dept));
		}
		
		if(age!= null) {
			if(age >= 10 && age <= 50) {
				criteriaList.add(Criteria.where("age").gt(age));
			} else {
				throw new Exception("Invalid age");
			}
		}
		
		if(!criteriaList.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteriaList));
		}
		System.out.println("Mongo Query: " + query);
		
		List<Student> listOfStudents = mongoTemplateSchool.find(query, Student.class);
		
		return listOfStudents;
	}
	
	/*
	 *  Why Consider Pageable and PageExecutionUtils ?
	 *  1. This allows Spring Data Mongo to handle pagination automatically, and clients 
	 *  (like REST controllers) can pass Pageable via query params like ?page=0&size=10
	 *  
	 *  2. Automatic total count calculation
	 *	When you use manual pagination, you don’t get the total number of records, which is useful for:
     *  -> Total pages
     *  -> Showing “X of Y results”
     *  -> Disabling "next" button when on last page  (very important)
	 *  
	 *  3. Better compatibility with REST APIs and metadata is automatically added in the response.
	 *  -> Spring Data REST, or Spring HATEOAS, and even Swagger tools, understand Page<T> and expose it nicely.
     *  -> A REST response like:
     *  	{
	 *	  		"content": [ ... ],
	 *	  		"totalElements": 120,
	 *	  		"totalPages": 12,
	 *	  		"number": 1,
	 *	  		"size": 10
	 *		}
	 * …comes naturally with Page<T>.
     *  
	 */
	@Override
	public Page<Student> getStudentByDeptAndAgeGtVersion2(String dept, Integer age, int page, int limit) throws Exception {
		
		/*
		 * First page: 0
		 * Second page: 1
		 * Third page: 2
		 * ...
		 * 
		 */
		Pageable pageable = PageRequest.of(page, limit);
		
		Query query = new Query();
		query.with(pageable);
		List<Criteria> criteriaList = new ArrayList<>();
		
		if(StringUtils.isNotBlank(dept)) {
			criteriaList.add(Criteria.where("department").is(dept));
		}
		
		if(age!= null) {
			if(age >= 10 && age <= 50) {
				criteriaList.add(Criteria.where("age").gt(age));
			} else {
				throw new Exception("Invalid age");
			}
		}
		
		if(!criteriaList.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteriaList));
		}
		System.out.println("Mongo Query: " + query);
		
		List<Student> listOfStudent = mongoTemplateSchool.find(query, Student.class);
		long totalCount = mongoTemplateSchool.count(query.skip(0).limit(0), Student.class);
		
		return PageableExecutionUtils.getPage(listOfStudent, pageable, () -> totalCount);
	}
	
	/*
	 * Constructing MongoDB aggregation pipelines in Java using DSL (Domain Specific Language)
	 * Pros:
	 * -> Less chance of error
	 * -> Utilization of framework
	 * -> Easy debugging
	 * 
	 * Cons:
	 * -> Less flexibility
	 * -> Have to remember large no. of methods
	 */
	@Override
	public List<Document> getStateWiseCountOfStudentsAgeGt(Integer age) throws Exception {
		
		/*
			 	db.student.aggregate([
					{
						$match: {
							"age": {$gt: 20}
						}
					},
					{
						$group: {
							"_id": {"state": "$contactinfo.state"},
							"count": {$sum: 1},
							"student": {$addToSet: "$name"}
						}
					},
					{
						$sort: {
							"count": 1
						}
					}
				]);
		 */
		
		MatchOperation matchStage = Aggregation.match(Criteria.where("age").gt(age));
		
		GroupOperation groupStage = Aggregation.group("contactinfo.state")
											   .count().as("count")
											   .addToSet("name").as("student");
		
		SortOperation sortStage = Aggregation.sort(Sort.by(Sort.Direction.ASC, "count"));
		
		Aggregation aggregation = Aggregation.newAggregation(matchStage, groupStage, sortStage);
		
		AggregationResults<Document> aggregationResults = mongoTemplateSchool.aggregate(aggregation,
																			"student", Document.class);
		
		return aggregationResults.getMappedResults();
		
		/*
		 * Here, Document can be replace with DTO class but I am going with
		 * Document here because I am too lazy to create a DTO class.
		 */
	}

	/*
	 * Constructing MongoDB aggregation pipelines in Java using Documents
	 * Pros:
	 * -> Very flexible
	 * -> Similar to MQL used in MongoShell
	 * -> Generic way of doing
	 * 
	 * Cons:
	 * -> Hard to debug
	 * -> Need to construct manually which can be sometimes difficult.
	 */
	@Override
	public List<Document> getDeptWiseCountOfStudentsAgeGt(Integer age) throws Exception {
		
		/*
		 		db.student.aggregate([
					{
						$match: {
							"age": {$gt: 20}
						}
					},
					{
						$group: {
							"_id": {"department": "$department"},
							"count": {$sum: 1},
							"student": {$addToSet: "$name"}
						}
					},
					{
						$sort: {
							"count": 1
						}
					}
				]);
		 */
		
        Document matchStage = new Document("$match", new Document("age", new Document("$gt", age)));
        
        Document groupStage = new Document("$group", 
        					  new Document("_id", new Document("department", "$department"))
        					  .append("count", new Document("$sum", 1)) 
        					  .append("sudent", new Document("$addToSet", "$name")));
        
        //.append() always adds to the current Document.
        
        
        Document sortStage = new Document("$sort", new Document("count", 1));
        
        // Aggregate pipeline
        List<Document> pipeline = Arrays.asList(matchStage, groupStage, sortStage);
        
        MongoDatabase database = mongoTemplateSchool.getDb();
        MongoCollection<Document> studentCollection = database.getCollection("student");
        List<Document> results = studentCollection.aggregate(pipeline).into(new ArrayList<>());
        
        /*
		 * Here, Document can be replace with DTO class but I am going with
		 * Document here because I am too lazy to create a DTO class.
		 */
        
        return results;
	}

	@Override
	public List<Document> getStudentsOfParticularSubject(String subject) {
		
		List<Document> listOfDocument = new ArrayList<>();
			
		/*
		 		=> Find Students who study Microprocessor
				db.student.aggregate([
					{
						"$unwind": "$subject"
					},
					{
						$match: {
							"subject": "Microprocessor"
						}
					},
					{
						$group: {
							"_id": {"subject": "$subject"},
							"count": {$sum: 1},
							"student_name": {$push: "$name"} 
						}
					}
				]);
		 */
		
		UnwindOperation unwindStage = Aggregation.unwind("subject");
		
		MatchOperation matchStage = Aggregation.match(Criteria.where("subject").is("Microprocessor"));
		
		GroupOperation groupStage = Aggregation.group("subject").count().as("count")
																.push("name").as("student_name");
		
		Aggregation aggregation = Aggregation.newAggregation(unwindStage, matchStage, groupStage);
		
		AggregationResults<Document> aggregationResults = mongoTemplateSchool.aggregate(aggregation, 
																				"student", Document.class);
		
	    listOfDocument = aggregationResults.getMappedResults();
		
		
		return listOfDocument;
	}

	@Override
	public void updateStudentNameByRoll(Integer roll) {
		
		/*
		  		db.student.updateOne({"roll": 142}, 
					{
						$set: {
							"name": "Krunal Deshmukh",
							"firstName": "Krunal",
							"lastName": "Deshmukh",
							"contactinfo.emailAddress": "krunaldeshmukh@google.com"
						}
					}
				);
		 
		 */
		
		Query query = new Query();
		List<Criteria> criteriaList = new ArrayList<>();
		criteriaList.add(Criteria.where("roll").is(roll));
		
		query.addCriteria(criteriaList.get(0));
		
		Document updateDefinition = new Document("$set", 
												new Document("name", "Krunal Deshmukh")
												.append("firstName", "Krunal")
												.append("lastName", "Deshmukh")
												.append("contactinfo.emailAddress", "krunaldeshmukh@google.com"));
		
		// Create update from pipeline
		Update update = Update.fromDocument(updateDefinition);

        // Perform update
        mongoTemplateSchool.updateFirst(query, update, "student");
	}

	@Override
	public void updateStudentNamePrefixByRoll(Integer roll) {
		
		/*
			db.student.updateOne(
					{
						"roll": 140
					},
					[
						{
							$set: {
								"name": {
									"$concat": [
										{
											$cond: {
												if: {$eq: ["$gender", "Male"]},
												then: "Mr.",
												else: "Mrs."
											}
										}, "$name"
									]
								}
							}
						}
					]
				);
		*/
		
		Document filterDoc = new Document("roll", roll);
		
		Document setStage = new Document("$set", 
				new Document("name", 
					new Document("$concat", 
						List.of(new Document("$cond", 
							new Document("if", new Document("$eq", List.of("$gender", "Male")))
							.append("then", "Mr.")
							.append("else", "Mrs.")), "$name"))));
		
		List<Document> pipeline = List.of(setStage);
		
		MongoDatabase database = mongoTemplateSchool.getDb();
	    MongoCollection<Document> studentCollection = database.getCollection("student");
	    
	    studentCollection.updateOne(filterDoc, pipeline);
	}

}
