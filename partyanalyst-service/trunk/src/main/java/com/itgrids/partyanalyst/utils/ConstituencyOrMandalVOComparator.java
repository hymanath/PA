package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ConstituencyOrMandalWiseElectionVO;

public class ConstituencyOrMandalVOComparator implements Comparator<ConstituencyOrMandalWiseElectionVO> {

	public int compare(ConstituencyOrMandalWiseElectionVO a,
			ConstituencyOrMandalWiseElectionVO b) {
		return a.getLocationName().compareTo(b.getLocationName());
	}

}
