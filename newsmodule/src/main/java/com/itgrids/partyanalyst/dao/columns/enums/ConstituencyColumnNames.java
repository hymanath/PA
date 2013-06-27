package com.itgrids.partyanalyst.dao.columns.enums;

public enum ConstituencyColumnNames {
	
	NAME("name"),
	ELECTIONSCOPE("electionScope"),
	STARTDATE("startDate"),
	DEFORMDATE("deformDate"),
	STATE("stateId"),
	DISTRICT("district"),
	COUNTRY_ID("countryId");

	private final String value;

	public String getValue() {
		return value;
	}

	private ConstituencyColumnNames(String value) {
		this.value = value;
	}

}
