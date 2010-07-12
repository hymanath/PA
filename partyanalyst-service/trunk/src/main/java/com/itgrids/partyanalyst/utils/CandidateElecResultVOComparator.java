package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;

public class CandidateElecResultVOComparator implements Comparator<CandidateElectionResultVO> {

	public int compare(CandidateElectionResultVO a,
			CandidateElectionResultVO b) {
		return b.getTotalVotesEarned().compareTo(a.getTotalVotesEarned());
	}

}
