package com.turkcell.rentACar.business.dtos.list;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListColorDto {

	private int colorId;
	private String colorName;
}
