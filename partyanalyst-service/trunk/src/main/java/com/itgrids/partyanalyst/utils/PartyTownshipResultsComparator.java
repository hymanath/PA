package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyTownshipResultsVO;

public class PartyTownshipResultsComparator implements Comparator<PartyTownshipResultsVO> {

	public int compare(PartyTownshipResultsVO res1, PartyTownshipResultsVO res2) {
		return res2.getVotesEarned().compareTo(res1.getVotesEarned());
	}

}
