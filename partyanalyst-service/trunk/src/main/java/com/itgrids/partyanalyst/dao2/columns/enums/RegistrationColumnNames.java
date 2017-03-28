package com.itgrids.partyanalyst.dao.columns.enums;

public enum RegistrationColumnNames {
	
	//REGISTRATION_ID("registrationId"), 
	  FIRSTNAME("firstName"),
	  MIDDLENAME("middleName"),
	  LASTNAME("lastName"),
	  USERNAME("userName"),
	  PASSWORD("password"),
	DATEOFBIRTH("dateOfBirth"),
	EMAIL("email"),
	PHONE("phone"),
	MOBILE("mobile"),
	  ADDRESS("address"),
	  GENDER("gender"),
	  COUNTRY("country"),
	  PINCODE("pincode");


	private final String value;

	public String getValue() {
		return value;
	}

	private RegistrationColumnNames(String value) {
		this.value = value;
	}
}
