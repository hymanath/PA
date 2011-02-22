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
				" and model.problemHistory.problemStatus.problemStatusId = ? and model.problemHistory.isDelete is null",params );
	}

	@SuppressWarnings("unchecked")
	public List findProblemsForAHamletByHistoryId(Long historyId) {
		return getHibernateTemplate().find("select model.problemSourceScopeConcernedDepartment.department," +
				" model.concernedPersonName,model.problemHistory.dateUpdated,model.contactNo,model.designation" +
				" from AssignedProblemProgress model where model.problemHistory.problemHistoryId = ?", historyId);
	}

	@SuppressWarnings("unchecked")
	public List<AssignedProblemProgress> getLatestProblemsByRegistrationIdAndStatusId(
			Long registrationId, Long statusId, String status) {
		Object[] params = {registrationId, statusId,status};
		return getHibernateTemplate().find("from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ?" +
				" and  model.problemHistory.problemStatus.problemStatusId = ? and model.problemHistory.isDelete = ? ",params );
	}

	public List getAssignedProblemsProgressByLocation(Long problemLocationId) {
		return getHibernateTemplate().find("select model.problemSourceScopeConcernedDepartment.department, " +
				"model.concernedPersonName, model.contactNo, model.designation, model.performedDate " +
				"from AssignedProblemProgress model where " +
				"model.problemHistory.problemLocation.problemLocationId = ? and model.problemHistory.isDelete is null " +
				"group by model.problemHistory.problemHistoryId ",problemLocationId);
	}

	@SuppressWarnings("unchecked")
	public List<AssignedProblemProgress> getAssignedProblemProgressbyHistoryId(
			Long problemHistoryId) {
		
		return getHibernateTemplate().find("from AssignedProblemProgress model where model.problemHistory.problemHistoryId = ?",problemHistoryId);
	}

	@SuppressWarnings("unchecked")
	public List<AssignedProblemProgress> getProblemDifferentStagesByByProblemId(
			Long problemId) {
		
		return getHibernateTemplate().find("from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.problem.problemId = ?",problemId);
	}

	@SuppressWarnings("unchecked")
	public List getProblemRecentUpdatesByProblemId(Long problemId) {
		
		Object[] params = {problemId,problemId};
		return getHibernateTemplate().find("select model from AssignedProblemProgress model "+
				"where model.performedDate = (select max(model.performedDate) from AssignedProblemProgress model " +
				"where model.problemHistory.problemLocation.problemAndProblemSource.problem.problemId = ?) and "+
				"model.problemHistory.problemLocation.problemAndProblemSource.problem.problemId = ?",params);				
	}

	@SuppressWarnings("unchecked")
	public List<AssignedProblemProgress> getProblemAllActivitiesByProblemId(
			Long problemId) {
		
		return getHibernateTemplate().find("from AssignedProblemProgress model where "+
				"model.problemHistory.problemLocation.problemAndProblemSource.problem.problemId = ? "+
				"order by model.performedDate desc",problemId);	
	}

		
}
