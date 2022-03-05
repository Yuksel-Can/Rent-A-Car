package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.ColorService;
import com.turkcell.rentACar.business.dtos.get.GetByIdColorDto;
import com.turkcell.rentACar.business.dtos.list.ListColorDto;
import com.turkcell.rentACar.business.request.create.CreateColorRequest;
import com.turkcell.rentACar.business.request.update.UpdateColorRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.modelMapper.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentACar.core.utilities.results.ErrorResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
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
	public DataResult<List<ListColorDto>> getAll() {
		
		List<Color> colors = this.colorDao.findAll();
		List<ListColorDto> ListcolorDtos = colors.stream().map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListColorDto>>(ListcolorDtos, "All Colors default listed");
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		
		try {
			isExistsColorName(createColorRequest.getColorName());
			
			Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
			this.colorDao.save(color);
			return new SuccessResult("Color successfully added");
			
		} catch (Exception e) {
			return new ErrorResult("Could not add color, this color already exists");
		}
		
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {

		if(updateColorRequest.getColorId()==0) {
			return new ErrorResult("id cannot be empty");
		}
		try {
			isExistsByColorId(updateColorRequest.getColorId());
			isExistsColorName(updateColorRequest.getColorName());
			
			Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
			this.colorDao.save(color);
			return new SuccessResult("Color successfully updated");
			
		} catch (Exception e) {
			return new ErrorResult("Could not update color, id not exists or this color already exists");
		}
		
	}

	@Override
	public Result delete(int id) {
		
		try {	
			isExistsByColorId(id);
			
			this.colorDao.deleteById(id);
			return new SuccessResult("Color successfully deleted");
			
		} catch (Exception e) {
			return new ErrorResult("Could not update color, id not exists");
		}
	}

	@Override
	public DataResult<GetByIdColorDto> findByColorId(int id) {

		//Color color = this.colorDao.getById(id);			//alternatif 1	//dao gerek yok
		//Color color = this.colorDao.getByColorId(id);		//alternatif 2	//dao lazım

		//Color color = this.colorDao.findById(id);			//opional dönüyor
		
		try {
			isExistsByColorId(id);

			Color color = this.colorDao.findByColorId(id);		//alternatif 3	//dao lazım
			GetByIdColorDto getColorDto = this.modelMapperService.forDto().map(color, GetByIdColorDto.class);

			return new SuccessDataResult<GetByIdColorDto>(getColorDto, "Color listed by id" );
			
		} catch (Exception e) {
			return new ErrorDataResult<>("Could not listed color, id not exists");
		}
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
