package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;

public class TehsilVotingTrendsComparator implements Comparator<TeshilPartyInfoVO> {
	public int compare(TeshilPartyInfoVO a, TeshilPartyInfoVO b) {
		return b.getSeatsWonByParty().compareTo(a.getSeatsWonByParty());
	  }
}
