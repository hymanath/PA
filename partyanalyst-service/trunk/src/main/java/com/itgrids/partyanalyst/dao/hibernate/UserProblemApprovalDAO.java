package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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

/*@SuppressWarnings("unchecked")
public List findApprovalInfoForProblem(Long problemHistoryId) {
	
	return getHibernateTemplate().find("select model.userApprovalDetails.user.userId, model.userApprovalDetails.user.name, model.userApprovalDetails.user.lastName," +
			" model.userApprovalDetails.approvalDetails.reason, model.userApprovalDetails.approvalDetails.isApproved, model.userApprovalDetails.approvalDetails.postedDate from UserProblemApproval model" +
			" where model.problemHistory.problemHistoryId = ? order by date(model.userApprovalDetails.approvalDetails.postedDate) desc", problemHistoryId);
}*/

@SuppressWarnings("unchecked")
public List findApprovalInfoForProblem(Long problemHistoryId, int startIndex, int maxResult) {
	Query queryObject = getSession().createQuery("select model.userApprovalDetails.user.userId," +
			" model.userApprovalDetails.user.name, model.userApprovalDetails.user.lastName," +
			" model.userApprovalDetails.approvalDetails.reason, model.userApprovalDetails.approvalDetails.isApproved," +
			" model.userApprovalDetails.approvalDetails.postedDate,model.userApprovalDetails.approvalDetails.approvalDetailsId" +
			" from UserProblemApproval model where model.problemHistory.problemHistoryId = ?" +
			" and model.userApprovalDetails.approvalDetails.isAdminApproved = 'true' order by" +
			" model.userApprovalDetails.approvalDetails.approvalDetailsId desc");
	//date(model.userApprovalDetails.approvalDetails.postedDate) 
	queryObject.setLong(0, problemHistoryId);
	queryObject.setFirstResult(startIndex);
	queryObject.setMaxResults(maxResult);
	return queryObject.list();
}

@SuppressWarnings("unchecked")
public List findCountOfPosts(Long problemHistoryId) {
	
Object[] params = {"true",problemHistoryId};
	return getHibernateTemplate().find("select count(model.userProblemApprovalId)," +
			" count(model.userApprovalDetails.approvalDetails.isApproved), " +
			" model.userApprovalDetails.approvalDetails.isApproved from UserProblemApproval model" +
			" where model.userApprovalDetails.approvalDetails.isAdminApproved = ?" +
			" and model.problemHistory.problemHistoryId = ? group by " +
			" model.userApprovalDetails.approvalDetails.isApproved ", params);
}
}
