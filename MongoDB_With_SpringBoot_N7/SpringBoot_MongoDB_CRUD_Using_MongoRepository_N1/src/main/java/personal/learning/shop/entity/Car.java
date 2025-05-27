package personal.learning.shop.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "car")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {

    @Id
    @Field("_id")
    @JsonProperty("_id")
    private ObjectId id;
    
    @JsonProperty("maker")
    private String maker;

    @JsonProperty("model")
    private String model;
    
    @Field("fuel_type")
    @JsonProperty("fuel_type")
    private String fuelType;

    @JsonProperty("transmission")
    private String transmission;

    @JsonProperty("engine")
    private Engine engine;

    @JsonProperty("features")
    private List<String> features;

    @JsonProperty("sunroof")
    private boolean sunroof;

    @JsonProperty("airbags")
    private int airbags;

    @JsonProperty("price")
    private int price;

    @JsonProperty("owners")
    private List<Owner> owners;

    @JsonProperty("service_history")
    private List<ServiceRecord> serviceHistory;

	public Car() { }

	public Car(ObjectId id, String maker, String model, String fuelType, String transmission, Engine engine,
			List<String> features, boolean sunroof, int airbags, int price, List<Owner> owners,
			List<ServiceRecord> serviceHistory) {
		this.id = id;
		this.maker = maker;
		this.model = model;
		this.fuelType = fuelType;
		this.transmission = transmission;
		this.engine = engine;
		this.features = features;
		this.sunroof = sunroof;
		this.airbags = airbags;
		this.price = price;
		this.owners = owners;
		this.serviceHistory = serviceHistory;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public boolean isSunroof() {
		return sunroof;
	}

	public void setSunroof(boolean sunroof) {
		this.sunroof = sunroof;
	}

	public int getAirbags() {
		return airbags;
	}

	public void setAirbags(int airbags) {
		this.airbags = airbags;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

	public List<ServiceRecord> getServiceHistory() {
		return serviceHistory;
	}

	public void setServiceHistory(List<ServiceRecord> serviceHistory) {
		this.serviceHistory = serviceHistory;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", maker=" + maker + ", model=" + model + ", fuelType=" + fuelType + ", transmission="
				+ transmission + ", engine=" + engine + ", features=" + features + ", sunroof=" + sunroof + ", airbags="
				+ airbags + ", price=" + price + ", owners=" + owners + ", serviceHistory=" + serviceHistory + "]";
	}
	
}

