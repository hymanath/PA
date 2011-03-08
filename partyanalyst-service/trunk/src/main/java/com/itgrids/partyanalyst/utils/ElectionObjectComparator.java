package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartiesDetailsVO;

public class ElectionObjectComparator implements Comparator<PartiesDetailsVO>{
	public int compare(PartiesDetailsVO a,PartiesDetailsVO b) {
		return b.getConstituencyId().compareTo(a.getConstituencyId());
	}
}
