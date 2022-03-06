package com.turkcell.rentACar.business.dtos.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSortedCarDto {

	private int carId;
	private double dailyPrice;
	private short modelYear;
	private String brandName;
	private String colorName;

}
