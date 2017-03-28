package com.itgrids.partyanalyst.dao.columns.enums;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Township;

public enum TehsilColumnNames {	

	TEHSIL_NAME("tehsilName"),
	TEHSIL_CODE("tehsilCode"),
	START_DATE("startDate"),
	DEFORM_DATE("deformDate");
	
	private final String value;

	public String getValue() {
		return value;
	}

	private TehsilColumnNames(String value) {
		this.value = value;
	}
}
