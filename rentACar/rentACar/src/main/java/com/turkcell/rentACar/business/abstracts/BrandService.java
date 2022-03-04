package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.get.GetByIdBrandDto;
import com.turkcell.rentACar.business.dtos.list.ListBrandDto;
import com.turkcell.rentACar.business.request.create.CreateBrandRequest;
import com.turkcell.rentACar.business.request.update.UpdateBrandRequest;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.Result;

public interface BrandService {

	DataResult<List<ListBrandDto>> getAll();
	Result add(CreateBrandRequest createBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
	Result delete(int brandId);
	
	DataResult<GetByIdBrandDto> findByBrandId(int brandId);
}
