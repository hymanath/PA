package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemProgressDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.ProblemProgress;

public class ProblemProgressDAO extends GenericDaoHibernate<ProblemProgress,Long> implements IProblemProgressDAO{

	public ProblemProgressDAO()
	{
		super(ProblemProgress.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProblemProgress>  getProblemPrograss(Long userProblemId)
	{
		return getHibernateTemplate().find("from ProblemProgress model where model.userProblem.userProblemId = ? ",userProblemId);
	}
	public List<ProblemProgress>  getProblemPrograssDetails(Long userProblemId)
	{
		Object[] parameters = {userProblemId,"false"};
		return getHibernateTemplate().find("from ProblemProgress model where model.userProblem.userProblemId = ? and model.isDelete =? order by model.insertedTime desc",parameters);
	}
}