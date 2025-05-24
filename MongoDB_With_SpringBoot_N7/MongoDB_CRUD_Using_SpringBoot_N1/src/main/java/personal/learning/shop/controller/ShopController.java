package personal.learning.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.shop.entity.Car;
import personal.learning.shop.entity.Sales;
import personal.learning.shop.service.CarService;
import personal.learning.shop.service.SalesService;

@RestController
@RequestMapping("/shoping")
public class ShopController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private SalesService salesService;
	
	@GetMapping("/car")
	public ResponseEntity<List<Car>> getCar(@RequestParam(name = "fuel", required = false) String fuel,
							                @RequestParam(name = "transmission", required = false) String transmission) {
		
		List<Car> listOfCar = new ArrayList<>();
		listOfCar = carService.findCar(fuel, transmission);
		return ResponseEntity.ok(listOfCar);
	}
	
	@PostMapping("/sales")
	public String insertSales(@RequestBody Sales sales) {
		sales.setId(new ObjectId());
		return salesService.save(sales);
	}
	
	@DeleteMapping("/sales/delete")
	public void deleteSales(@RequestParam(name = "id", required = false) String id,
							@RequestParam(name = "seller", required = false) String seller) {
		
		salesService.delete(id, seller);
	}
	
}
