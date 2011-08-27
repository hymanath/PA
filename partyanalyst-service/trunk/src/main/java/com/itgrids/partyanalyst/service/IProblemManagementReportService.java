package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemClassificationVO;
import com.itgrids.partyanalyst.dto.ProblemCompleteDetailsVO;
import com.itgrids.partyanalyst.dto.ProblemHistoryVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IProblemManagementReportService {
	
	//public List<ProblemBeanVO> getHamletProblemsInfo(Long hamletId,Long registrationId,String taskType);
	
	public List<ProblemBeanVO> getTehsilProblemsInfo(Long tehsilId,Long registrationId,String taskType);
	
	public List<ProblemBeanVO> getProblemsInfoBasedOnLocation(Long locationId,Long userId,Long status, Long regionScope,Long deptId);
	
	public List<ProblemClassificationVO> getProblemsInfoBasedOnLocation(Long locationId,Long userId,Long status, Long regionScope,Long deptId,Boolean groupCadre,Boolean groupDept);
	
	public List<ProblemHistoryVO> getCompleteDetailsForAProblem(Long problemLocationId);
	
	public LocationwiseProblemStatusInfoVO getProblemsStatusCount(Long userId,String accessType, Long accessValue, int limit);
	
	public List<SelectOptionVO> getAllProblemStatusInfo();
	
	public List<ProblemBeanVO> getProblemsPostedByStatusAndDates(Long userId,String fromDate, String toDate, Long statusId, String accessType, Long accessValue);
	
	public List<InfluencingPeopleVO> findInfluencingPeopleInfoInLocation(String accessType, Long accessValue, Long hamletId, String flag,Long userId);
	
	public List<ProblemBeanVO> getProblemsInfoByStatusInALocation(Long accessValue,String accessType,Long registrationId,Long taskType);
	
	public LocationwiseProblemStatusInfoVO getRecentProblemsWithInTheRegion(String accessType, Long accessValue, Long statusId, int limit);
	
	public ProblemBeanVO getProblemHistoryInfo(Long problemLocationId);
	
	public NavigationVO getProblemsCountInAWeek(Date fromDate,Date toDate,String status,String type);
	
	public NavigationVO getAllApprovalProblemsBetweenTheDates(String fromDate,String toDate,String status,String type);
	
	public NavigationVO getAllApprovalProblemsForTheCurrentDay(String status,String type);
	
	public NavigationVO getCountOfAllNonApprovedProblemsByLocationWiseForCurrentDate(Date date,String status,String type);

	public void deleteSelectedProblemsByAdmin(Integer[] problemIds);
	
	public void acceptSelectedProblemsByAdmin(Integer[] problemHistoryIds);
	
	public NavigationVO getAllApprovalProblemsForSelectedDate(Date date,String status,String type);
	
	
	
	//Problem Retrival Methods (can be retrived based on locationIds and locationType)
	
	public NavigationVO getAllProblemsForGivenLocation(List<Long> locationIds,String locationType);
	
	public List<Object> getAllAcceptedProblemsInAWard(List<Long> locationIds, String locationType); 
	
	public List<Object> getAllAcceptedProblemsInAHamlet(List<Long> locationIds, String locationType);
	
	public List<Object> getAllAcceptedProblemsInATehsil(List<Long> locationIds, String locationType);
	
	public List<Object> getAllAcceptedProblemsInALocalElectionBody(List<Long> locationIds, String locationType);
	
	public List getAllAcceptedProblemsInAConstituency(List<Long> locationIds, String locationType);
	
	public List<Object> getAllAcceptedProblemsInADistrict(List<Long> locationIds, String locationType); 
	
	public List<Object> getAllAcceptedProblemsInAState(List<Long> locationIds, String locationType);
	
	public List<SelectOptionVO> getTotalProblemsCountForAnUserInARegion(Long userId,Long impactedRegionId,Long locationId);
	
	public List<ProblemBeanVO> getStatusWiseProblemsForAnUserInARegion(Long userId,Long impactedRegionId,Long locationId,String status);
		
	public List<SelectOptionVO> getTotalProblemsStatusForAnUser(Long userId);
	
	public List<ProblemBeanVO> getStatusWiseProblemsForAnUser(Long userId,String status);
	
	public List<SelectOptionVO> getCadreProblemsCountInARegion(Long userId,Long impactedRegionId,Long locationId);
	
	public List<ProblemBeanVO> getCadreProblemsInARegion(Long userId,Long impactedRegionId,Long locationId,String status);
	
	public List<ProblemBeanVO> getDeptWiseProblemsCountForAnUser(Long userId,Long deptScopeId);
	
	public List<ProblemBeanVO> getDepartmentWiseProblemsBasedOnStatus(Long userId,Long deptId,String status);
	
	public List<ProblemBeanVO> getProblemsInADeptScopeBasedOnScope(Long userId,Long scopeId,String status);
	
	public List<ProblemBeanVO> getDeptWiseProblemsCountInALocation(Long userId,Long impactedRegionId,Long locationId);
	
	public List<ProblemBeanVO> getDeptProblemsBasedOnStatusInARegion(Long userId,Long impactedRegionId,Long locationId,Long deptId,String status);
}
