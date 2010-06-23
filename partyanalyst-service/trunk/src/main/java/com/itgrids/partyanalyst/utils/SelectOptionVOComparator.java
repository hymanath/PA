package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class SelectOptionVOComparator implements Comparator<SelectOptionVO> {

	public int compare(SelectOptionVO a, SelectOptionVO b) {
		  return b.getName().compareToIgnoreCase(a.getName());
	}
	
}
