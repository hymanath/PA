package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.LocationVotersVO;



public interface IMeetingLocationDashboardService {
	public List<LocationVotersVO> getLocationWiseMeetingsCount(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr);
	
}
