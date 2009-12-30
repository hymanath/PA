package com.itgrids.partyanalyst.dao.columns.enums;

import com.itgrids.partyanalyst.model.Township;

public enum WardColumnNames {

	WARD_NAME("wardName"),
	WARD_CODE("wardCode");

	private final String value;

	public String getValue() {
		return value;
	}

	private WardColumnNames(String value) {
		this.value = value;
	}
}
