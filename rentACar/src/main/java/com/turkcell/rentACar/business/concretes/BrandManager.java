package com.turkcell.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{

	private BrandDao brandDao;
	
	@Autowired
	public BrandManager(BrandDao brandDao) {
		this.brandDao = brandDao;
	}
	
	@Override
	public List<Brand> getAll() {
		return this.brandDao.findAll();
	}

	@Override
	public void add(Brand brand) {
		
		try {
			isExistsBrandName(brand.getBrandName());
			this.brandDao.save(brand);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	boolean isExistsBrandName(String name) throws BusinessException {
		if(this.brandDao.existsByBrandName(name)) {
			throw new BusinessException("this value already exists, cannot be re-entered");
		}
		return true;
	}

}
