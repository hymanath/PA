package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.CrossVotedMandalVO;

public class CrossVotedMandalVOComparator implements Comparator<CrossVotedMandalVO>{

	public int compare(CrossVotedMandalVO obj1, CrossVotedMandalVO obj2) {
		return obj1.getPercentageDifferenceInMandal().compareTo(obj2.getPercentageDifferenceInMandal());
	}
}
