package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;

public class ElectionDetailsVOComparator implements Comparator<ConstituencyElectionResultsVO> {

	

	public int compare(ConstituencyElectionResultsVO a,ConstituencyElectionResultsVO b) {
		return b.getElectionYear().compareTo(a.getElectionYear());
	}

}
