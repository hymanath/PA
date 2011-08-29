package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.ProblemManagementChartVO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.ProblemHistory;

public interface IProblemHistoryDAO extends GenericDao<ProblemHistory, Long>{
	
	//public List findProblemsForALocationsByHamletId(Long hamletId, Long userId);
	
	public List findProblemsForALocationsByTehsilId(Long tehsilId);
	
	//public List findProblemsForALocationsByConstituencyId(String constituencyIds, Long userId);
	
	//public List findProblemsByStatusForALocationsByHamletId(Long hamletId,String status, Long userId);
	
	public List findProblemsByStatusForALocationsByTehsilId(Long tehsilId,String status);
	
	//public List findProblemsByStatusForALocationsByConstituencyId(String constituencyIds,String status);
	
	public List<ProblemHistory> findProblemLocationsByUserId(Long registrationId, Long statusId);
	
	public List findCompleteProblems(Long problemLocationId);
	
	public List getProblemsCountInAllStatusByLocationForAUser(String locationIds, Long registrationId);
	
	public List getProblemsCountInAllStatusByLocation(String locationIds);
	
	public List findProblemsByDateAndLocation(String tehsilIds, Date fromDate, Date toDate);
	
	public List findProblemsByStatusDateAndLocation(String tehsilIds, Long statusId, Date fromDate, Date toDate);
	
	public List findLatestProblemsByMandals(String tehsilIds, Long statusId);
	
	public List findLatestProblemsGroupByDatePostedByMandalsAndStatus(String tehsilIds, String statusIds);
	
	public List<ProblemHistory> findProblemHistoryByProblemLocation(Long problemLocationId);

	public List<AssignedProblemProgress> getAssignedProblemsProgress(Long problemHistoryId);
	
	public List<Object> getAllNonApprovedProblemsBetweenDates(Date fromDate, Date toDate, String status,String isApproved);
	
	public List<Object> getAllNonApprovedProblemsBetweenDatesWithCompleteData(Date fromDate, Date toDate, String status,String isApproved);
	
	public List<Object> getAllNonApprovedProblemsPostedForCurrentDay(Date date,String status,String isApproved);
	
	public List<Object> getCountOfAllNonApprovedProblemsByLocationWiseForCurrentDate(Date date,String status,String isApproved);
	
	@SuppressWarnings("unchecked")
	public List getAllProblemHistoryIdsForGivenLocationByTheirIds(List<Long> locationIds,String impactLevel,String problemType);
	
	@SuppressWarnings("unchecked")
	public List getProblemHistoryByProblemStatusForAUser(Long userId,Long statusId,String isPushed,String isDeleted);
	
	@SuppressWarnings("unchecked")
	public List getProblemHistoryForAUser(Long userId,String isPushed,String isDeleted);
	
	@SuppressWarnings("unchecked")
	public List getProblemsCountInAllStatusByLocation(Long userId);
	
	@SuppressWarnings("unchecked")
	public List findLatestProblemsGroupByDatePostedByMandalsAndStatus(Long userId, String statusIds);
	
	@SuppressWarnings("unchecked")
	public List findProblemsByDateAndLocation(Long userId, Date fromDate, Date toDate);
	
	@SuppressWarnings("unchecked")
	public List findProblemsByStatusDateAndLocation(Long userId, Long statusId, Date fromDate, Date toDate);
	
	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByConstituencyId(Long userId, Long status);
	
	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByConstituencyId(Long userId);
	
	@SuppressWarnings("unchecked")
	public List findProblemCompleteInfo(Long problemHistoryId);	
	
	public List<ProblemHistory> findProblemsForSelectedSearchOptions(Long locationId, Long status, Long userId,String model,String idToCompare,Long deptId,Boolean groupCadre,Boolean groupDept);
	public Long getAllRecordsCountForPostedProblemsByAnanymousUserId(Long registrationId, String reasonType);
	

	@SuppressWarnings("unchecked")
	public List getAllPostedProblemsByAnanymousUserId(Long registrationId, Integer startIndex, Integer results, String order, String columnName, String reasonType);
	
	@SuppressWarnings("unchecked")
	public List getAllPostedProblemCount(Long registrationId);
	
	@SuppressWarnings("unchecked")
	public List getAllPostedProblemCountOtherThanLoggedInUser(Long registrationId);
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getRecentPostedProblemsForAUserByCount(Long userId,Long problemStatusId,Integer startIndex,Integer maxResults);
	
	public Long getRecentPostedProblemsCountForAUserByProblemStatus(Long userId,Long problemStatusId);
	
	@SuppressWarnings("unchecked")
	public List getProblemsPostedForAUserInBetweenDates(Long userId,Long statusId,Integer startIndex,Integer maxResults);
	
	public Long getProblemsCountPostedByAUserInBetweenMonths(Long userId, Long problemStatusId);
	
	@SuppressWarnings("unchecked")
	public List getProblemsPostedForAUserInBetweenDates(Long userId,Integer startIndex,Integer maxResults);
	
	@SuppressWarnings("unchecked")
	public List getProblemsPostedForAUserInBetweenMonths(Long userId,Long statusId,Integer startIndex,Integer maxResults);
	
	public Long getProblemsCountPostedByAUserInBetweenDates(Long userId, Long problemStatusId);
	
	@SuppressWarnings("unchecked")
	public List getProblemsPostedForAUserInBetweenMonths(Long userId,Integer startIndex,Integer maxResults);
	
	@SuppressWarnings("unchecked")
	public List getProblemsCountPostedByAUserInBetweenDates(Long userId);
	
	@SuppressWarnings("unchecked")
	public List getProblemsCountPostedByAUserInBetweenMonths(Long userId);
	
	@SuppressWarnings("unchecked")
	public List getProblemsCountPostedByUserInDifferentLifeCycleStages(Long userId);
	
	public List<ProblemHistory> getProblemsPostedByUserInDifferentLifeCycleStagesByDate(Long userId, Integer startIndex, Integer maxResults);
	
	@SuppressWarnings("unchecked")
	public List getProblemsPostedByUserInDifferentLifeCycleStagesByRecentDate(Long userId, Integer startIndex, Integer maxResults);
	
	public Long getProblemsPostedByUserInDifferentLifeCycleStagesByRecentDate(Long userId);
	
	public List<ProblemHistory> getDifferentLifeCycleProblemsOfAUserPostedBetweenDates(Long userId,Long statusId,Date startDate,Date endDate, Integer startIndex, Integer maxResults);
	
	@SuppressWarnings("unchecked")
	public List getDifferentLifeCycleProblemsCountOfAUserPostedBetweenDates(Long userId,Long statusId,Date startDate,Date endDate);
	
	public List<ProblemHistory> getProblemHistoryBasedOnId(Long historyId);
	
	public List<Object> getTotalProblemsCountForAnUserInARegion(Long userId,String locationStr);
	
	public List<ProblemHistory> getStatusWiseProblemsForAnUserInARegion(Long userId,String locationStr,String statusStr);
	
	public List<Object> getTotalProblemsStatusForAnUser(Long userId);
	
	public List<ProblemHistory> getStatusWiseProblemsForAnUser(Long userId,String statusStr);
	
	public List<Object[]> getCompleteProblemDetailsBySearchString(String query);
	
	public List<Object[]> getProblemDetailsByName(String name);
	
}
