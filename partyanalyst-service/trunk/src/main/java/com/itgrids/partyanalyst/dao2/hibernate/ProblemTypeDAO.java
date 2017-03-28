package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemTypeDAO;
import com.itgrids.partyanalyst.model.ProblemType;

public class ProblemTypeDAO extends GenericDaoHibernate<ProblemType, Long> implements IProblemTypeDAO{

	public ProblemTypeDAO() {
		super(ProblemType.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getProblemTypes()
	{
		return getHibernateTemplate().find("select model.problemTypeId,model.problemType from ProblemType model order by model.problemType");
	}
	
}
