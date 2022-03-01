package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.CarService;
import com.turkcell.rentACar.business.dtos.list.ListCarDto;
import com.turkcell.rentACar.business.request.create.CreateCarRequest;
import com.turkcell.rentACar.business.request.update.UpdateCarRequest;
import com.turkcell.rentACar.core.utilities.modelMapper.ModelMapperService;
import com.turkcell.rentACar.dataAccess.abstracts.CarDao;
import com.turkcell.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService{

	private CarDao carDao;
	private ModelMapperService modelMapperService;
	
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListCarDto> getAll() {
		
		List<Car> cars = this.carDao.findAll();
		List<ListCarDto> listCarDtos = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());
		return listCarDtos;
	}

	@Override
	public void add(CreateCarRequest createCarRequest) {
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		
	}

	@Override
	public void update(UpdateCarRequest updateCarRequest) {

		Car car  = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
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
