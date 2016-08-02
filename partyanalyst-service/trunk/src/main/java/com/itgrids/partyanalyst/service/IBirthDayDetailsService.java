package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.BirthDayDetailsVO;

public interface IBirthDayDetailsService {

	public BirthDayDetailsVO getLeaderOccasionDetails(Long occastionTypeId);
	public String getWishingDetails(Long searchId);
	public BirthDayDetailsVO getAllLstOfDaysForBdayDtails(String searchType,Long occastionTypeId);
}
