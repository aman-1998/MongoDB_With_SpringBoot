package personal.learning.udemy.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "course")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
	
	@Id
	@JsonProperty("_id")
	 private ObjectId id;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("author")
	private String author;
	
	@JsonProperty("hours")
	private String hours;
	
	@JsonProperty("comments")
	private List<Comment> commentList;

	public Course() { }

	public Course(ObjectId id, String title, String author, String hours, List<Comment> commentList) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.hours = hours;
		this.commentList = commentList;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + id.toHexString() + ", title=" + title + ", author=" + author + ", hours=" + hours
				+ ", commentList=" + commentList + "]";
	}

}
