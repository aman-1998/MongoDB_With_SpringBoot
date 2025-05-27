package personal.learning.shop.service;

import java.util.List;

import personal.learning.shop.entity.Car;

public interface CarService {

	List<Car> findCar(String fuel, String transmission);

}
