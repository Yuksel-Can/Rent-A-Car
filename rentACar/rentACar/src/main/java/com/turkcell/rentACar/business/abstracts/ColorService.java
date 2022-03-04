package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.get.GetByIdColorDto;
import com.turkcell.rentACar.business.dtos.list.ListColorDto;
import com.turkcell.rentACar.business.request.create.CreateColorRequest;
import com.turkcell.rentACar.business.request.update.UpdateColorRequest;

public interface ColorService {

	List<ListColorDto> getAll();
	void add(CreateColorRequest createColorRequest);
	void update(UpdateColorRequest updateColorRequest);
	void delete(int colorId);
	
	GetByIdColorDto findByColorId(int colorId);
}
