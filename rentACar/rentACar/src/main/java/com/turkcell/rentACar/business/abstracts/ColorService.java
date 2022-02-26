package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.ColorListDto;
import com.turkcell.rentACar.business.request.CreateColorRequest;
import com.turkcell.rentACar.entities.concretes.Color;

public interface ColorService {

	List<ColorListDto> getAll();
	void add(CreateColorRequest createColorRequest);
}
