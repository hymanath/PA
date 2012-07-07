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
		return getHibernateTemplate().find("select count(model.problemCommentsId) from ProblemComments model where model.isApproved ='"+IConstants.TRUE+"' and model.isDelete = null and model.problem.problemId = ?",problemId);
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
}
