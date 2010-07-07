package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ElectionResultVO;

public class ElectionResultTypeComparator implements Comparator<ElectionResultVO>{

	public int compare(ElectionResultVO o1, ElectionResultVO o2) {
		return o1.getElectionYearAndType().compareTo(o2.getElectionYearAndType());
	}

}
