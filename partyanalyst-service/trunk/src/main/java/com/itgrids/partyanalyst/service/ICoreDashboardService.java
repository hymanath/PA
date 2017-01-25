package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;

public interface ICoreDashboardService {
	
	public UserDataVO getUserBasicDetails(Long userId);
	public CommitteeVO getCommitteesCumulativeBasicReportChart(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,Long committeeId,String startDateString,String endDateString);
	public List<CommitteeVO> getCommitteesCumulativeOverallReportCharts(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public List<CommitteeVO> getCommitteesComparativeBascicReportChart(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String firstMonthString,String secondMonthString);
	public List<CommitteeVO> getCommitteesComparativeOverallReportChart(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String firstMonthString,String secondMonthString);
	public UserTypeVO getLoggedInUserStructure(Long userId);
	
	public List<IdAndNameVO> getActivityDetails(String fromDateStr,String toDateStr);
	public List<IdAndNameVO> getActivityOverAllSummary(Long activityId);
	public List<IdAndNameVO> activitiesDistrictWiseCohort(List<Long> activityIdsLst,String fromDateStr,String toDateStr,Long scopeId);
	public List<EventDetailsVO> getDistrictWiseActivityCounts(Long districtId,Long activityScopeId, String searchType ,Long stateId );
}
