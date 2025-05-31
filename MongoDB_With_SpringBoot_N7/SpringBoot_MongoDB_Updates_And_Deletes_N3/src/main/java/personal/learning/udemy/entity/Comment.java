package personal.learning.udemy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
	
	@JsonProperty("user")
	private String user;
	
	@JsonProperty("age")
	private String age;
	
	@JsonProperty("text")
	private String text;
	
	public Comment() {}

	public Comment(String user, String age, String text) {
		this.user = user;
		this.age = age;
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Comment [user=" + user + ", age=" + age + ", text=" + text + "]";
	}
	
}
