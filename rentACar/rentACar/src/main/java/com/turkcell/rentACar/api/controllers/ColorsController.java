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

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.get.GetByIdColorDto;
import com.turkcell.rentACar.business.dtos.list.ListColorDto;
import com.turkcell.rentACar.business.request.create.CreateColorRequest;
import com.turkcell.rentACar.business.request.update.UpdateColorRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

	private ColorService colorService;
	
	@Autowired
	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListColorDto>> getAll(){
		return this.colorService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateColorRequest createColorRequest) {
		return this.colorService.add(createColorRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateColorRequest updateColorRequest) {
		return this.colorService.update(updateColorRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam int id) {
		return this.colorService.delete(id);
	}
	
	@GetMapping("/findByColorId")
	public DataResult<GetByIdColorDto> findByColorId(@RequestParam int id) {
		return this.colorService.findByColorId(id);
	}
}
