/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 16,2010
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyWiseResultVO;

public class ElectionResultsForPartiesBySeats implements Comparator<PartyWiseResultVO> {

	public int compare(PartyWiseResultVO a, PartyWiseResultVO b) {
		return b.getTotalSeatsWon().compareTo(a.getTotalSeatsWon());
	}

}
