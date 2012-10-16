package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import org.hibernate.Query;


import com.itgrids.partyanalyst.model.ProblemComments;

public interface IProblemCommentsDAO extends GenericDao<ProblemComments,Long>{
	public List<Long> getAllApprovedComments(Long problemId);
	@SuppressWarnings("unchecked")
	public List findUserApprovalStatusbetweendates(Date fromDate, Date toDate);
	public int updatesCommentsByAdmin(List<Long> approvalDetailsIds, String isApproved);
	
	public List<Object[]> getProblemComments(Long problemId);
	
	//public Long getCountOfNewlyPostedProblemCommentsByUser();
	
	public List<Object[]> getAllProblemComments(Long problemId,Long userId,List<Long> userIds);
	
	public Long getCountOfNewlyPostedProblemCommentsByUser(Date currentDate);
		
	public List<Long> getAllProblemsCommentedByMe(Long userId);
}
