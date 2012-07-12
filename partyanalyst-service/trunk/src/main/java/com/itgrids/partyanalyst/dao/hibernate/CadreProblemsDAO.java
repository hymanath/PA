package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreProblemsDAO;
import com.itgrids.partyanalyst.model.CadreProblems;

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
	
	
}
