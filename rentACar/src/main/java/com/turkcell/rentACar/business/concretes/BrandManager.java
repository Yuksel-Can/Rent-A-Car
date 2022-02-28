package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.dtos.GetBrandDto;
import com.turkcell.rentACar.business.dtos.list.ListBrandDto;
import com.turkcell.rentACar.business.request.create.CreateBrandRequest;
import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.modelMapper.ModelMapperService;
import com.turkcell.rentACar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public List<ListBrandDto> getAll() {
		List<Brand> brands =  this.brandDao.findAll();
		List<ListBrandDto> brandListDtos = brands.stream().map(brand -> this.modelMapperService.forDto().map(brand, ListBrandDto.class))
				.collect(Collectors.toList());
		return brandListDtos;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		
		try {
			isExistsBrandName(createBrandRequest.getBrandName());
			
			Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
			this.brandDao.save(brand);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public GetBrandDto findByBrandId(int id) {
		Brand brand = this.brandDao.findByBrandId(id);
		try {

			isExistsBrandId(id);
			
			GetBrandDto brandDto = this.modelMapperService.forRequest().map(brand, GetBrandDto.class);
			return brandDto;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	boolean isExistsBrandName(String name) throws BusinessException {
		if(this.brandDao.existsByBrandName(name)) {
			throw new BusinessException("This brand already exists, cannot be re-added");
		}
		return true;
	}
	boolean isExistsBrandId(int id) throws BusinessException {
		if(!this.brandDao.existsByBrandId(id)) {
			throw new BusinessException("This id not exists");
		}
		return true;
	}

}
