package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.model.Nomination;

public class NominatResultsComparator implements Comparator<Nomination> {

	@Override
	public int compare(Nomination o1, Nomination o2) {
		return o1.getCandidateResult().getRank().compareTo(o2.getCandidateResult().getRank());
	}	
}
