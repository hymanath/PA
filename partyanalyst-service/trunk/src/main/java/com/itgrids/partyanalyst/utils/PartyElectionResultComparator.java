package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyElectionResultVO;

public class PartyElectionResultComparator implements Comparator<PartyElectionResultVO> {

	public int compare(PartyElectionResultVO a, PartyElectionResultVO b) {
		return a.getPartyName().compareTo(b.getPartyName());
	}

}
