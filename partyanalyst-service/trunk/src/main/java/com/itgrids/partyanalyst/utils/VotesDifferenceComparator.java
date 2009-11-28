/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 28, 2009
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;

public class VotesDifferenceComparator implements Comparator<PartyElectionResultsVO> {

	public int compare(PartyElectionResultsVO o1,PartyElectionResultsVO o2) {
		
		return o2.getVoteDiff().compareTo(o1.getVoteDiff());
	}

	
}
