package personal.learning.school.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Document(collection = "student")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    @Id
    @Field("_id")
    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("age")
    private int age;

    @JsonProperty("roll")
    private int roll;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("department")
    private String department;

    @JsonProperty("subject")
    private List<String> subject;

    @JsonProperty("bio")
    private String bio;
    
    @JsonProperty("contactInfo")
    private Contactinfo contactinfo;

    public Student() { }

	public Student(ObjectId id, String name, String firstName, String lastName, int age, int roll, String gender,
			String department, List<String> subject, String bio, Contactinfo contactinfo) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.roll = roll;
		this.gender = gender;
		this.department = department;
		this.subject = subject;
		this.bio = bio;
		this.contactinfo = contactinfo;
	}

	// Getters and Setters

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

	public Contactinfo getContactinfo() {
		return contactinfo;
	}

	public void setContactinfo(Contactinfo contactinfo) {
		this.contactinfo = contactinfo;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", firstName=" + firstName + ", lastName=" + lastName + ", age="
				+ age + ", roll=" + roll + ", gender=" + gender + ", department=" + department + ", subject=" + subject
				+ ", bio=" + bio + ", contactinfo=" + contactinfo + "]";
	}
    
}

