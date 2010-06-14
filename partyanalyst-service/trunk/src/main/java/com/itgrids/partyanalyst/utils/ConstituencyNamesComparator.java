package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;

public class ConstituencyNamesComparator implements Comparator<ConstituencyElectionResultsVO> {

	public int compare(ConstituencyElectionResultsVO o1,
			ConstituencyElectionResultsVO o2) {
		
		return o1.getConstituencyName().compareToIgnoreCase(o2.getConstituencyName());
	}

}
