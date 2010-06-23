package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyElectionResultVO;

public class PartyElectionResultVOComparator implements Comparator<PartyElectionResultVO>{

	public int compare(PartyElectionResultVO obj1, PartyElectionResultVO obj2) {
		return obj2.getElectionTypeYear().compareTo(obj1.getElectionTypeYear());
	}

}
