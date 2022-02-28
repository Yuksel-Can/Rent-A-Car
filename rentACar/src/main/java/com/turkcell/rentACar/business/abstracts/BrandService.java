package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.GetBrandDto;
import com.turkcell.rentACar.business.dtos.list.ListBrandDto;
import com.turkcell.rentACar.business.request.create.CreateBrandRequest;

public interface BrandService {

	List<ListBrandDto> getAll();
	void add(CreateBrandRequest createBrandRequest);
	GetBrandDto findByBrandId(int id);
}
