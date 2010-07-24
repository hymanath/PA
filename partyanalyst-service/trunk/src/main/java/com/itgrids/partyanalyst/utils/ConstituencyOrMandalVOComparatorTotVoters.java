package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ConstituencyOrMandalWiseElectionVO;

public class ConstituencyOrMandalVOComparatorTotVoters implements Comparator<ConstituencyOrMandalWiseElectionVO> {

	public int compare(ConstituencyOrMandalWiseElectionVO a,
			ConstituencyOrMandalWiseElectionVO b) {
		return b.getTotalVoters().compareTo(a.getTotalVoters());
	}

}
