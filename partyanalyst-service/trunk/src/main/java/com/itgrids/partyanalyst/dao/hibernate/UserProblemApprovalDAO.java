package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserProblemApprovalDAO;
import com.itgrids.partyanalyst.model.UserProblemApproval;

public class UserProblemApprovalDAO extends GenericDaoHibernate<UserProblemApproval, Long> implements IUserProblemApprovalDAO  {

	public UserProblemApprovalDAO() {
		super(UserProblemApproval.class);
		
	}
	
@SuppressWarnings("unchecked")
public List findProblemApprovalsByUser(Long userId, Long problemHistoryId) {
		Object[] params = {userId, problemHistoryId};
		return getHibernateTemplate().find("select model.userApprovalDetails.approvalDetails.approvalDetailsId, model.userApprovalDetails.approvalDetails.reason, model.userApprovalDetails.approvalDetails.isApproved" +
											" from UserProblemApproval model where model.userApprovalDetails.user.userId = ? and model.problemHistory.problemHistoryId = ?", params);
	}

@SuppressWarnings("unchecked")
public List findApprovalInfoForProblem(Long problemHistoryId) {
	
	return getHibernateTemplate().find("select model.userApprovalDetails.user.userId, model.userApprovalDetails.user.name, model.userApprovalDetails.user.lastName," +
			" model.userApprovalDetails.approvalDetails.reason, model.userApprovalDetails.approvalDetails.isApproved, model.userApprovalDetails.approvalDetails.postedDate from UserProblemApproval model" +
			" where model.problemHistory.problemHistoryId = ? order by model.userApprovalDetails.approvalDetails.postedDate", problemHistoryId);
}

@SuppressWarnings("unchecked")
public List findCountOfPosts(Long problemHistoryId) {
	
	return getHibernateTemplate().find("select count(model.userProblemApprovalId), count(model.userApprovalDetails.approvalDetails.isApproved), model.userApprovalDetails.approvalDetails.isApproved from UserProblemApproval model" +
			" where model.userApprovalDetails.approvalDetails.isApproved is not null and model.problemHistory.problemHistoryId = ? group by model.userApprovalDetails.approvalDetails.isApproved", problemHistoryId);
}
}
