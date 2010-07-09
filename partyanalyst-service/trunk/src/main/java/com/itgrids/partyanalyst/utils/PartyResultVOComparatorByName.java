package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyResultVO;

public class PartyResultVOComparatorByName implements Comparator<PartyResultVO> {

	public int compare(PartyResultVO a, PartyResultVO b) {
		return a.getConstituencyName().compareTo(b.getConstituencyName());
	}

}
