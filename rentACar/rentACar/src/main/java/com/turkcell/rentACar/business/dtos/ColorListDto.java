package com.turkcell.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorListDto {

	private int colorId;
	private String colorName;
}
