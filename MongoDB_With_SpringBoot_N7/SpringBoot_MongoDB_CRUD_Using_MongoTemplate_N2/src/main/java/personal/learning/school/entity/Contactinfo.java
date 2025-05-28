package personal.learning.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contactinfo {
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("emailAddress")
	private String emailAddress;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("zipcode")
	private String zipcode;
	
	public Contactinfo() { }

	public Contactinfo(String city, String phone, String emailAddress, 
					   String state, String zipcode) {
		this.city = city;
		this.phone = phone;
		this.emailAddress = emailAddress;
		this.state = state;
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString() {
		return "ContactInfo [city=" + city + ", phone=" + phone + ", emailAddress=" + emailAddress + ", state=" + state
				+ ", zipcode=" + zipcode + "]";
	}
	
}
