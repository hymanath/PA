package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemStatusDAO;
import com.itgrids.partyanalyst.model.ProblemStatus;

public class ProblemStatusDAO extends GenericDaoHibernate<ProblemStatus, Long> implements IProblemStatusDAO{

	public ProblemStatusDAO(){
		super(ProblemStatus.class);
	}

	@SuppressWarnings("unchecked")
	public List getDefaultProblemStatus(String statusValues) {
		
		return getHibernateTemplate().find("select model.problemStatusId,model.status from ProblemStatus model where model.status in ("+statusValues+") order by model.problemStatusId");
	}

	@SuppressWarnings("unchecked")
	public List<ProblemStatus> getByStatus(String status) {
		
		return getHibernateTemplate().find("from ProblemStatus model where model.status = ? ",status);
	}
	
	
}
