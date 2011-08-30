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
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.ProblemHistory;

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
	
	@SuppressWarnings("unchecked")
	public List<AssignedProblemProgress> getProblemsByDepartmentScope(Long userId,Long deptScopeId)
	{
		Object[] params = {userId,deptScopeId};
		
		return getHibernateTemplate().find("from AssignedProblemProgress model where "+
				" model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ? and " +
				" model.problemSourceScopeConcernedDepartment.problemSourceScope.problemSourceScopeId = ? and " +
				" model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAssignedCadreProblemsCountInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select distinct model.problemHistory.problemHistoryId from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ? " +
				" "+locationStr+" and model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' " +
				" and model.cadre is not null ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAssignedCadreProblemsCountForAnUser(Long userId)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select distinct model.problemHistory.problemHistoryId from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ?  and " +
				" model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.cadre is not null ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getAssignedCadreProblemsInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select distinct(model.problemHistory) from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ? "+
				" "+locationStr+" and model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.cadre is not null ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getAssignedCadreProblemsForAnUser(Long userId)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select distinct(model.problemHistory) from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ?  and " +
				" model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.cadre is not null ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getDepartmentWiseProblemStatus(Long userId,Long deptOrgId)
	{
		Object[] params = {userId,deptOrgId};
		return getHibernateTemplate().find(" select model.problemHistory.problemStatus.status from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ?  and " +
				" model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.departmentOrganisation.departmentOrganisationId = ? group by model.problemHistory.problemHistoryId ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getDepartmentWiseProblemsBasedOnStatus(Long userId,Long deptOrgId,String statusStr)
	{
		Object[] params = {userId,deptOrgId};
		return getHibernateTemplate().find(" select model.problemHistory from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ?  and " +
				" model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.departmentOrganisation.departmentOrganisationId = ? "+ statusStr +" group by model.problemHistory.problemHistoryId ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getDeptWiseAssignedProblemProgressIds(Long userId,String deptLocationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select max(model2.assignedProblemProgressId) from AssignedProblemProgress model2 where model2.problemHistory.problemHistoryId in (select model.problemHistory.problemHistoryId from AssignedProblemProgress model " +
				" where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ?  and " +
				" model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' "+deptLocationStr+" group by model.problemHistory.problemHistoryId) group by model2.problemHistory.problemHistoryId ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProblemsStatusBasedOnAssignedProblemProgressId(Long userId,List<Long> progressIdList)
	{
		Query queryObject = getSession().createQuery(" select model.problemHistory.problemStatus.status,model.departmentOrganisation.departmentOrganisationId,model.departmentOrganisation.organisationName from AssignedProblemProgress model where " +
				" model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ?  and model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.assignedProblemProgressId in(:progressIdList) ");
		
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("progressIdList", progressIdList);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getProblemsBasedOnAssignedProblemProgressIdAndStatus(Long userId,List<Long> progressIdList,String deptStr,String statusStr)
	{
		Query queryObject = getSession().createQuery(" select model.problemHistory from AssignedProblemProgress model where model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ? and " +
				" model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' and model.assignedProblemProgressId in(:progressIdList) "+deptStr+" "+ statusStr);
		
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("progressIdList", progressIdList);
		return queryObject.list();
	}
	
		
	@SuppressWarnings("unchecked")
	public List<AssignedProblemProgress> getByCadreId(Long cadreId)
	{
		Object[] params = {cadreId};
		return getHibernateTemplate().find(" from AssignedProblemProgress model where model.cadre.cadreId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> getCadreForCadreProblemsInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find("select distinct model.cadre from AssignedProblemProgress model where " +
				" model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ? "+locationStr+" and model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cadre> getCadreForCadreProblemsForAnUser(Long userId)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select distinct model.cadre from AssignedProblemProgress model where " +
				" model.problemHistory.problemLocation.problemAndProblemSource.user.registrationId = ? and  model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getProblemStatusOfACadre(Long cadreId)
	{
		Object[] params = {cadreId};
		return getHibernateTemplate().find(" select model.problemHistory.problemStatus.status from AssignedProblemProgress model where " +
				" model.cadre.cadreId = ? and model.problemHistory.isDelete is null and model.problemHistory.isApproved = 'true' group by model.problemHistory.problemHistoryId ",params);
	}
			
}
