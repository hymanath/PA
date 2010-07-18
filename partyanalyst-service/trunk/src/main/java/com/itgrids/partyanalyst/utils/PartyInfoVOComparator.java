package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyInfoVO;

public class PartyInfoVOComparator implements Comparator<PartyInfoVO>{

	public int compare(PartyInfoVO obj1, PartyInfoVO obj2) {
		return obj1.getPartyShortName().compareToIgnoreCase(obj2.getPartyShortName());
	}

}
