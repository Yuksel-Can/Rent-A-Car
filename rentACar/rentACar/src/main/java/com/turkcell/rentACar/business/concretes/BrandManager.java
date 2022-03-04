package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.dtos.get.GetByIdBrandDto;
import com.turkcell.rentACar.business.dtos.list.ListBrandDto;
import com.turkcell.rentACar.business.request.create.CreateBrandRequest;
import com.turkcell.rentACar.business.request.update.UpdateBrandRequest;
import com.turkcell.rentACar.business.abstracts.BrandService;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.modelMapper.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentACar.core.utilities.results.ErrorResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
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
	public DataResult<List<ListBrandDto>> getAll() {
		
		List<Brand> brands =  this.brandDao.findAll();
		List<ListBrandDto> brandListDtos = brands.stream().map(brand -> this.modelMapperService.forDto().map(brand, ListBrandDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListBrandDto>>(brandListDtos, "All Brands default listed");
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		
		try {
			isExistsBrandName(createBrandRequest.getBrandName());
			
			Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
			this.brandDao.save(brand);
			return new SuccessResult("Brand successfully added");
			
		} catch (Exception e) {
			return new ErrorResult("Could not add brand, this brand already exists");
		}
		
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		
		if(updateBrandRequest.getBrandId()==0) {
			return new ErrorResult("id cannot be empty");
		}
		try {
			isExistsBrandId(updateBrandRequest.getBrandId());
			isExistsBrandName(updateBrandRequest.getBrandName());
			
			Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
			this.brandDao.save(brand);
			return new SuccessResult("Brand successfully updated");
			
		} catch (Exception e) {
			return new ErrorResult("Could not update brand, id not exists or this brand already exists");
		}
	}

	@Override
	public Result delete(int id) {
		
		try {
			isExistsBrandId(id);
			
			this.brandDao.deleteById(id);
			return new SuccessResult("Brand successfully deleted");
			
		} catch (Exception e) {
			return new ErrorResult("Could not update brand, id not exists");
			
		}
		
	}

	@Override
	public DataResult<GetByIdBrandDto> findByBrandId(int id) {
		
		try {
			isExistsBrandId(id);

			Brand brand = this.brandDao.findByBrandId(id);
			GetByIdBrandDto brandDto = this.modelMapperService.forRequest().map(brand, GetByIdBrandDto.class);

			return new SuccessDataResult<GetByIdBrandDto>(brandDto, "Brand listed by id" );
			
		} catch (Exception e) {
			return new ErrorDataResult<>("Could not listed brand, id not exists");
		}
	}

	/*Another Methods*/
	
	boolean isExistsBrandName(String name) throws BusinessException {
		if(this.brandDao.existsByBrandName(name)) {
			throw new BusinessException("This brand already exists, cannot be re-added");
		}
		return true;
	}
	
	boolean isExistsBrandId(int id) throws BusinessException {
		if(!this.brandDao.existsByBrandId(id)) {
			throw new BusinessException("Brand id not exists");
		}
		return true;
	}

}
