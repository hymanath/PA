package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyElectionResultVO;

public class SortByRankOnPartyElectionResultComparator implements Comparator<PartyElectionResultVO> {

	public int compare(PartyElectionResultVO a, PartyElectionResultVO b) {
		return b.getRank().compareTo(a.getRank());
	}

}
