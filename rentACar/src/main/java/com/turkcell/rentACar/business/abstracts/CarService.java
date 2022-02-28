package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.entities.concretes.Car;

public interface CarService {

	List<Car> getAll();
	void add(Car car);
	void update(Car car);
	void delete(int id);
	Car getById(int id);
}
