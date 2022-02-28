package com.turkcell.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.CarService;
import com.turkcell.rentACar.dataAccess.abstracts.CarDao;
import com.turkcell.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService{

	private CarDao carDao;
	
	public CarManager(CarDao carDao) {
		this.carDao = carDao;
	}

	@Override
	public List<Car> getAll() {
		return this.carDao.findAll();
	}

	@Override
	public void add(Car car) {
		this.carDao.save(car);
		
	}

	@Override
	public void update(Car car) {

		this.carDao.save(car);
	}

	@Override
	public void delete(int id) {
		this.carDao.deleteById(id);
	}

	@Override
	public Car getById(int id) {
		return this.carDao.getByCarId(id);
	}
	
	
}
