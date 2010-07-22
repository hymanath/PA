package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ByeElecGroupVO;

public class ByeElecGroupVOComparator implements Comparator<ByeElecGroupVO>{

	public int compare(ByeElecGroupVO o1, ByeElecGroupVO o2) {
		return new Float(o2.getPercenatge()).compareTo(new Float(o1.getPercenatge()));
	}

}
