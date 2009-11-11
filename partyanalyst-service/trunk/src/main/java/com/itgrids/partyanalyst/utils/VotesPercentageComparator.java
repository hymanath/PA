/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 10, 2009
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.DistrictWiseConstituencyElectionResultsVO;

public class VotesPercentageComparator implements Comparator<DistrictWiseConstituencyElectionResultsVO> {

	public int compare(DistrictWiseConstituencyElectionResultsVO o1,DistrictWiseConstituencyElectionResultsVO o2) {
		
		return o1.getDistrictWiseVotesPercntDiff().compareTo(o2.getDistrictWiseVotesPercntDiff());
	}

	
}
