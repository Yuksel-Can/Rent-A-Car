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

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.get.GetColorDto;
import com.turkcell.rentACar.business.dtos.list.ListColorDto;
import com.turkcell.rentACar.business.request.create.CreateColorRequest;
import com.turkcell.rentACar.business.request.update.UpdateColorRequest;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

	private ColorService colorService;
	
	@Autowired
	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@GetMapping("/getAll")
	public List<ListColorDto> getAll(){
		return this.colorService.getAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateColorRequest createColorRequest) {
		this.colorService.add(createColorRequest);
	}
	
	@PostMapping("/update")
	public void update(@RequestBody UpdateColorRequest updateColorRequest) {
		this.colorService.update(updateColorRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam int id) {
		this.colorService.delete(id);
	}
	
	@PostMapping("/findByColorId")
	public GetColorDto findByColorId(@RequestParam int id) {
		return this.colorService.findByColorId(id);
	}
}
