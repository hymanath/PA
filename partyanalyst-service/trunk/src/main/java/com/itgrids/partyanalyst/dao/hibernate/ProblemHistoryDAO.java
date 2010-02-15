package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.model.ProblemHistory;

public class ProblemHistoryDAO extends GenericDaoHibernate<ProblemHistory, Long> implements IProblemHistoryDAO{

	public ProblemHistoryDAO(){
		super(ProblemHistory.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> findProblemLocationsByUserId(Long registrationId, Long statusId){
		Object [] params = {registrationId, statusId};
		return getHibernateTemplate().find("from ProblemHistory model where model.problemLocation.problemAndProblemSource.user.registrationId = ? " +
				"and model.problemStatus.problemStatusId = ?",params);
	}
}
