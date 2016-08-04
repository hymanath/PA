package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BirthDayDetailsVO;

public interface IBirthDayDetailsService {

	public List<BirthDayDetailsVO> getLeaderOccasionDetails(Long occastionTypeId,String dataBuildTypeStr,String memberTypeStr);
	public String getWishingDetails(Long searchId);
}
