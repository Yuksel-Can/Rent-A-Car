package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.dtos.BrandDto;
import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.business.dtos.BrandListDto;
import com.turkcell.rentACar.business.request.CreateBrandRequest;
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
	public List<BrandListDto> getAll() {
		List<Brand> brands =  this.brandDao.findAll();
		List<BrandListDto> brandListDtos = brands.stream().map(brand -> this.modelMapperService.forDto().map(brand, BrandListDto.class)).collect(Collectors.toList());
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
	public BrandDto findByBrandId(int id) {
		Brand brand = this.brandDao.findByBrandId(id);
		try {

			isExistsBrandId(id);
			
			BrandDto brandDto = this.modelMapperService.forRequest().map(brand, BrandDto.class);
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
