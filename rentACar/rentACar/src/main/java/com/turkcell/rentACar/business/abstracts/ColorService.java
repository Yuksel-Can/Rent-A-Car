package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.get.GetByIdColorDto;
import com.turkcell.rentACar.business.dtos.list.ListColorDto;
import com.turkcell.rentACar.business.request.create.CreateColorRequest;
import com.turkcell.rentACar.business.request.update.UpdateColorRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

public interface ColorService {

	DataResult<List<ListColorDto>> getAll();
	Result add(CreateColorRequest createColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
	Result delete(int colorId);
	
	DataResult<GetByIdColorDto> findByColorId(int colorId);
}
