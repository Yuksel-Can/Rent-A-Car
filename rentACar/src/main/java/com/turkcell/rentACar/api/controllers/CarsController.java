package com.turkcell.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACar.business.abstracts.CarService;
import com.turkcell.rentACar.entities.concretes.Car;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

	private CarService carService;
	
	public CarsController(CarService carService) {
		this.carService = carService;
	}
	
	
	@GetMapping("/getAll")
	public List<Car> getAll(){
		return this.carService.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Car car) {
		this.carService.add(car);
	}
	
	@PostMapping("/update")
	public void update(@RequestBody Car car) {
		this.carService.update(car);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam int id) {
		this.carService.delete(id);
	}
	
	@PostMapping("/getById")
	public Car getById(@RequestParam int id) {
		return carService.getById(id);
	}
}
