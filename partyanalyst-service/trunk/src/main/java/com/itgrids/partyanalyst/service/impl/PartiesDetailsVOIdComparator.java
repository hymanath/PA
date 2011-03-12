package com.itgrids.partyanalyst.service.impl;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.PartiesDetailsVO;

public class PartiesDetailsVOIdComparator  implements Comparator<PartiesDetailsVO>{

	public int compare(PartiesDetailsVO o1, PartiesDetailsVO o2) {
		return o1.getPartyId().toString().compareToIgnoreCase(o2.getPartyId().toString());		
	}
	
}
