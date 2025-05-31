package personal.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceRecord {

    @JsonProperty("date")
    private String date; // Can use LocalDate if needed

    @JsonProperty("service_type")
    private String serviceType;

    @JsonProperty("cost")
    private int cost;

    public ServiceRecord() { }

	public ServiceRecord(String date, String serviceType, int cost) {
		this.date = date;
		this.serviceType = serviceType;
		this.cost = cost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
    public String toString() {
        return "ServiceRecord [date=" + date + ", serviceType=" + serviceType + ", cost=" + cost + "]";
    }
}
