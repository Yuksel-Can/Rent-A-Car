package com.turkcell.rentACar.business.dtos.get;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBrandDto {

	private int brandId;
	private String brandName;
}
