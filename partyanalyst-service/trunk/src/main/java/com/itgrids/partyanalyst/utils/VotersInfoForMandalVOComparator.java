package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;

/**
 * 
 * @author Ravi Kiran.Y
 * Date:   04-07-10
 * Purpose : For sorting percentage in ConstituencyVotingTrendzAction (method -- getMandalsVotesShare).
 *
 */
public class VotersInfoForMandalVOComparator implements	Comparator<VotersInfoForMandalVO> {

	public int compare(VotersInfoForMandalVO a, VotersInfoForMandalVO b) {
		return b.getPercent().compareTo(a.getPercent());
	}

}
