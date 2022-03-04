package com.turkcell.rentACar.business.dtos.get;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdBrandDto {

	private int brandId;
	private String brandName;
}
