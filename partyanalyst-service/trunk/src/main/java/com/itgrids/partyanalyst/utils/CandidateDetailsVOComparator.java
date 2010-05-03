package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;

public class CandidateDetailsVOComparator implements Comparator<CandidateDetailsVO> {
	public int compare(CandidateDetailsVO a, CandidateDetailsVO b) {
		return b.getCandidateVotesEarned().compareTo(a.getCandidateVotesEarned());
	  }
}
