package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.dto.ProblemSearchVO;
import com.itgrids.partyanalyst.model.UserProblem;

public interface IUserProblemDAO extends GenericDao<UserProblem,Long>{
	
	public List<Long> getAllValidProblemIds(Integer startIndex, Integer maxIndex);
	
	public List<Long> getAllValidProblemIdsCount();
	
	public List<Object[]> getProblemCompleteInfo(Long problemId);
	
	public List<UserProblem> getAllProblemDetails(Long problemId);
	
	public List getAllPostedProblemsByAnanymousUserId(Long userId, Integer startIndex, Integer results, 
			String order, String columnName, String reasonType);
	
	public Long getAllRecordsCountForPostedProblemsByAnanymousUserId(Long userId, String reasonType);
	
	public List getAllPostedProblemCount(Long userId);
	
	public List getAllPostedProblemCountOtherThanLoggedInUser(Long userId);
	
	public String getCommonDataForAllProblems();
	
	public List getAllProblemHistoryIdsForGivenLocationByTheirIds(List<Long> locationIds,String impactLevel,String isApproved);

	public List<Object[]> getStates();
	
	public List<Long> getUserProblemIdByUserIdAndProblemId(Long userId, Long problemId);
	
	public List<UserProblem> getUserProblemId(Long problemId,Long userId);
	
	public List<Object> getAllProblemsOfCurrentDateByFreeUser(Date firstDate,Date lastDate,String isApproved);

	public List<Object[]> getProblemPostedUserDetails();
	
	public List<Object[]> getFreeUserProblemsInSearch(ProblemSearchVO problemSearchVO,int startIndex,int maxIndex,boolean countReq);
	
	public Long getFreeUserIdOfAProblem(Long problemHistoryId);
	
	public List<Object[]> checkUserFileUploadRight(Long userId,Long problemHistoryId);
	
	public List<Object> getTotalProblemsCountForAnUserInARegion(Long userId,String locationStr);
	
	public List<Object> getTotalProblemsStatusForAnUser(Long userId);
	
	public List<UserProblem> getStatusWiseProblemsForAnUserInARegion(Long userId,String locationStr,String statusStr);
	
	public List<UserProblem> getStatusWiseProblemsForAnUser(Long userId,String statusStr);
	
	public List getProblemsCountInAllStatusByLocation(Long userId);
	
	public List findLatestProblemsGroupByDatePostedByMandalsAndStatus(String tehsilIds, String statusIds);
	
	public List findLatestProblemsGroupByDatePostedByMandalsAndStatus(Long userId, String statusIds);
	
	public List findLatestProblemsByMandals(String tehsilIds, Long statusId);
	
	public String buildCommonQueryForProblems();
	
	public List findProblemsByStatusForALocationsByConstituencyId(Long userId, Long status);
	
	public List findProblemsForALocationsByConstituencyId(Long userId);	
	public List getProblemsCountPostedByUserInDifferentLifeCycleStages(Long userId);
	
	public List<Problem> getProblemsPostedByUserInDifferentLifeCycleStagesByDate(Long userId,Integer startIndex, Integer maxResults);

	public List<Long> getProblemsPostedByUserInDifferentLifeCycleStagesByDateCount(Long userId);
	
	public List<Problem> getDifferentLifeCycleProblemsOfAUserPostedBetweenDates(
			Long userId,Long statusId, Date startDate, Date endDate, Integer startIndex,
			Integer maxResults);
	
	public List<Long> getDifferentLifeCycleProblemsCountOfAUserPostedBetweenDates(Long userId,Long statusId,Date startDate,Date endDate);
	
	public List<Problem> getProblemHistoryBasedOnId(Long problemId,Long userId);
	public List<UserProblem> getUserProblem(Long problemId, Long userId);
	
	public List findProblemsByDateAndLocation(Long userId, Date fromDate, Date toDate);
	
	public List findProblemsByStatusDateAndLocation(Long userId, Long statusId, Date fromDate, Date toDate);
	
	public List<UserProblem> findProblemDetailsByProblemId(Long problemId);
	
	public List<UserProblem> getUserProblemIdForCadreProblems(Long problemId,Long userId);
	
	public List<UserProblem> getProblemDetailsOfUserToSendEmailAfterProblemApproval(Long problemId);
}
