package com.itgrids.partyanalyst.dao.columns.enums;

public enum DistrictColumnNames {

	DISTRICT_NAME("districtName"),
	DISTRICT_CAPITAL("districtCapital"),
	AREA("area"),
	POPULATION("population"),
	DISTRICT_CODE("districtCode"),
	START_DATE("startDate"),
	DEFORM_DATE("deformDate"),;
	
	private final String value;

	public String getValue() {
		return value;
	}

	private DistrictColumnNames(String value) {
		this.value = value;
	}
}
