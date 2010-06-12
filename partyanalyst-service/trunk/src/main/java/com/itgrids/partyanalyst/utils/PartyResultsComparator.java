/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 12, 2010
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class PartyResultsComparator implements Comparator<SelectOptionVO> {

	public int compare(SelectOptionVO a, SelectOptionVO b) {
	 return a.getName().compareToIgnoreCase(b.getName());
	}

}
