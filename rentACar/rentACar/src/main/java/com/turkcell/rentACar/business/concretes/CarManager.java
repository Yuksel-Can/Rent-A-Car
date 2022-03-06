package com.turkcell.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.turkcell.rentACar.business.abstracts.CarService;
import com.turkcell.rentACar.business.dtos.get.GetByIdCarDto;
import com.turkcell.rentACar.business.dtos.list.ListCarByDailyPriceDto;
import com.turkcell.rentACar.business.dtos.list.ListCarDto;
import com.turkcell.rentACar.business.dtos.list.ListPagedCarDto;
import com.turkcell.rentACar.business.dtos.list.ListSortedCarDto;
import com.turkcell.rentACar.business.request.create.CreateCarRequest;
import com.turkcell.rentACar.business.request.update.UpdateCarRequest;
import com.turkcell.rentACar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACar.core.utilities.modelMapper.ModelMapperService;
import com.turkcell.rentACar.core.utilities.results.DataResult;
import com.turkcell.rentACar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentACar.core.utilities.results.ErrorResult;
import com.turkcell.rentACar.core.utilities.results.Result;
import com.turkcell.rentACar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar.core.utilities.results.SuccessResult;
import com.turkcell.rentACar.dataAccess.abstracts.CarDao;
import com.turkcell.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService{

	private CarDao carDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListCarDto>> getAll() {
		
		List<Car> cars = this.carDao.findAll();
		List<ListCarDto> listCarDtos = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(listCarDtos, "All Cars default listed");
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult("Car successfully added");
		
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		if(updateCarRequest.getCarId()==0) {
			return new ErrorResult("id cannot be empty");
		}
		try {
			isExistsByCarId(updateCarRequest.getCarId());

			Car car  = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
			this.carDao.save(car);
			return new SuccessResult("Car successfully updated");
			
		} catch (Exception e) {
			return new ErrorResult("Could not update car, id not exists");
		}
	}

	@Override
	public Result delete(int id) {
		
		try {	
			isExistsByCarId(id);
			
			this.carDao.deleteById(id);
			return new SuccessResult("Car successfully deleted");

		} catch (Exception e) {
			return new ErrorResult("Could not update car, id not exists");
		}
	}

	@Override
	public DataResult<GetByIdCarDto> getById(int id) {
		
		try {
			isExistsByCarId(id);
			
			Car car = this.carDao.getByCarId(id);
			GetByIdCarDto getCarDto = this.modelMapperService.forDto().map(car, GetByIdCarDto.class);

			return new SuccessDataResult<GetByIdCarDto>(getCarDto, "Car listed by id" );
			
		} catch (Exception e) {
			return new ErrorDataResult<>("Could not listed car, id not exists");
		}
	}

	@Override
	public DataResult<List<ListCarByDailyPriceDto>> findByDailyPriceLessThanEqual(double dailyPrice) {
		List<Car> cars = this.carDao.findByDailyPriceLessThanEqual(dailyPrice);
		List<ListCarByDailyPriceDto> result = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarByDailyPriceDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(result, "Cars listed by daily price");
	}

	@Override
	public DataResult<List<ListPagedCarDto>> listPagedCar(int pageNo, int pageSize) {
		
		if(pageNo <= 0 || pageSize <= 0) {
			return new ErrorDataResult<>("please enter valid value");
		}
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		List<Car> cars = this.carDao.findAll(pageable).getContent();
		
		List<ListPagedCarDto> result = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListPagedCarDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListPagedCarDto>>(result, "Cars paginated");
	}

	@Override
	public DataResult<List<ListSortedCarDto>> listSortedCar(int sortType) {
		
		if(!(sortType==0 || sortType==1)) {
			return new ErrorDataResult<>("Only values 0 and 1 can be selected");
		}
		Sort sort = selectBySortType(sortType);
		List<Car> cars = this.carDao.findAll(sort);
		
		List<ListSortedCarDto> result = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListSortedCarDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListSortedCarDto>>(result, "All cars successfully sorted");
	}

	/*Another Methods*/
	
	private boolean isExistsByCarId(int id) throws BusinessException {
		if(!this.carDao.existsByCarId(id)) {
			throw new BusinessException("Car id not exists");
		}
		return true;
	}
	
	private Sort selectBySortType(int sortType) {
		if(sortType==1) {
			return Sort.by(Sort.Direction.ASC, "dailyPrice");
		}else if(sortType==0) {
			return Sort.by(Sort.Direction.DESC, "dailyPrice");
		}else {
			return Sort.by(Sort.Direction.ASC, "dailyPrice");
		}
	}
	
	
}
