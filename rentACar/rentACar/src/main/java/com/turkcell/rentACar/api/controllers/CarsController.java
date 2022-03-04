package com.turkcell.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACar.business.abstracts.CarService;
import com.turkcell.rentACar.business.dtos.get.GetByIdCarDto;
import com.turkcell.rentACar.business.dtos.list.ListCarDto;
import com.turkcell.rentACar.business.request.create.CreateCarRequest;
import com.turkcell.rentACar.business.request.update.UpdateCarRequest;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

	private CarService carService;
	
	@Autowired
	public CarsController(CarService carService) {
		this.carService = carService;
	}
	
	
	@GetMapping("/getAll")
	public List<ListCarDto> getAll(){
		return this.carService.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateCarRequest createCarRequest) {
		this.carService.add(createCarRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody UpdateCarRequest updateCarRequest) {
		this.carService.update(updateCarRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam int id) {
		this.carService.delete(id);
	}
	
	@GetMapping("/getByCarId")
	public GetByIdCarDto getById(@RequestParam int id) {
		return carService.getById(id);
	}
}
