package personal.learning.shop.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sales {
	
	@Field("_id")
	@JsonProperty("_id")
	private ObjectId id;
	
	@Field("seller")
	@JsonProperty("seller")
	private String seller;
	
	@Field("varietyOfProducts")
	@JsonProperty("varietyOfProducts")
	private List<String> varietyOfProducts;
	
	@Field("shopRating")
	@JsonProperty("shopRating")
	private String shopRating;
	
	@Field("productCategory")
	@JsonProperty("productCategory")
	private String productCategory;
	
	@Field("quantity")
	@JsonProperty("quantity")
	private String quantity;
	
	@Field("price")
	@JsonProperty("price")
	private String price;
	
	@Field("targetPrice")
	@JsonProperty("targetPrice")
	private String targetPrice;

	public Sales() { }

	public Sales(ObjectId id, String seller, List<String> varietyOfProducts, String shopRating, String productCategory,
			String quantity, String price, String targetPrice) {
		this.id = id;
		this.seller = seller;
		this.varietyOfProducts = varietyOfProducts;
		this.shopRating = shopRating;
		this.productCategory = productCategory;
		this.quantity = quantity;
		this.price = price;
		this.targetPrice = targetPrice;
	}
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public List<String> getVarietyOfProducts() {
		return varietyOfProducts;
	}

	public void setVarietyOfProducts(List<String> varietyOfProducts) {
		this.varietyOfProducts = varietyOfProducts;
	}

	public String getShopRating() {
		return shopRating;
	}

	public void setShopRating(String shopRating) {
		this.shopRating = shopRating;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTargetPrice() {
		return targetPrice;
	}

	public void setTargetPrice(String targetPrice) {
		this.targetPrice = targetPrice;
	}

	@Override
	public String toString() {
		return "Sales [id=" + id + ", seller=" + seller + ", varietyOfProducts=" + varietyOfProducts + ", shopRating="
				+ shopRating + ", productCategory=" + productCategory + ", quantity=" + quantity + ", price=" + price
				+ ", targetPrice=" + targetPrice + "]";
	}
	
}
