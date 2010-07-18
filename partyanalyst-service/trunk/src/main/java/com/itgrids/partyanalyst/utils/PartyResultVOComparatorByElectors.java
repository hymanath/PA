package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyResultVO;

public class PartyResultVOComparatorByElectors implements Comparator<PartyResultVO> {

	public int compare(PartyResultVO a, PartyResultVO b) {
		return b.getElectors().compareTo(a.getElectors());
	}

}
