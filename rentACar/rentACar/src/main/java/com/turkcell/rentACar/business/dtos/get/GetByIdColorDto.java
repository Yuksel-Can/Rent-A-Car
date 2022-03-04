package com.turkcell.rentACar.business.dtos.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdColorDto {

	private int colorId;
	private String colorName;
}
