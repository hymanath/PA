package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;

public class CandidatePartyInfoVOComparator implements Comparator<CandidatePartyInfoVO> {

	public int compare(CandidatePartyInfoVO a, CandidatePartyInfoVO b) {
		return a.getParty().compareTo(b.getParty());
	}

}
