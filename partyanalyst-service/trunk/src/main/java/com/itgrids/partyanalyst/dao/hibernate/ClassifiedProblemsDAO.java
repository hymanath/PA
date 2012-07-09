package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IClassifiedProblemsDAO;
import com.itgrids.partyanalyst.model.ClassifiedProblems;

public class ClassifiedProblemsDAO extends GenericDaoHibernate<ClassifiedProblems,Long> implements IClassifiedProblemsDAO{
	
	public ClassifiedProblemsDAO()
	{
		super(ClassifiedProblems.class);
	}
	@SuppressWarnings("unchecked")
	public List<Long> checkIfProblemAlreadyClassified(Long userProblemId){
		return getHibernateTemplate().find("select model.classifiedProblemsId from ClassifiedProblems model where " +
				"model.userProblem.userProblemId=?", userProblemId);
	}	
}
