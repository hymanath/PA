package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreProblemsDAO;
import com.itgrids.partyanalyst.model.CadreProblems;

public class CadreProblemsDAO extends GenericDaoHibernate<CadreProblems,Long> implements ICadreProblemsDAO{

	public CadreProblemsDAO()
	{
		super(CadreProblems.class);
	}

}
