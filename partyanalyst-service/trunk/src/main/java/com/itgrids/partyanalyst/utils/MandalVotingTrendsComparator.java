package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;

public class MandalVotingTrendsComparator implements Comparator<MandalAllElectionDetailsVO>{

	public int compare(MandalAllElectionDetailsVO obj1,	MandalAllElectionDetailsVO obj2) {	
		return obj1.getElectionYear().compareTo(obj2.getElectionYear());
	}

}
