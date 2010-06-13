package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;


public class DistrictNamesComparator implements Comparator<CandidateElectionResultVO> {

	
	public int compare(CandidateElectionResultVO o1, CandidateElectionResultVO o2) {
		return o1.getDistrictName().compareToIgnoreCase(o2.getDistrictName());
		
	}

}
