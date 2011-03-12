package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ContenetTransferVO;

public class ContenetTransferVOIdComparator implements Comparator<ContenetTransferVO> {		
	
	public int compare(ContenetTransferVO o1, ContenetTransferVO o2) {
		return o1.getCount().toString().compareToIgnoreCase(o2.getCount().toString());		
	}
	
}
