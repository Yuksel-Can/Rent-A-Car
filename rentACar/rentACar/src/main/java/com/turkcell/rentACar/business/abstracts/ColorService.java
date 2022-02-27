package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.ColorDto;
import com.turkcell.rentACar.business.dtos.ColorListDto;
import com.turkcell.rentACar.business.request.CreateColorRequest;

public interface ColorService {

	List<ColorListDto> getAll();
	void add(CreateColorRequest createColorRequest);
	ColorDto findByColorId(int id);
}
