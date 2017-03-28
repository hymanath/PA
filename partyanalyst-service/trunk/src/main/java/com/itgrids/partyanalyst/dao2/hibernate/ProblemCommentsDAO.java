package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemCommentsDAO;
import com.itgrids.partyanalyst.model.ProblemComments;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemCommentsDAO extends GenericDaoHibernate<ProblemComments,Long> implements IProblemCommentsDAO{

	public ProblemCommentsDAO()
	{
		super(ProblemComments.class);
	}
	public List<Long> getAllApprovedComments(Long problemId)
	{
		return getHibernateTemplate().find("select count(model.problemCommentsId) from ProblemComments model where model.isApproved ='"+IConstants.TRUE+"' and (model.isDelete = null or model.isDelete = '"+IConstants.FALSE+"') and model.problem.problemId = ?",problemId);
	}
	public List findUserApprovalStatusbetweendates(Date fromDate, Date toDate){
		
		StringBuffer query=new StringBuffer("select model.problemCommentsId, model.comment.comment,  model.insertedTime, model.user.firstName, model.user.lastName ,model.isApproved ");
		query.append(" from ProblemComments model where date(model.insertedTime) >= :start and date(model.insertedTime) <= :end and model.isDelete =:isDelete ");
		Query queryObject=getSession().createQuery(query.toString());
		queryObject.setParameter("isDelete", "false");
		queryObject.setParameter("start", fromDate);
		queryObject.setParameter("end", toDate);
		
		return queryObject.list();
		
	}
	public int updatesCommentsByAdmin(List<Long> approvalDetailsIds, String isApproved)
	{
		Query queryObject = getSession().createQuery("update ProblemComments model set model.isApproved = ? where model.problemCommentsId in (:approvalDetailsIds)");
		queryObject.setParameter(0, isApproved);
		queryObject.setParameterList("approvalDetailsIds", approvalDetailsIds);
		return queryObject.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProblemComments(Long problemId)
	{
		return getHibernateTemplate().find("select model.comment.comment,model.comment.commentId from ProblemComments model where model.problem.problemId = ? and model.isApproved='"+IConstants.TRUE+"' and (model.isDelete = '"+IConstants.FALSE+"' or model.isDelete is null))",problemId);
	}	
	/*public Long getCountOfNewlyPostedProblemCommentsByUser()
	{
		Query query = getSession().createQuery("select count(*) from ProblemComments model where (model.isApproved = 'false' or model.isApproved is null) and (model.isDelete is null or model.isDelete = 'false') ");
		return (Long) query.uniqueResult();
	}*/
	
	public Long getCountOfNewlyPostedProblemCommentsByUser(Date currentDate)
	{
	Query query = getSession().createQuery("select count(*) from ProblemComments model where Date(model.insertedTime) = ? and (model.isDelete is null or model.isDelete = 'false') ");
	query.setParameter(0, currentDate);
	return (Long) query.uniqueResult();
	}
	public List<Object[]> getAllProblemComments(Long problemId,Long userId,List<Long> userIds){
		StringBuilder query = new StringBuilder();
		query.append("select model.comment.comment,model.user.firstName,model.user.lastName,model.user.userId,model.insertedTime,model.user.profileImg, model.comment.commentId from ProblemComments model,UserProblem model1 where model.problem.problemId = model1.problem.problemId and (");
		if(userId != null)
			query.append(" model.user.userId = :userId or ");
		query.append(" (  model1.visibility.visibilityId = :visibilityId  ");
		if(!userIds.isEmpty())
		query.append("and model.user.userId not in(:userIds)");
		query.append(" )) and model.problem.problemId = :problemId  and model.isApproved = :isApproved and model.comment.isAbused = :isAbused and model.isDelete = :isDelete order by model.insertedTime desc");
		Query queryObject = getSession().createQuery(query.toString());
		if(userId != null)
		  queryObject.setParameter("userId", userId);
		queryObject.setParameter("visibilityId", 1l);
		if(!userIds.isEmpty())
			queryObject.setParameterList("userIds",userIds);
		queryObject.setParameter("problemId", problemId);
		queryObject.setParameter("isApproved", IConstants.TRUE);
		queryObject.setParameter("isAbused", IConstants.FALSE);
		queryObject.setParameter("isDelete", IConstants.FALSE);
		return queryObject.list();
	}
	public List<Long> getAllProblemsCommentedByMe(Long userId){
		
		Query query = getSession().createQuery("select distinct(model.problem.problemId) from ProblemComments model where model.user.userId = "+userId+" and model.isApproved = '"+IConstants.TRUE+"' and model.isDelete =  '"+IConstants.FALSE+"' and model.comment.isAbused = '"+IConstants.FALSE+"' ");
		return query.list();
	}
}
