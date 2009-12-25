/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

public class ElectionYearsComparator implements Comparator<String> {

	public int compare(String o1, String o2) {
		return Long.valueOf(o2).compareTo(Long.valueOf(o1));
	}

	

}
