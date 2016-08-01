package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.UserDataVO;

public interface ICoreDashboardService {
	
	public UserDataVO getUserBasicDetails(Long userId);
	public CommitteeVO getCommitteesCumulativeBasicReportChart(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,Long committeeId,String startDateString,String endDateString);
	public List<CommitteeVO> getCommitteesCumulaticeOverallReportCharts(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public List<CommitteeVO> getCommitteesComparativeBascicReportChart(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String firstMonthString,String secondMonthString);
	public List<CommitteeVO> getCommitteesComparativeOverallReportChart(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String firstMonthString,String secondMonthString);
}
