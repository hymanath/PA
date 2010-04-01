/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 31,2010
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyPositionsVO;

public class PartyElectionResultsComparator implements Comparator<PartyPositionsVO> {

	public int compare(PartyPositionsVO a, PartyPositionsVO b) {
		return b.getTotalSeatsWon().compareTo(a.getTotalSeatsWon());
	}

}
