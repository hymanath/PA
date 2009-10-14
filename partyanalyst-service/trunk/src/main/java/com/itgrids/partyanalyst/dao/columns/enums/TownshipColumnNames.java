package com.itgrids.partyanalyst.dao.columns.enums;

public enum TownshipColumnNames {

	TOWNSHIP_NAME("townshipName"),
	TOWNSHIP_CODE("townshipCode"),
	TOWNSHIP_TYPE("townshipType");
	
	private final String value;

	public String getValue() {
		return value;
	}

	private TownshipColumnNames(String value) {
		this.value = value;
	}
}
