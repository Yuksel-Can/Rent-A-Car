package com.turkcell.rentACar.business.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

	private double dailyPrice;
	private short modelYear;
	private String description;
	private int brandId;
	private int colorId;
}
