/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;

public class AssignedProblemProgressDAO extends GenericDaoHibernate<AssignedProblemProgress, Long> implements IAssignedProblemProgressDAO {

	public AssignedProblemProgressDAO() {
		super(AssignedProblemProgress.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<AssignedProblemProgress> findByProgressLevel(String progressLevel) {
		return getHibernateTemplate().find("from AssignedProblemProgress model where model.progressLevel = ?", progressLevel);
	}

	@SuppressWarnings("unchecked")
	public List<AssignedProblemProgress> findByRegistrationIdAndStatusId(Long registrationId, Long statusId){
		Object[] params = {registrationId, statusId};
		return getHibernateTemplate().find("from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ?" +
				" and model.problemHistory.problemStatus.problemStatusId = ?",params );
	}

	@SuppressWarnings("unchecked")
	public List findProblemsForAHamletByHistoryId(Long historyId) {
		return getHibernateTemplate().find("select model.problemSourceScopeConcernedDepartment.department," +
				" model.concernedPersonName,model.problemHistory.dateUpdated" +
				" from AssignedProblemProgress model where model.problemHistory.problemHistoryId = ?", historyId);
	}
}
