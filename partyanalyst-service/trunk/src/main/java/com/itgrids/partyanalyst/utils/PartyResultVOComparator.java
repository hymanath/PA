package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyResultVO;

public class PartyResultVOComparator implements Comparator<PartyResultVO> {

	public int compare(PartyResultVO obj1, PartyResultVO obj2) {
		return obj2.getSeatsWonCountToSort().compareTo(obj1.getSeatsWonCountToSort());
	}
	
}
