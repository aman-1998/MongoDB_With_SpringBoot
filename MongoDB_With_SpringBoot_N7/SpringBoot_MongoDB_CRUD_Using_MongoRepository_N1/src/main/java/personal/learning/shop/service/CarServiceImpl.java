package personal.learning.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.learning.shop.entity.Car;
import personal.learning.shop.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;

	@Override
	public List<Car> findCar(String fuel, String transmission) {
		
		List<Car> listOfCar = new ArrayList<>();
		if(StringUtils.isNotBlank(fuel) && StringUtils.isNotBlank(transmission)) {
			listOfCar = carRepository.findByFuelTypeAndTransmission(fuel, transmission);
		} else if(StringUtils.isNotBlank(fuel)) {
			listOfCar = carRepository.findByFuelType(fuel);
		} else if(StringUtils.isNotBlank(transmission)) {
			listOfCar = carRepository.findByTransmission(transmission);
		} else {
			listOfCar = carRepository.findAll();
		}
		
		return listOfCar;
	}

}
