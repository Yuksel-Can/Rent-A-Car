package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.get.GetColorDto;
import com.turkcell.rentACar.business.dtos.list.ListColorDto;
import com.turkcell.rentACar.business.request.create.CreateColorRequest;

public interface ColorService {

	List<ListColorDto> getAll();
	void add(CreateColorRequest createColorRequest);
	GetColorDto findByColorId(int id);
}
