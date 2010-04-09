/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 09,2010
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.DistrictWisePartyPositionsVO;

public class PartyElecDistrictResultsComparator implements Comparator<DistrictWisePartyPositionsVO> {

	public int compare(DistrictWisePartyPositionsVO o1,DistrictWisePartyPositionsVO o2) {
		return o2.getTotSeatsWonInAllPartiDistricts().compareTo(o1.getTotSeatsWonInAllPartiDistricts());
	}
	
}
