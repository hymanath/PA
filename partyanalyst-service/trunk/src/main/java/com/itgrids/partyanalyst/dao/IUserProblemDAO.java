package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


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

}
