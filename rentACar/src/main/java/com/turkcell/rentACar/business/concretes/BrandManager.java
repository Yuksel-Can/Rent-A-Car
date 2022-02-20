package com.turkcell.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.BrandService;
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
		this.brandDao.save(brand);
		
	}

}
