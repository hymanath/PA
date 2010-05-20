package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartyPostionInfoVO;

public class PartyPostionInfoVOComparator implements Comparator<PartyPostionInfoVO>{
	public int compare(PartyPostionInfoVO a, PartyPostionInfoVO b){
		return a.getRank().compareTo(b.getRank());
	}
}
