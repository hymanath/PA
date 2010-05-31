package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ProblemsCountByStatus;

public class ProblemsCountByStatusComparator implements
		Comparator<ProblemsCountByStatus> {
	public int compare(ProblemsCountByStatus a, ProblemsCountByStatus b) {
		return a.getDate().compareTo(b.getDate());
	}

}
