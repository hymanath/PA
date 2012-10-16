package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreProblemsDAO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreProblems;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemHistory;

import com.itgrids.partyanalyst.utils.IConstants;

public class CadreProblemsDAO extends GenericDaoHibernate<CadreProblems,Long> implements ICadreProblemsDAO{

	public CadreProblemsDAO()
	{
		super(CadreProblems.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCadreDetailsAndMobileNoByProblemId(Long problemId)
	{
		return getHibernateTemplate().find("select model.cadre.firstName,model.cadre.lastName,model.cadre.mobile from CadreProblems model where model.problem.problemId = ?",problemId);
	}
	public List<Object> getCadreProblemsCountForAnUser(Long userId)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find("select model.problem.problemId from CadreProblems model,UserProblem model2 where model2.user.userId=? and model.problem.problemId = model2.problem.problemId and (model.problem.isDelete is null or model.problem.isDelete='"+IConstants.FALSE+"') and  model.problem.isApproved = 'true'",params);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getCadreProblemsCountInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select model.problem.problemId from CadreProblems model,UserProblem model2 where model2.user.userId = ? "+locationStr+" and model.problem.problemId = model2.problem.problemId and (model.problem.isDelete is null or model.problem.isDelete='"+IConstants.FALSE+"') and model.problem.isApproved = 'true'",userId);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Problem> getCadreProblemsInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find("select model.problem from CadreProblems model,UserProblem model2 where model2.user.userId = ? "+locationStr+" and model.problem.problemId = model2.problem.problemId and (model.problem.isDelete is null or model.problem.isDelete='"+IConstants.FALSE+"') and model.problem.isApproved = 'true'",params);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Problem> getCadreProblemsForAnUser(Long userId)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select model.problem from CadreProblems model,UserProblem model2 where model2.user.userId=? and model.problem.problemId = model2.problem.problemId and (model.problem.isDelete is null or model.problem.isDelete='"+IConstants.FALSE+"') and  model.problem.isApproved = 'true'",params);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Cadre> getCadreForCadreProblemsInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find("select distinct model.cadre from CadreProblems model,UserProblem model2 where model2.user.userId = ? "+locationStr+" and model.problem.problemId = model2.problem.problemId and (model.problem.isDelete is null or model.problem.isDelete='"+IConstants.FALSE+"') and model.problem.isApproved = 'true'",params);
	}

	
	@SuppressWarnings("unchecked")
	public List<Cadre> getCadreForCadreProblemsForAnUser(Long userId)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select distinct model.cadre from CadreProblems model,UserProblem model2 where model2.user.userId=? and model.problem.problemId = model2.problem.problemId and (model.problem.isDelete is null or model.problem.isDelete='"+IConstants.FALSE+"') and  model.problem.isApproved = 'true'",params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getProblemStatusOfACadre(Long cadreId)
	{
		Object[] params = {cadreId};
		return getHibernateTemplate().find(" select model.problem.problemStatus.status from CadreProblems model where " +
				" model.cadre.cadreId = ? and (model.problem.isDelete is null or model.problem.isDelete='"+IConstants.FALSE+"') and  model.problem.isApproved = 'true'",params);
	}
	
	
	
	
		
	public List<Object[]> getProblemPostedCadreName(Long problemId){
		return getHibernateTemplate().find("select model.cadre.firstName,model.cadre.lastName from CadreProblems model where model.problem.problemId = ?",problemId);		 
	}
	@SuppressWarnings("unchecked")
	public List<CadreProblems> getCadreProblemDetailsByProblemId(Long problemId)
	{
		return getHibernateTemplate().find("from CadreProblems model where model.problem.problemId = ?",problemId);
	}
	
	@SuppressWarnings("unchecked")
	public Integer  deleteCadreProblem(Long problemId)
	{
		Query queryObj = getSession().createQuery("delete from CadreProblems model where model.problem.problemId = :problemId");
		queryObj.setParameter("problemId", problemId);
		
		return queryObj.executeUpdate();
	}
	
	public List<Object[]> getProblemPostedCadreNameAndId(Long problemId){
		return getHibernateTemplate().find("select model.cadre.cadreId,model.cadre.firstName,model.cadre.lastName from CadreProblems model where model.problem.problemId = ?",problemId);		 
	}

}
