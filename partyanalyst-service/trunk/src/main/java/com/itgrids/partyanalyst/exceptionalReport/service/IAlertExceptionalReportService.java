package com.itgrids.partyanalyst.exceptionalReport.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;

public interface IAlertExceptionalReportService {
	public List<AlertCoreDashBoardVO> getAssignedCandidateWisePendingAlerts(String startDate,String endDate, Long stateId,int size,List<Long> alertTypeId);
	public List<AlertCoreDashBoardVO> getOverAllAlertsDetails(String startDate,String endDate, Long stateId,int size,List<Long> alertTypeIds);
	public List<AlertCoreDashBoardVO> getDistrictWiseAlertsDetails(String startDate,String endDate, Long stateId,int size,List<Long> alertTypeIds);
	public List<AlertCoreDashBoardVO> getLocationWiseAlertsDetails(String startDate,String endDate, Long stateId,int size,List<Long> alertTypeIds,String locationType);
}
