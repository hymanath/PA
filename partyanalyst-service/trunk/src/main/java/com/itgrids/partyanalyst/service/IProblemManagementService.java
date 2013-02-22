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

import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemCompleteDetailsVO;
import com.itgrids.partyanalyst.dto.ProblemManagementChartVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ProblemSearchVO;
import com.itgrids.partyanalyst.dto.ProblemStatusDataVO;
import com.itgrids.partyanalyst.dto.ProblemsOfUserVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Comment;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemComments;
import com.itgrids.partyanalyst.model.ProblemCompleteLocation;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.model.ProblemProgress;
import com.itgrids.partyanalyst.model.UserProblem;

public interface IProblemManagementService {

	public ProblemManagementDataVO getProblemsForAHamlet(Long hamletId,String year);
	public ProblemBeanVO saveNewProblemData(ProblemBeanVO problemBeanVOToSave);
	public ProblemsOfUserVO getNewProblemsForUser(Long userId, Long statusId);
	public List<ProblemBeanVO> getClassifiedProblemsOfUser(Long userId, Long statusId);
	public List<ProblemBeanVO> updateAndGetClassifiedProblemDataIntoDB(List<ProblemBeanVO> problemsToUpdate);
	public List<ProblemBeanVO> updateAndGetAssignedProblems(List<ProblemBeanVO> problemsToAssign);
	public List<ProblemBeanVO> updateAndGetProblemsUnderProgress(List<ProblemBeanVO> problemsInProgress);
	public List<SelectOptionVO> getDepartmentsForProblemScope(String problemScope);
	public List<ProblemBeanVO> getAssignedProblems(Long userId, Long statusId);
	public List<ProblemBeanVO> getProblemsUnderProgress(Long userId, Long statusId);
	public List<ProblemBeanVO> getPendingProblemsForAnUser(Long userId, Long statusId);
	public List<ProblemBeanVO> updateAndGetProblemsUnderFixed(List<ProblemBeanVO> problemsFixed);
	public List<ProblemBeanVO> getFixedProblemsForUser(Long userId, Long statusId);
	public List<ProblemBeanVO> updateAndGetProblemsUnderPending(List<ProblemBeanVO> pendingProblems);
	public List<SelectOptionVO> getAllProblemImpactLevel();
	
	//Problems Push Methods
	public List<ProblemBeanVO> getProblemDetailsBasedOnProblemStatusForAUser(Long userId,Long statusId,String isPushed,String isDeleted);
	public String getLocationDetails(Long problemImpactLevelId,Long problemImpactLevelValue);
	public ProblemBeanVO getProblemCompleteInfo(Long problemHistoryId);
	public Date getCurrentDateAndTime();
	public List<SelectOptionVO> getAllDepartmentScopes(Long stateId);
	
	public ProblemsOfUserVO getProblemsForAUserBasedOnStatusAndCount(Long userId,Long statusId,Integer startIndex,Integer maxIndex);
	
	public ProblemManagementChartVO getProblemsDataForAUserToBuildChart(Long userId,Long statusId,Integer startIndex,Integer maxIndex,String type);
	
	public ProblemManagementChartVO getProblemsDataForAUserToBuildChart(Long userId,Integer startIndex,Integer maxIndex,String type);
	
	public ProblemManagementChartVO getOverallProblemsCountInDifferentLifeCycleStagesPostedByUser(Long userId);
	
	public ProblemsOfUserVO getUserProblemsInDifferentStagesByDate(Long userId,Integer startIndex,Integer maxResults);
	
	public ProblemsOfUserVO getUserProblemsInDifferentStagesByFilters(Long userId,Long statusId,Date startDate,Date endDate,Integer startIndex,Integer maxResults);
	
	public ProblemCompleteDetailsVO getProblemCompleteInformationByProblemHistory(Long userId,Long problemHistoryId);
	
	public List<SelectOptionVO> getDepartmentScopesForAnUser(Long userId);
	
