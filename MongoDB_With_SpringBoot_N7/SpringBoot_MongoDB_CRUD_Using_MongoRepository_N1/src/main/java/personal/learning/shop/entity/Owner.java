package personal.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    @JsonProperty("name")
    private String name;

    @JsonProperty("purchase_date")
    private String purchaseDate; // Can use LocalDate if needed

    @JsonProperty("location")
    private String location;

    public Owner() { }

	public Owner(String name, String purchaseDate, String location) {
		this.name = name;
		this.purchaseDate = purchaseDate;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
    public String toString() {
        return "Owner [name=" + name + ", purchaseDate=" + purchaseDate + ", location=" + location + "]";
    }
}

