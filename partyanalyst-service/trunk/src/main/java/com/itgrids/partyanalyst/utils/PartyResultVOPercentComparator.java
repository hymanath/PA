package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyResultVO;

public class PartyResultVOPercentComparator implements Comparator<PartyResultVO>{

	public int compare(PartyResultVO o1, PartyResultVO o2) {
		return new Float(o2.getVotesPercent()).compareTo(new Float(o1.getVotesPercent()));
	}

}
