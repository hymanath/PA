package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.LocationAlertVO;


public interface IAlertLocationDashboardService {
	public  LocationAlertVO getTotalAlertDetailsForConstituencyInfo(String fromDateStr ,String toDateStr,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year);
	
}
