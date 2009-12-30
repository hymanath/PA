package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyInfoVO;

public class PartyInfoComparator implements Comparator<PartyInfoVO> {

	  public int compare(PartyInfoVO partyInfo1, PartyInfoVO partyInfo2) {
		  // for descending order 2nd object is compared with 1st obj
		  //if(partyInfo1==null || partyInfo2==null)
			int result = partyInfo2.getSeatsWin().compareTo(partyInfo1.getSeatsWin());
			if(result==0)
				result = partyInfo2.getPercentageOfVotes().compareTo(partyInfo1.getPercentageOfVotes());
			return result;
	  }
}
