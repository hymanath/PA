/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ProblemsOfUserVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IProblemManagementService {

	public ProblemManagementDataVO getProblemsForAHamlet(Long hamletId,String year);
	public ProblemBeanVO saveNewProblemData(ProblemBeanVO problemBeanVOToSave);
	public ProblemsOfUserVO getNewProblemsForUser(Long registrationId, Long statusId);
	public List<ProblemBeanVO> getClassifiedProblemsOfUser(Long registrationId, Long statusId);
	public List<ProblemBeanVO> updateAndGetClassifiedProblemDataIntoDB(List<ProblemBeanVO> problemsToUpdate);
	public List<ProblemBeanVO> updateAndGetAssignedProblems(List<ProblemBeanVO> problemsToAssign);
	public List<ProblemBeanVO> updateAndGetProblemsUnderProgress(List<ProblemBeanVO> problemsInProgress);
	public List<SelectOptionVO> getDepartmentsForProblemScope(String problemScope);
	public List<ProblemBeanVO> getAssignedProblems(Long registrationId, Long statusId);
	public List<ProblemBeanVO> getProblemsUnderProgress(Long registrationId, Long statusId);
	public List<ProblemBeanVO> getPendingProblemsForAnUser(Long registrationId, Long statusId);
	public List<ProblemBeanVO> updateAndGetProblemsUnderFixed(List<ProblemBeanVO> problemsFixed);
	public List<ProblemBeanVO> getFixedProblemsForUser(Long registrationId, Long statusId);
	public List<ProblemBeanVO> updateAndGetProblemsUnderPending(List<ProblemBeanVO> pendingProblems);
	public List<SelectOptionVO> getAllProblemImpactLevel();
	
	//Problems Push Methods
	public List<ProblemBeanVO> getProblemDetailsBasedOnProblemStatusForAUser(Long userId,Long statusId,String isPushed,String isDeleted);
	public String getLocationDetails(Long problemImpactLevelId,Long problemImpactLevelValue);
	public ProblemBeanVO getProblemCompleteInfo(Long problemHistoryId);
	public Date getCurrentDateAndTime();
		
}
