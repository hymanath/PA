package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;

public class PartyElectionResultVOByRankComparator implements Comparator<PartyElectionResultsVO> {

	public int compare(PartyElectionResultsVO a, PartyElectionResultsVO b) {
		return a.getRank().compareTo(b.getRank());
	}

}
