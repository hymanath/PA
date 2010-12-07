package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemLocation;

public class ProblemLocationDAO extends GenericDaoHibernate<ProblemLocation, Long> implements IProblemLocationDAO{

	public ProblemLocationDAO(){
		super(ProblemLocation.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemLocation> findByHamletId(Long hamletId){
		return getHibernateTemplate().find("from ProblemLocation model where model.hamlet.hamletId = ?", hamletId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Problem> findByHamletIdAndYear(Long hamletId,String year){
		Object[] params ={hamletId,year};
		return getHibernateTemplate().find("select model.problemAndProblemSource.problem  from ProblemLocation model where model.hamlet.hamletId = ? and model.problemAndProblemSource.problem.year = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findByHamletandYear(Long hamletId,String year){
		Object[] params ={hamletId,year};
		return getHibernateTemplate().find("select model.problemAndProblemSource.problem,model.problemAndProblemSource,model.problemAndProblemSource.problemExternalSource  from ProblemLocation model where model.hamlet.hamletId = ? and model.problemAndProblemSource.problem.year = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemLocation> findByHamletIdandYear(Long hamletId,String year){
		Object[] params ={hamletId,year};
		return getHibernateTemplate().find("from ProblemLocation model where model.hamlet.hamletId = ? and model.problemAndProblemSource.problem.year = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemLocation> findProblemsByUserId(Long registrationId){
		return getHibernateTemplate().find("from ProblemLocation model where model.problemAndProblemSource.user.registrationId = ?", registrationId);
	}

	@SuppressWarnings("unchecked")
	public List<ProblemLocation> findByLevel(Long levelId, Long levelValue) {
		Object[] params = {levelId,levelValue};
		return getHibernateTemplate().find("from ProblemLocation model where model.problemImpactLevel.regionScopesId =  and "+
				"model.problemImpactLevelValue = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findByLevel(Long levelId, List<Long> levelValues) {
		Query queryObject = getSession().createQuery("select model.problemLocationId from ProblemLocation model where model.problemImpactLevel.regionScopesId = ? and "+
				"model.problemImpactLevelValue in (:levelValues)");
		queryObject.setParameter(0,levelId);
		queryObject.setParameterList("levelValues", levelValues);
		return queryObject.list();
	}
	
}
