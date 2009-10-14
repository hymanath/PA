package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.excel.CandidateElectionResult;

public class CandidateVotesComparator implements Comparator<CandidateElectionResult> {
	public int compare(CandidateElectionResult a, CandidateElectionResult b) {
		return b.getVotesEarned().compareTo(a.getVotesEarned());
	  }
}
