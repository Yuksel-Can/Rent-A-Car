package com.turkcell.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.business.dtos.BrandDto;
import com.turkcell.rentACar.business.dtos.BrandListDto;
import com.turkcell.rentACar.business.request.CreateBrandRequest;

@RestController
@RequestMapping("/api/brand")
public class BrandsController {

	private BrandService brandService;
	
	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping("/getAll")
	public List<BrandListDto> getAll(){
		return this.brandService.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateBrandRequest createBrandRequest) {
		this.brandService.add(createBrandRequest);
	}
	
	@PostMapping("/findByBrandId")
	public BrandDto findByBrandId(@RequestParam int id) {
		return this.brandService.findByBrandId(id);
	}
}
