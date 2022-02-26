package com.turkcell.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	
	@Autowired
	public ColorManager(ColorDao colorDao) {
		this.colorDao = colorDao;
	}
	
	@Override
	public List<Color> getAll() {
		return this.colorDao.findAll();
	}

	@Override
	public void add(Color color) {
		
		try {
			isExistsColorName(color.getColorName());
			this.colorDao.save(color);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	boolean isExistsColorName(String name) throws BusinessException {
		if(this.colorDao.existsByColorName(name)) {
			throw new BusinessException("This color already exists, cannot be re-added");
		}
		return true;
	}
	
}
