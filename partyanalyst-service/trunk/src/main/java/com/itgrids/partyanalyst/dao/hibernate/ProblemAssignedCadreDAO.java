package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemAssignedCadreDAO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.ProblemAssignedCadre;
import com.itgrids.partyanalyst.model.UserProblem;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemAssignedCadreDAO extends GenericDaoHibernate<ProblemAssignedCadre,Long> implements IProblemAssignedCadreDAO
{
	public ProblemAssignedCadreDAO()
	{
		super(ProblemAssignedCadre.class);
	}
	
	/*@SuppressWarnings("unchecked")
	public List<ProblemHistory> getAssignedCadreProblemsInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select distinct(model.problemHistory) from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.userId = ? "+
				" "+locationStr+" and model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.cadre is not null ",params);
	}*/
	
	
	@SuppressWarnings("unchecked")
	public List<UserProblem> getAssignedCadreProblemsInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select distinct(model.userProblem) from ProblemAssignedCadre model where model.userProblem.user.userId = ? "+
				" "+locationStr+" and (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete = '"+IConstants.FALSE+"') and model.userProblem.problem.isApproved = '"+IConstants.TRUE+"' and model.cadre is not null ",params);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getAssignedCadreProblemsCountForAnUser(Long userId)
	{
		//Object[] params = {userId};
		return getHibernateTemplate().find("select distinct model.userProblem.userProblemId from ProblemAssignedCadre model where model.userProblem.user.userId = ? " +
				"and (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete = '"+IConstants.FALSE+"') and model.userProblem.problem.isApproved = '"+IConstants.TRUE+"' and model.cadre is not null ",userId);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getAssignedCadreProblemsCountInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select distinct model.userProblem.userProblemId from ProblemAssignedCadre model where model.userProblem.user.userId = ? "+
				" "+locationStr+" and (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete = '"+IConstants.FALSE+"') and model.userProblem.problem.isApproved = '"+IConstants.TRUE+"' and model.cadre is not null ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProblem> getAssignedCadreProblemsForAnUser(Long userId)
	{
		//Object[] params = {userId};
		return getHibernateTemplate().find("select distinct(model.userProblem) from ProblemAssignedCadre model where model.userProblem.user.userId = ? " +
				"and (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete = '"+IConstants.FALSE+"') and model.userProblem.problem.isApproved = '"+IConstants.TRUE+"' and model.cadre is not null ",userId);
	}

	
	@SuppressWarnings("unchecked")
	public List<Cadre> getCadreForCadreProblemsInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find("select distinct model.cadre from ProblemAssignedCadre model where " +
				" model.userProblem.user.userId = ? "+locationStr+" and (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete = '"+IConstants.FALSE+"') and model.userProblem.problem.isApproved = '"+IConstants.TRUE+"'",params);
	}
	@SuppressWarnings("unchecked")
	public List<Cadre> getCadreForCadreProblemsForAnUser(Long userId)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find("select distinct model.cadre from ProblemAssignedCadre model where " +
				" model.userProblem.user.userId = ? and (model.userProblem.problem.isDelete is null or model.userProblem.problem.isDelete = '"+IConstants.FALSE+"') and model.userProblem.problem.isApproved = '"+IConstants.TRUE+"'",params);
	}
			
	public List<ProblemAssignedCadre> getProblemAssignedCadreByUserProblemId(Long userProblemId){
		return getHibernateTemplate().find("from ProblemAssignedCadre model where model.userProblem.userProblemId =?  order by model.updatedTime desc",userProblemId);
	}
	
	public List<Object[]> getProblemIds(Long userId,boolean userProbOnly){
		StringBuilder queryObj = new StringBuilder();
		queryObj.append("select model1.userProblem.problem.problemId,model1.cadre.firstName,model1.cadre.lastName,model1.cadre.cadreId from ProblemAssignedCadre model1 where (model1.userProblem.userProblemId, model1.updatedTime) in (select model.userProblem.userProblemId,max(model.updatedTime) from ProblemAssignedCadre model group by model.userProblem.userProblemId) ");
		queryObj.append("  and model1.status in ('ASSIGNED','MODIFIED' ) ");
		
		if(userProbOnly && userId != null){
			queryObj.append("  and (model1.userProblem.user.userId = :userId ");
		}
		else{
		queryObj.append("  and (model1.userProblem.visibility.visibilityId = :visibilityId ");
		if(userId != null)
			queryObj.append("  or model1.userProblem.user.userId = :userId ");
		
		}
		
		queryObj.append(" ) and model1.userProblem.problem.isApproved = :isApproved and  model1.userProblem.problem.isDelete = :isDelete ");
		 
		Query query = getSession().createQuery(queryObj.toString());
		
		if(!(userProbOnly && userId != null)){
			query.setParameter("visibilityId",1l);
			}
		
		if(userId != null)
			query.setParameter("userId",userId);
		
		query.setParameter("isApproved",IConstants.TRUE);
		query.setParameter("isDelete",IConstants.FALSE);
		
		  return query.list();
	}
}
