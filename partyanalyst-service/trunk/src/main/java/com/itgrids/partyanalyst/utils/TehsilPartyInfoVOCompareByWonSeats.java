package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;

public class TehsilPartyInfoVOCompareByWonSeats implements Comparator<TeshilPartyInfoVO> {

	public int compare(TeshilPartyInfoVO a, TeshilPartyInfoVO b) {
		return b.getPartyWonSeats().compareTo(b.getPartyWonSeats());
	}

}
