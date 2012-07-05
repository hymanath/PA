package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


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

	public List<Object[]> getProblemPostedUserDetails();
	
	public List<Object[]> getFreeUserProblemsInSearch(ProblemSearchVO problemSearchVO,int startIndex,int maxIndex,boolean countReq);
	
	public Long getFreeUserIdOfAProblem(Long problemHistoryId);
	
	public List<Object[]> checkUserFileUploadRight(Long userId,Long problemHistoryId);
}
