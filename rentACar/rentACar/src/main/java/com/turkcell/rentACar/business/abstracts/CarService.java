package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.get.GetByIdCarDto;
import com.turkcell.rentACar.business.dtos.list.ListCarDto;
import com.turkcell.rentACar.business.request.create.CreateCarRequest;
import com.turkcell.rentACar.business.request.update.UpdateCarRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

public interface CarService {

	DataResult<List<ListCarDto>> getAll();
	Result add(CreateCarRequest createCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	Result delete(int carId);
	
	DataResult<GetByIdCarDto> getById(int carId);
}
