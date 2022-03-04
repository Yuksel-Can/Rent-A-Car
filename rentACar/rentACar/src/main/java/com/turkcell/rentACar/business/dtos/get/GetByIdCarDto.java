package com.turkcell.rentACar.business.dtos.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCarDto {
	
	private int carId;
	private double dailyPrice;
	private short modelYear;
	private String brandName;
	private String colorName;
}
