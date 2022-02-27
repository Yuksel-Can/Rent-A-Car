package com.turkcell.rentACar.business.dtos;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

	private int brandId;
	private String brandName;
}
