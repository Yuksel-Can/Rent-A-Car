package com.turkcell.rentACar.business.request;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {

	private String brandName;
}
