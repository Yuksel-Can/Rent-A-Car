package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.ColorListDto;
import com.turkcell.rentACar.business.request.CreateColorRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.modelMapper.ModelMapperService;
import com.turkcell.rentACar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public List<ColorListDto> getAll() {
		List<Color> colors = this.colorDao.findAll();
		List<ColorListDto> colorListDtos = colors.stream().map(color -> this.modelMapperService.forDto().map(color, ColorListDto.class)).collect(Collectors.toList());
		return colorListDtos;
	}

	@Override
	public void add(CreateColorRequest createColorRequest) {
		
		try {
			isExistsColorName(createColorRequest.getColorName());
			
			Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
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
