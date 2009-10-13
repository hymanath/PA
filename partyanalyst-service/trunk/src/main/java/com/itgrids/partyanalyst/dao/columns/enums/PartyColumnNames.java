package com.itgrids.partyanalyst.dao.columns.enums;

public enum PartyColumnNames {
	LONG_NAME("longName"),
	SHORT_NAME("shortName"),
	SYMBOL("symbol"),
	ADDRESS("address"),
	COMMENTS("comments"),
	PARTY_RECOGZATION("partyRecognization");
	private final String value;
	public String getValue() {
		return value;
	}

	private PartyColumnNames(String value) {
		this.value = value;
	}
}
