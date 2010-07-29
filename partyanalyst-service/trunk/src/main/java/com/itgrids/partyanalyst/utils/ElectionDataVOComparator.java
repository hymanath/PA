package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ElectionDataVO;

public class ElectionDataVOComparator implements Comparator<ElectionDataVO>{

	public int compare(ElectionDataVO o1, ElectionDataVO o2) {
		return (o2.getElectionYear()+" "+o2.getElectionType()).compareTo(o1.getElectionYear()+" "+o1.getElectionType());
	}

}
