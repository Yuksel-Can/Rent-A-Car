package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.BrandDto;
import com.turkcell.rentACar.business.dtos.BrandListDto;
import com.turkcell.rentACar.business.request.CreateBrandRequest;

public interface BrandService {

	List<BrandListDto> getAll();
	void add(CreateBrandRequest createBrandRequest);
	BrandDto findByBrandId(int id);
}
