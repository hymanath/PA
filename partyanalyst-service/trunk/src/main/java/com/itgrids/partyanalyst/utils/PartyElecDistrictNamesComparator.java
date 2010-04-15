/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 15,2010
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyPositionsInDistrictVO;

public class PartyElecDistrictNamesComparator implements Comparator<PartyPositionsInDistrictVO> {

	public int compare(PartyPositionsInDistrictVO a,PartyPositionsInDistrictVO b) {
		return a.getDistrictName().compareToIgnoreCase(b.getDistrictName());
	}
	
}
