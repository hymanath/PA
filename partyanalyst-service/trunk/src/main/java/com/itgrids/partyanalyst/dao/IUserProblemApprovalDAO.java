package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.UserProblemApproval;

public interface IUserProblemApprovalDAO extends GenericDao<UserProblemApproval, Long> {
	@SuppressWarnings("unchecked")
	List findProblemApprovalsByUser(Long userId, Long problemHistoryId);
	@SuppressWarnings("unchecked")
	List findApprovalInfoForProblem(Long problemHistoryId, int startIndex, int maxResult);
	@SuppressWarnings("unchecked")
	List findCountOfPosts(Long problemHistoryId);
	
}
