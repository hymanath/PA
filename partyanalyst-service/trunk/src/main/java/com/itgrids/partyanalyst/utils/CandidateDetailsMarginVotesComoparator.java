package com.itgrids.partyanalyst.utils;

import java.util.Comparator;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;

public class CandidateDetailsMarginVotesComoparator implements Comparator<CandidateDetailsVO> {

	public int compare(CandidateDetailsVO a, CandidateDetailsVO b) {
		return a.getMarginVotesPercentage().compareTo(b.getMarginVotesPercentage());
	}
}