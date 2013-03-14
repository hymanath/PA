package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
			String order, String columnName, String reasonType, List<Long> connectedUserIds);
	
	public Long getAllRecordsCountForPostedProblemsByAnanymousUserId(Long userId, String reasonType, List<Long> conectedUserIds);
	
	public List getAllPostedProblemCount(Long userId);
	
	public List getAllPostedProblemCountOtherThanLoggedInUser(Long userId);
	
	public String getCommonDataForAllProblems();
	
	public List getAllProblemHistoryIdsForGivenLocationByTheirIds(List<Long> locationIds,String impactLevel,String isApproved);

	public List<Object[]> getStates();
	
	public List<Long> getUserProblemIdByUserIdAndProblemId(Long userId, Long problemId);
	
	
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
	
	public List<UserProblem> getProblemsPostedByUserInDifferentLifeCycleStagesByDate(Long userId,Integer startIndex, Integer maxResults);

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

	
	public List<UserProblem> getCadreProblemsInARegionByUserProblemId(Long userId,Long problemId);
	
	public List<String> checkIsProblemOwner(Long problemId,Long userId);
	
	public List<Object[]> getProblemOwnerName(Long problemId);
	
	public List<Object[]> getProblemDetailsByProblemReferenceNo(String problemReferenceNo);
	
	public List<Long> checkIsPublicProblem(Long problemId);
	
	public List<UserProblem> getProblemAndOwnerDetails(Long problemId);
	
	public List<Long> checkIsTakenUpProblem(Long problemId,Long userId);
	
	public List<Long> checkIsTakenUpProblemIsInPublicVisiblty(Long problemId);
	
	public List<Long> getUserIds(Long problemId);
	
	public int makeProblemPublic(Long problemId,Long visibility);
	
	public List<Problem> getUserProblemsByProblemTyp(Long problemId ,String problemTyp);
	
	public List<Problem> geUserProblemsByConstituency(List<Long> problemIds,Long cnstncyId,Long userId);
	
	public List<Problem> getProblemsByDistrictId(List<Long> problemIds,Long districtId,Long userId);
	
	public List<Problem> getProblemsByUserId(List<Long> problemIds,Long userId);
	
	public List<UserProblem> getUserProblemByUserAndProblemId(Long problemId,Long userId);
		
	public List<Object[]> getAllProblemPostedUserDetails(Long userId);
	
	public List<Object[]> getAllProblemContainStates(List<Long> problemIds);
	
	public List<Long> getCadrePostedProblems(Long userId,boolean isOnlyUserProb);
	
	public List<Problem> getAllProblemsByFilterOptions(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO);
	
	public List<Long> getAllProblemsByFilterOptionsCount(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO);
	
	public List<Object[]> getAllProblemsByFilterOptionsStatusCount(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO);
 
	public List<Long> getAllProblemCount(Long userId,Long stateId,String location);
	
	public List<Long> getMyProblemsCount(String query);
	
	public Long getCommentedByMeProbCount(Long userId,List<Long> problemIds);
	
	public List<Long> getMyProblems(String query);
	
	public List<Long> getCommentedByMeProblemIds(Long userId,List<Long> problemIds);
	
	public Long getRegionWideProbCount(Long stateId,String locationString,List<Long> problemIds);
	
	public List<Object[]> getAllProblemContainDistricts(Long stateId,List<Long> problemIds,String locationscop);
	
	public List<Object[]> getAllProblemContainConstituencies(Long districtId,List<Long> problemIds,String locationscop);
	
	public List<Object[]> getAllProblemContainMandals(Long constituencyId,List<Long> problemIds,String locationscop);
	
	public List<Object[]> getAllProblemContainVillages(Long mandalId,List<Long> problemIds,String locationscop);
	
	public List<Object[]> getAllProblemContainBooths(Long mandalId,List<Long> problemIds,String locationscop);
	
	public List<Object[]> getAllProblemContainMuncpCorpGmc(Long constituencyId,List<Long> problemIds,String locationscop);
	
	public List<Object[]> getAllProblemContainWards(Long localElection,List<Long> problemIds,String locationscop);
	
	 public List<UserProblem> getProblemDeatilsByProblemId(Long problemId);
	 
	 public List<UserProblem> getUserProblemId(Long userId,Long problemId);
	 
	 public List getConnectedUsersProblemCount(List<Long> connectedUserIds);
	 
	 public List<Object[]> getProblemDetailsForPublicProfile(Long userId,int startIndex, int maxIndex);
	 
	// public List<Long> getAllPublicProblemsByLocation(Long userId,Long locationId,Long locationValue,String status);
	 public List<Long> getAllProblemsByLocation(Long userId,Long locationId,Long locationValue,String status);
	 
	// public Long getAllPrivateProblemsByLocation(Long locationValue,Long userId,Long locationId,String status);
	 
	 public List<Long> getAllUserProblemsBySource(Long locationValue,Long userId,Long locationId,Long sourceId);
	 
	 public List<Long> getAllProblemsByLocation(Long userId,Long locationId,Long locationValue,String status,Integer startIndex,Integer maxIndex);
	 
	 public List<Long> getAllProblemsBySource(Long locationValue,Long userId,Long locationId,Long sourceId,Integer startIndex,Integer maxIndex);
	 
	 public Long getCountOfNewlyPostedProblemsByPublicUser(Date currentDate);
	 
	 public List getProblemDetailsByLocationValuesList(List<Long> locationValuesList, String problemScope, String visibilityType);
	 
}
