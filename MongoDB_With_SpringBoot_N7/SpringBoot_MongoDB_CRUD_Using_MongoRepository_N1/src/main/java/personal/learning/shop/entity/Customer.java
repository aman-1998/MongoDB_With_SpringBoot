package personal.learning.shop.entity;

import java.time.ZonedDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    @Id
    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty("cus_id")
    private int cusId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first")
    private String first;

    @JsonProperty("last")
    private String last;

    @JsonProperty("company")
    private String company;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("country")
    private String country;

    public Customer() {}

    public Customer(ObjectId id, int cusId, String email, String first, String last, String company, ZonedDateTime createdAt, String country) {
        this.id = id;
        this.cusId = cusId;
        this.email = email;
        this.first = first;
        this.last = last;
        this.company = company;
        this.createdAt = createdAt;
        this.country = country;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id.toHexString() +
               ", cusId=" + cusId +
               ", email=" + email +
               ", first=" + first +
               ", last=" + last +
               ", company=" + company +
               ", createdAt=" + createdAt +
               ", country=" + country + "]";
    }
}
