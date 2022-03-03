package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.get.GetColorDto;
import com.turkcell.rentACar.business.dtos.list.ListColorDto;
import com.turkcell.rentACar.business.request.create.CreateColorRequest;
import com.turkcell.rentACar.business.request.update.UpdateColorRequest;
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
	public List<ListColorDto> getAll() {
		List<Color> colors = this.colorDao.findAll();
		List<ListColorDto> colorListDtos = colors.stream().map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class)).collect(Collectors.toList());
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

	@Override
	public void update(UpdateColorRequest updateColorRequest) {
		
		try {
			isExistsByColorId(updateColorRequest.getColorId());

			Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
			this.colorDao.save(color);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void delete(int id) {
		try {
			
			isExistsByColorId(id);
			this.colorDao.deleteById(id);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public GetColorDto findByColorId(int id) {

		//Color color = this.colorDao.getById(id);			//alternatif 1	//dao gerek yok
		//Color color = this.colorDao.getByColorId(id);		//alternatif 2	//dao lazım

		//Color color = this.colorDao.findById(id);			//opional dönüyor
		
		try {
			isExistsByColorId(id);

			Color color = this.colorDao.findByColorId(id);		//alternatif 3	//dao lazım
			GetColorDto colorDto = this.modelMapperService.forDto().map(color, GetColorDto.class);
			return colorDto;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/*Another Methods*/
	
	boolean isExistsColorName(String colorName) throws BusinessException {
		if(this.colorDao.existsByColorName(colorName)) {
			throw new BusinessException("This color already exists, cannot be re-added");
		}
		return true;
	}
	
	boolean isExistsByColorId(int colorId) throws BusinessException {
		if(!this.colorDao.existsByColorId(colorId)) {
			throw new BusinessException("Color id not exists");
		}
		return true;
	}
}
