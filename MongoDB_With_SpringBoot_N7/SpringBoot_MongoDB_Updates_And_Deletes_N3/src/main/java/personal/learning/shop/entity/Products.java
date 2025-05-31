package personal.learning.shop.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {

    @Id
    @JsonProperty("_id")
    private ObjectId objectId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("vendor_id")
    private int vendorId;

    @JsonProperty("requires_shipping")
    private boolean requiresShipping;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("taxable")
    private boolean taxable;

    @JsonProperty("status")
    private String status;

    @JsonProperty("price")
    private double price;

    @JsonProperty("vendor")
    private Vendor vendor;

    @JsonProperty("details")
    private List<TrackDetail> details;
    
    public Products() { }

	public Products(ObjectId objectId, int id, String name, int vendorId, boolean requiresShipping, String sku,
			boolean taxable, String status, double price, Vendor vendor, List<TrackDetail> details) {
		this.objectId = objectId;
		this.id = id;
		this.name = name;
		this.vendorId = vendorId;
		this.requiresShipping = requiresShipping;
		this.sku = sku;
		this.taxable = taxable;
		this.status = status;
		this.price = price;
		this.vendor = vendor;
		this.details = details;
	}

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public boolean isRequiresShipping() {
		return requiresShipping;
	}

	public void setRequiresShipping(boolean requiresShipping) {
		this.requiresShipping = requiresShipping;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public boolean isTaxable() {
		return taxable;
	}

	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public List<TrackDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TrackDetail> details) {
		this.details = details;
	}

	@Override
    public String toString() {
        return "Product [objectId=" + objectId.toHexString() + ", id=" + id + ", name=" + name +
                ", vendorId=" + vendorId + ", requiresShipping=" + requiresShipping +
                ", sku=" + sku + ", taxable=" + taxable + ", status=" + status +
                ", price=" + price + ", vendor=" + vendor + ", details=" + details + "]";
    }
}
