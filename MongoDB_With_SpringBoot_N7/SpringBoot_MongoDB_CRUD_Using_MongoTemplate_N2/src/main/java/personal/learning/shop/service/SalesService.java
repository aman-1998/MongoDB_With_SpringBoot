package personal.learning.shop.service;

import personal.learning.shop.entity.Sales;

public interface SalesService {

	String save(Sales sales);

	void delete(String id, String seller);

}
