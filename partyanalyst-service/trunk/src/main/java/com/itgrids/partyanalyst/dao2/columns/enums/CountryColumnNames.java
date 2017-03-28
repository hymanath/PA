/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
package com.itgrids.partyanalyst.dao.columns.enums;

/**
 * Country Table Column Name Constants in the form enum. 
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 *
 */
public enum CountryColumnNames {
	
	COUNTRY_NAME("countryName"),
	ISO_CODE("isoCode"),
	CAPITAL("capital");
	
	private final String value;

	public String getValue() {
		return value;
	}

	private CountryColumnNames(String value) {
		this.value = value;
	}

}