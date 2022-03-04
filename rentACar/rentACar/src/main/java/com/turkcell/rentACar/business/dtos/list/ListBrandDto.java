package com.turkcell.rentACar.business.dtos.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListBrandDto {

	private int brandId;
	private String brandName;
}
