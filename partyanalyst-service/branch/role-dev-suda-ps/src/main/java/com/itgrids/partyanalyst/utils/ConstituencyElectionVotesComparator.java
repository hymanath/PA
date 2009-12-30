/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 10, 2009
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ConstituencyElectionsDetailedResultVO;

public class ConstituencyElectionVotesComparator implements Comparator<ConstituencyElectionsDetailedResultVO> {

	public int compare(ConstituencyElectionsDetailedResultVO o1,ConstituencyElectionsDetailedResultVO o2) {
		return o1.getVotesPercntDiff().compareTo(o2.getVotesPercntDiff());
	}

	
}