	public List<SelectOptionVO> getProblemsDefaultClassifications();
	
	public List<SelectOptionVO> getDepartmentCategorysForAProblemScope(Long scopeId);
	
	public List<SelectOptionVO> getDepartmentOrganisationsForADeptOfScope(Long deptId,Long scopeId);
	
	public ResultStatus changePostedProblemStatusForAnUser(Long problemHistoryId,Long classificationId,Long scopeId,Long departmentId,Long cadreId,Long problemDeptLocId,String comments,String statusToChange);
	
	public ProblemStatusDataVO getProblemRecentDetailsByProblemId(Long problemId,Long userId);
	
	public List<ProblemStatusDataVO> getAllProblemRecentActivityDetails(Long problemHistoryId,Long userId);
	
	public ResultStatus updateProblemClassification(Long problemHistoryId,Long classificationId,String status);	
	
	public ResultStatus updateProblemStatus(Long problemHistoryId,Long statusId,String status);
	
	//public ResultStatus updateAssignedCadre(Long problemHistoryId,Long cadreId,String status);
	
	public ResultStatus updateProblemComments(Long problemHistoryId,String comments,String status);
	
	public ResultStatus updateProblemDepartment(Long problemHistoryId,Long departmentId,Long scopeId,Long regionId,String status,Long userId);
	
	public ResultStatus updateProblemClassificationData(Long problemHistoryId,String classification,String type);
	
	public ResultStatus updateProblemStatusData(Long problemHistoryId,String status);
	
	public List<SelectOptionVO> getDepartmentsForADepartmentResolvingAreaScope(Long scopeId);
	
	public ProblemBeanVO getProblemCompleteInfoForAUserBasedOnHistoryId(Long problemHistoryId);
	
	public List<SelectOptionVO> getCadreProblemDetailsForSms(Long userId,Long cadreId,Long pHistoryId);
	
	public ResultStatus sendSMS(Long userId,String message,String moduleName,String[] phoneNumbers);
	
	public ResultStatus sendSuccessMsgToMobile(Long problemHistoryId);
	
	public List<FileVO> getAllProblemRelatedImages(Long problemId,Long userId);
	
	public List<File> uploadFiles(ProblemBeanVO problemBeanVO);
	
	//public void saveProblemRelatedFiles(ProblemBeanVO problemBeanVO);
	
	public List<FileVO> getImageDetails();
	
	public void acceptSelectedImagesByAdmin(final Integer[] problemFileIds);
	
	public void deleteSelectedImagesByAdmin(final Integer[] problemFileIds);
	
	//public List<FileVO> getAllApprovalProblemImagesBetweenEventDates(String fromDate,String toDate,String status,String type);
	public List<FileVO> getProblemFilesBetweanDates(String fromDatestr, String toDatestr,String choice);
	
	//public List<FileVO> getAllApprovalProblemImagesForParticularDate(String particularDate,String status,String type);
	public List<FileVO> getProblemFilesForParticularDate(String particularDateStr,String choice);
	public ResultStatus sendSMSFromAdmin(String message,String[] phoneNumbers);
	
	//public ResultStatus sendEmailToFreeUserAfterProblemAdded(Long problemHistoryId);
	
	public List<ProblemBeanVO> getProblemDetailsForHomePage(int startIndex,int maxIndex);
	
	public Long getProblemsCount();
	
	public ProblemBeanVO getProblemCompleteInfoOfAFreeUserProblem(Long problemHistoryId, Long userId);
	
	public ProblemBeanVO getProblemDetailsByProblemReferenceId(String problemReferenceId , Long userId);
	
	public List<SelectOptionVO> getStates();
	
	public List<SelectOptionVO> getDistrictsByAStateID(Long stateId);
	
	public List<SelectOptionVO> getProblemPostedUserDetails();
	
	public List<SelectOptionVO> getProblemTypes();
	
	public List<ProblemBeanVO> getProblemDetailsForFreeUser(ProblemSearchVO problemSearchVO,int startIndex,int maxIndex);
	
