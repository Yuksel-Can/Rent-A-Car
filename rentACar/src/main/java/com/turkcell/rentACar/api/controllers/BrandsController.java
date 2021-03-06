package com.turkcell.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.business.dtos.get.GetBrandDto;
import com.turkcell.rentACar.business.dtos.list.ListBrandDto;
import com.turkcell.rentACar.business.request.create.CreateBrandRequest;
import com.turkcell.rentACar.business.request.update.UpdateBrandRequest;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

	private BrandService brandService;
	
	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping("/getAll")
	public List<ListBrandDto> getAll(){
		return this.brandService.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateBrandRequest createBrandRequest) {
		this.brandService.add(createBrandRequest);
	}
	
	@PostMapping("/update")
	public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		this.brandService.update(updateBrandRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam int id) {
		this.brandService.delete(id);
	}
	
	@PostMapping("/findByBrandId")
	public GetBrandDto findByBrandId(@RequestParam int id) {
		return this.brandService.findByBrandId(id);
	}
}
