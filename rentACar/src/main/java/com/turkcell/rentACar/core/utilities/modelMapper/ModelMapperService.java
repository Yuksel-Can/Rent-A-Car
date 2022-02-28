package com.turkcell.rentACar.core.utilities.modelMapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

	ModelMapper forDto();
	ModelMapper forRequest();
}
