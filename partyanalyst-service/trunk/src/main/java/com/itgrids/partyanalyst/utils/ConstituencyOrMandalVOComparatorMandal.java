/**
 * 
 */
package com.itgrids.partyanalyst.utils;

import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ConstituencyOrMandalWiseElectionVO;

/**
 * @author Sai Krishna
 *
 */
public class ConstituencyOrMandalVOComparatorMandal implements Comparator<ConstituencyOrMandalWiseElectionVO> {

	public int compare(ConstituencyOrMandalWiseElectionVO a,
			ConstituencyOrMandalWiseElectionVO b) {
		return a.getLocationName().compareToIgnoreCase(b.getLocationName());
	}

}
