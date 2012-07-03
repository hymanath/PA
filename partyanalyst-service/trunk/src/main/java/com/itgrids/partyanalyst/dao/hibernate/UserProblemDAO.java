package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.model.UserProblem;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserProblemDAO extends GenericDaoHibernate<UserProblem,Long> implements IUserProblemDAO{

	public UserProblemDAO()
	{
		super(UserProblem.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllValidProblemIds(Integer startIndex, Integer maxIndex)
	{
		Query query = getSession().createQuery("select model.userProblemId from UserProblem model where model.visibility.type = ? and model.isOwner = ? " +
				" and model.problem.isApproved = ?  and model.problem.isDelete = ? and model.problem.regionScopes.regionScopesId <= 4 ");
		 
		query.setParameter(0,IConstants.PUBLIC);
		query.setParameter(1,IConstants.TRUE);
		query.setParameter(2,IConstants.TRUE);
		query.setParameter(2,IConstants.FALSE);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	
	public List<Long> getAllValidProblemIdsCount()
	{
	return getHibernateTemplate().find("select count(model.userProblemId) from UserProblem model where model.visibility.type ='public' and model.isOwner = '"+IConstants.TRUE+"' and model.problem.isApproved = '"+IConstants.TRUE+"' " +
				" and model.problem.isDelete = null and model.problem.regionScopes.regionScopesId < 5");
	
}
	
	
	public List<Object[]> getProblemCompleteInfo(Long problemId)
	{
		return getHibernateTemplate().find("select model.problem.title,model.problem.description,model.problem.existingFrom,model.problem.identifiedOn,model.user.firstName,model.user.lastName,model.problem.regionScopes.regionScopesId,model.problem.impactLevelValue from UserProblem model where model.problem.problemId = ?",problemId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProblem> getAllProblemDetails(Long userProblemId)
	{
		return getHibernateTemplate().find("from UserProblem model where  model.userProblemId = ?",userProblemId);
	}
	
}