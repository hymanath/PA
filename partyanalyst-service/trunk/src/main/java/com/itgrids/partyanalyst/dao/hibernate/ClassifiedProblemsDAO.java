package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IClassifiedProblemsDAO;
import com.itgrids.partyanalyst.model.ClassifiedProblems;

public class ClassifiedProblemsDAO extends GenericDaoHibernate<ClassifiedProblems,Long> implements IClassifiedProblemsDAO{
	
	public ClassifiedProblemsDAO()
	{
		super(ClassifiedProblems.class);
	}
}
