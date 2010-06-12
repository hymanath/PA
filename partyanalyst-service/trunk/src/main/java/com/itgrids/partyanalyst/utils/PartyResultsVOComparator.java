/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 12, 2010
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyResultsVO;

public class PartyResultsVOComparator implements Comparator<PartyResultsVO> {

	public int compare(PartyResultsVO a, PartyResultsVO b) {
	  return a.getPartyName().compareToIgnoreCase(b.getPartyName());
	}

}
