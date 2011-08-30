/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.ProblemHistory;

public interface IAssignedProblemProgressDAO extends GenericDao<AssignedProblemProgress, Long> {

	public List findProblemsForAHamletByHistoryId(Long historyId);
	
	public List<AssignedProblemProgress> findByRegistrationIdAndStatusId(Long registrationId, Long statusId);	
	
	public List<AssignedProblemProgress> getLatestProblemsByRegistrationIdAndStatusId(Long registrationId, Long statusId,String status);

	public List getAssignedProblemsProgressByLocation(Long problemLocationId);

	public List<AssignedProblemProgress> getAssignedProblemProgressbyHistoryId(Long problemHistoryId);
	
	public List<AssignedProblemProgress> getProblemDifferentStagesByByProblemId(Long problemId);
	
	@SuppressWarnings("unchecked")
	public List getProblemRecentUpdatesByProblemId(Long problemId);
	
	public List<AssignedProblemProgress> getProblemAllActivitiesByProblemId(Long problemId);
	
	public List<AssignedProblemProgress> getProblemsByDepartmentScope(Long userId,Long deptScopeId);
	
	public List<Object> getAssignedCadreProblemsCountInARegion(Long userId,String locationStr);
	
	public List<Object> getAssignedCadreProblemsCountForAnUser(Long userId);
	
	public List<ProblemHistory> getAssignedCadreProblemsInARegion(Long userId,String locationStr);
	
	public List<ProblemHistory> getAssignedCadreProblemsForAnUser(Long userId);
	
	public List<Object> getDepartmentWiseProblemStatus(Long userId,Long deptOrgId);
	
	public List<ProblemHistory> getDepartmentWiseProblemsBasedOnStatus(Long userId,Long deptOrgId,String statusStr);
	
	public List<Long> getDeptWiseAssignedProblemProgressIds(Long userId,String deptLocationStr);
	
	public List<Object[]> getProblemsStatusBasedOnAssignedProblemProgressId(Long userId,List<Long> progressIdList);
	
	public List<ProblemHistory> getProblemsBasedOnAssignedProblemProgressIdAndStatus(Long userId,List<Long> progressIdList,String deptStr,String statusStr);
	
	public List<AssignedProblemProgress> getByCadreId(Long cadreId);
	
	public List<Cadre> getCadreForCadreProblemsInARegion(Long userId,String locationStr);
	
	public List<Cadre> getCadreForCadreProblemsForAnUser(Long userId);
	
	public List<Object> getProblemStatusOfACadre(Long cadreId);
	
}
