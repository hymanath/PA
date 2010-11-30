package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class SelectOptionVOByIdComparator implements Comparator<SelectOptionVO>{

	public int compare(SelectOptionVO a, SelectOptionVO b) {
		  return a.getId().toString().compareToIgnoreCase(b.getId().toString());
	}
}
