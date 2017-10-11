package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.LocationAlertVO;


public interface IAlertLocationDashboardService {
	public  LocationAlertVO getTotalAlertDetailsForConstituencyInfo(String fromDateStr ,String toDateStr,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year);
	public  List<LocationAlertVO> getDesignationWiseAlertsOverview(String fromDateStr ,String toDateStr,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year);
	public  List<AlertCoreDashBoardVO> getAlertOverviewClick(String fromDateStr ,String toDateStr,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year,
			List<Long> statusIdsList,List<Long> impactIdsList,String type,Long designationId,List<Long> alertCategeryIdsList,String otherCategory);
}
