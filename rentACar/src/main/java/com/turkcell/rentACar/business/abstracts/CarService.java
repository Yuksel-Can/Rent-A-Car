package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.list.ListCarDto;
import com.turkcell.rentACar.business.request.create.CreateCarRequest;
import com.turkcell.rentACar.business.request.update.UpdateCarRequest;
import com.turkcell.rentACar.entities.concretes.Car;

public interface CarService {

	List<ListCarDto> getAll();
	void add(CreateCarRequest createCarRequest);
	void update(UpdateCarRequest updateCarRequest);
	void delete(int id);
	Car getById(int id);
}
