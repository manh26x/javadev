package com.mike.springbootregistrationform.model;

public class Country {
	
	private String countryCode;
	private String countryName;
	
	public Country() {
		
	}

	public Country(String countryCode, String countryName) {
		super();
		this.setCountryCode(countryCode);
		this.setCountryName(countryName);
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
}
