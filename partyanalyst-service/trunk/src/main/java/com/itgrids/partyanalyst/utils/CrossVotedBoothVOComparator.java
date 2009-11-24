package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.CrossVotedBoothVO;

public class CrossVotedBoothVOComparator implements Comparator<CrossVotedBoothVO>{

	public int compare(CrossVotedBoothVO obj1, CrossVotedBoothVO obj2) {
		return obj1.getPartNO().compareTo(obj2.getPartNO());
	}

}
