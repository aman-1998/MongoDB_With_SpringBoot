package personal.learning.genric.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorPayload {
	
	@JsonProperty("httpStatus")
	private HttpStatus httpStatus;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("remarks")
	private String remarks;
	
	public ErrorPayload( ) {}

	public ErrorPayload(HttpStatus httpStatus, String message, String remarks) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.remarks = remarks;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Error [httpStatus=" + httpStatus + ", message=" + message + ", remarks=" + remarks + "]";
	}
	
}