	public void saveProblemRelatedFiles(ProblemBeanVO problemBeanVO,Problem problem,UserProblem userProblem);
	
	public ProblemCompleteLocation saveProblemCompleteLocation(ProblemBeanVO problemBeanVO);
	
	public ProblemExternalSource saveProblemExternalSource(ProblemBeanVO problemBeanVO);
	
	public String getProblemReferenceNo();
	
	public void saveCadreProblemDetails(ProblemBeanVO problemBeanVO, Problem problem);
	
	public UserProblem saveUserProblemDetails(ProblemBeanVO problBeanVO, Problem problem);
	public ResultStatus updateClassificationOfProblem(Long problemId, Long userId, String classification, String status);
	public ResultStatus updateStatusOfProblem(Long problemId, Long userId, String probStatus);
	public ResultStatus setProblemLikeOrDislike(Long problemId, Long userId, String userAction);	
	public ResultStatus sendEmailToFreeUserAfterProblemAdded(ProblemBeanVO problemBeanVO);
	public ResultStatus deleteProblemDetails(Long problemId);
	
	public ResultStatus updateAssignedCadre(final Long problemId,
			final Long cadreId, final String pbStatus,final Long userId) ;
	
	public ResultStatus addProblemRelatedFiles(ProblemBeanVO problemBeanVO);
	
public String getProblemLocationString(ProblemCompleteLocation problemCompleteLocation);
	
	public ProblemStatusDataVO getProblemActivityDetailsSetToVO(ProblemProgress problemProgress) throws Exception; 
	
	public ResultStatus freeUserProblemAssignedToCustomer(Long userId, String visibility, Long problemId);
	
	public ResultStatus saveRatingOfAProblem(Long userId, Long problemId, String rating);
	
	public CompleteProblemDetailsVO getAverageRatingOfAProblem(Long problemId);
	
	public List<CompleteProblemDetailsVO> getRatingWiseCountOfAProblem(Long problemId);
	
	public ResultStatus saveProblemRelatedComments(final ProblemBeanVO problemBeanVO);
	
	public Comment saveProblemCommentDetails(ProblemBeanVO problemBeanVO);
	
	public ProblemComments saveCommentDataInProblemComments(ProblemBeanVO problemBeanVO, Comment comment);
	
	public ProblemProgress saveCommentDataInProblemProgress(ProblemBeanVO problemBeanVO, Comment comment);
	
	public ResultStatus changeActivityState(Long prblmPrgrssId,String task);
	
	public ResultStatus changeProblemToPublic(Long problemId,String task);
	
	public Integer getUserRatingOfAProblem(Long problemId, Long userId);
	
	public String getIsUserRatedAProblem(Long userId, Long problemId);
	
	public ResultStatus saveAbusedCommentsToProblem(Long commentId, Long userId);
	
	public List<CompleteProblemDetailsVO> getProblemDetailsForUpdate(Long problemId);
	
	public ResultStatus updateProblemDetails(Long problemId,List<CompleteProblemDetailsVO> completeProblemDetailsVO);
	
	public ProblemBeanVO getProblemCompleteInfoForAUserBasedOnProblemId(Long problemId);
	
	public List<ProblemBeanVO> getProblemDetailsForProfilePage(int startIndex,int maxIndex);
	
	public List<ProblemBeanVO> getProblemDetailsByProfileId(Long profileId,int startIndex,int maxIndex);
	
	public List<ProblemBeanVO> getProblemDetailsForVoterPage(Long userId,Long locationId,Long locationValue);
	
	public List<ProblemBeanVO> getProblemDetailsInfoVoterPage(Long userId,Long locationId,Long locationValue,String status,Long informationsrcId,Integer startIndex,Integer maxIndex);
	
	public ProblemBeanVO saveProblemDataForNews(ProblemBeanVO problemBeanVO);
	
	public Long getRegionScopesIdByScope(String scope);
	
	public ResultStatus deleteProblemFile(Long problemFileId);
	
}
