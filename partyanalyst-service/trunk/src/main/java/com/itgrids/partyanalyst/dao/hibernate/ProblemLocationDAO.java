package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

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
		return getHibernateTemplate().find("select model.problemSource.problem  from ProblemLocation model where model.hamlet.hamletId = ? and model.problemSource.problem.year = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findByHamletandYear(Long hamletId,String year){
		Object[] params ={hamletId,year};
		return getHibernateTemplate().find("select model.problemSource.problem,model.problemSource,model.problemSource.problemExternalSource  from ProblemLocation model where model.hamlet.hamletId = ? and model.problemSource.problem.year = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemLocation> findByHamletIdandYear(Long hamletId,String year){
		Object[] params ={hamletId,year};
		return getHibernateTemplate().find("from ProblemLocation model where model.hamlet.hamletId = ? and model.problemSource.problem.year = ?",params);
	}
}
