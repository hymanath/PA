package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ElectionResultVO;

public class ElectionResultComparator implements Comparator<ElectionResultVO>{

	public int compare(ElectionResultVO a, ElectionResultVO b) {
		return a.getElectionYear().compareTo(b.getElectionYear());
	}

}
