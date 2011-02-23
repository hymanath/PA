package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartiesDetailsVO;

public class PartiesDetailsVOComparator implements Comparator<PartiesDetailsVO>{

	public int compare(PartiesDetailsVO o1, PartiesDetailsVO o2) {
		return o1.getPartyName().compareToIgnoreCase(o2.getPartyName());		
	}
	
}
