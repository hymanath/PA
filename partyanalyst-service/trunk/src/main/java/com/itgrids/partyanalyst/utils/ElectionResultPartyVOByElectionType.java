package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;

public class ElectionResultPartyVOByElectionType implements Comparator<ElectionResultPartyVO> {

	public int compare(ElectionResultPartyVO a, ElectionResultPartyVO b) {
		return (a.getElectionYear()+" "+a.getElectionType()).compareTo(b.getElectionYear()+" "+b.getElectionType());
	}

}
