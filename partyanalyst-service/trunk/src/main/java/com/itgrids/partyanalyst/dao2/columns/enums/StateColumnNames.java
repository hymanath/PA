package com.itgrids.partyanalyst.dao.columns.enums;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;

public enum StateColumnNames {

	STATE_NAME("stateName"),
	ADMIN_CAPITAL("adminCapital"),
	LEGIS_CAPITAL("legisCapital"),
	JUDICIARY_CAPITAL("judiciaryCapital"),
	YEAR_ESTABLISHED("yearEstablished"),
	STATE_LANGUAGE("stateLanguage"),
	STATE_SYMBOL("stateSymbol"),
	STATE_SONG("stateSong"),
	STATE_ANIMAL("stateAnimal"),
	STATE_BIRD("stateBird"),
	STATE_TREE("stateTree"),
	STATE_SPORT("stateSport"),
	STATE_DANCE("stateDance"),
	STATE_FLOWER("stateFlower"),
	ISO_CODE("isoCode"),
	STATE_CODE("stateCode"),
	START_DATE("startDate"),
	DEFORM_DATE("deformDate");
	
	private final String value;

	public String getValue() {
		return value;
	}

	private StateColumnNames(String value) {
		this.value = value;
	}
}
