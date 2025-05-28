package personal.learning.shop.service;

import java.util.List;

import org.bson.Document;

import personal.learning.shop.entity.Sales;

public interface SalesService {

	String save(Sales sales);

	void delete(String id, String seller);

	List<Document> getSellersWhoSellParticularProductType(String productType);

}
