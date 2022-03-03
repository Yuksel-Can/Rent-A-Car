package com.turkcell.rentACar.business.abstracts;

import java.util.List;

import com.turkcell.rentACar.business.dtos.get.GetBrandDto;
import com.turkcell.rentACar.business.dtos.list.ListBrandDto;
import com.turkcell.rentACar.business.request.create.CreateBrandRequest;
import com.turkcell.rentACar.business.request.update.UpdateBrandRequest;

public interface BrandService {

	List<ListBrandDto> getAll();
	void add(CreateBrandRequest createBrandRequest);
	void update(UpdateBrandRequest updateBrandRequest);
	void delete(int id);
	GetBrandDto findByBrandId(int id);
}
