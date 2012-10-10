package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemDAO extends GenericDaoHibernate<Problem,Long> implements IProblemDAO{

	public ProblemDAO()
	{
		super(Problem.class);
	}	
	
	public Integer deleteProblemDetails(Long problemId,Date currentDate)
	{
		Query queryObj = getSession().createQuery("update Problem model set model.isDelete = 'true', model.updatedTime = :updatedTime where model.problemId = :problemId");
		queryObj.setParameter("problemId", problemId);
		queryObj.setParameter("updatedTime", currentDate);
		return queryObj.executeUpdate();
	}	
	
	/*public Long getCountOfNewlyPostedProblemsByFreeUser()
	{
		Query query = getSession().createQuery("select count(model.problemId) from Problem model where (model.isApproved = 'false' or model.isApproved is null) and (model.isDelete is null or model.isDelete = 'false')");
		return (Long) query.uniqueResult();
	}*/
	
	public Long getCountOfNewlyPostedProblemsByFreeUser(Date currentDate)
	{
		Query query = getSession().createQuery("select count(model.problemId) from Problem model where Date(model.updatedTime) = ? and (model.isDelete is null or model.isDelete = 'false')");
		query.setParameter(0, currentDate);
		return (Long)query.uniqueResult();
		
	}
	public List<Long> isProblemDeleted(Long problemId){
		Query query = getSession().createQuery("select count(model.problemId) from Problem model where model.problemId = :problemId and model.isApproved = :approval  and  model.isDelete = :isdelete ");
		query.setParameter("problemId", problemId);
		query.setParameter("approval", IConstants.TRUE);
		query.setParameter("isdelete", IConstants.FALSE);
		return query.list();
	}
}
