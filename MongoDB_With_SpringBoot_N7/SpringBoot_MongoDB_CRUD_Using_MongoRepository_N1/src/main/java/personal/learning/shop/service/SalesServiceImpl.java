package personal.learning.shop.service;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.learning.shop.entity.Sales;
import personal.learning.shop.repository.SalesRepository;

@Service
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	private SalesRepository salesRepository;

	@Override
	public String save(Sales sales) {
		return salesRepository.save(sales).getId().toHexString();
	}

	@Override
	public void delete(String id, String seller) {
		if(StringUtils.isNotBlank(id)) {
			salesRepository.deleteById(new ObjectId(id));
		} else if(StringUtils.isNotBlank(seller)) {
			salesRepository.deleteBySeller(seller);
		} else {
			salesRepository.deleteAll();
		}
	}

}
