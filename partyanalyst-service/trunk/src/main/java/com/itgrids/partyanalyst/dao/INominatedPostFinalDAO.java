package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostApplication;
import com.itgrids.partyanalyst.model.NominatedPostFinal;

public interface INominatedPostFinalDAO extends GenericDao<NominatedPostFinal, Long>{
	public List<Object[]> getFinalShortListedApplciationStatusDtls(Long boardLevelId,Date startDate,Date endDate);
	public List<Object[]> getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type,Long searchLevelId,Long applicationStatusId);
	public Long getNominatedPostFinalDetails(Long nominatedPostId,Long nominationPostCandidateId);
	public List<Long> getAnyShortlistedDepartmentsForCandidateList(Set<Long> nominatedPostCandidateIds,Long deptId,Long boardId,Long applicationStatusId);
	public List<Object[]> getAnyAppliedDepartmentsCountForCandidateList(Set<Long> nominatedPostCandidateIds,Long deptId,Long boardId,Long applicationStatusId);
	public List<Object[]> getAllReferredMemberDetailsForPosition(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,Long statusId);
	public List<Object[]> getShortlistedDepartmentsCountForCandidateList(Set<Long> nominatedPostCandidateIds,Long deptId,Long boardId);
	public List<Object[]> getGenderWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId);
	public List<Object[]> getCasteWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId);
	public List<Object[]> getAgeGroupWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId);
	public List<Object[]> getCasteCategoryGroupWiseCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,String type,Long stateId);
	public List<Object[]> getApplicationStatusList();
	public List<Long> getNominatedPostApplicationIdsByMemberOfFinalReview(Long memberId);
	public List<Object[]> getCandidateCasteList(Long locationLevelId,Long stateId,Long positionId);
	public List<Object[]> getLocationWiseCastePositionCount(Long LocationLevelId,Long positionId,Long stateId);
	public List<Object[]> getCasteGroup(Long locationLevelId,Long stateId,Long positionId);
	public List<Object[]> getLocationWiseCasteGroupPositionCount(Long LocationLevelId,Long positionId,Long stateId);
	public List<Object[]> getCasteWisePositionsCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long casteId,Long stateId);
	public List<Object[]> getPositionCountForGender(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long districtId,String type);
	
	//public List<Object[]>  getNominatedCandidateGroupByDist(Long positionIdList, Long locationLevelIdList, Long deptIdList, Long corporationIdList, Long castGroupIdList, Long positionStatusIdList, Long stateId);
	//public List<Object[]>  getNominatedCandidateGroupByDistAndGender(Long positionIdList, Long locationLevelIdList, Long deptIdList, Long corporationIdList, Long castGroupIdList, Long positionStatusIdList, Long stateId);
	//public List<Object[]>  getNominatedCandidateGroupByDistAndAgeGroup(Long positionIdList, Long locationLevelIdList, Long deptIdList, Long corporationIdList, Long castGroupIdList, Long positionStatusIdList, Long stateId);

	public List<Object[]> getNominatedCandidateLocationDetails(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId, String locationLevelName);
	public List<Object[]>  getNominatedCandidateGroupByLocationAndGender(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId, String locationLevelName);
	public List<Object[]>  getNominatedCandidateGroupByLocationAndAgeGroup(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId, String locationLevelName);

	public List<Object[]> getNominatedCandidatePositionDetails(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long locationId, String locationLevelName);
	public List<Object[]> getNominatedCandidateGroupByPositionAndGender(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long locationId, String locationLevelName);
	public List<Object[]> getNominatedCandidateGroupByPositionAndAgeGroup(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long locationId, String locationLevelName);

	 public Object[] getShortListedPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId,Long stateId);
	 public List<Object[]> getWishListCount(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId);
	 public int updateApplicationStatusToFinalReview(Long userId,List<Long> finalIds);
	 public List<Long> getApplicationFinalModels(Long deptId,Long boardId,List<Long> positions,Long levelId,List<Long> searchLevelValues);
	 public  List<NominatedPostFinal>  getNominatedPostApplicationDetailsByApplciationId(Long nominatedPostApplicationId);
	 public List<Long> getNominatedPostFinalIds(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId,List<Long> positionsList);
	 public Integer updateGoIssuedStatusInNominatedPostFinal(List<Long> nominatedPostFinalIds,Date date);
	 public Long getTotalApplicationsByLocation(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId);
	 public List<Object[]> getShortlistedCandidatesStatus(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
				Long locationValue,String type,Long searchLevelId);
	 public List<Object[]> getShortlistedApplicationDetailsOfCandidate(Set<Long> candidateIds);
	 public List<Object[]> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId);
	 public List<Object[]> getNPCAndNpForApplication(List<Long> applicationIds);
	 public Integer updateStatusToGOPassed(List<Long> applicationIds,Date date,Long statusId);
	 public List<NominatedPostApplication> getNominatedPostApplicationsByMemberOfFinalReview(Long memberId);
	 public int updateApllicationStatusToReject(Long applicationId,Long statusId,Long userId);
	 public List<Object[]> getPositionDetaislOfEveryApplicationStatus(Long boardLevelId,List<Long> locationValues,List<Long> deptsIds,List<Long> boardIds,String statusType,String positionType);
	 public int updateApllicationStatusToReject(Long memberId,final Long userId);
	 public List<Object[]> getApplicationDataByApplctnIds(List<Long> applicationIds,Long statusId);
	 public List<Object[]> getUpdatedPositionsForCandidate(List<Long> applicationIds);
	 public Long getIsApplicationShortlistedOrNot(Long applicationId,Long candId,Long nominatePostApplicationId);
	 public List<Object[]> getLinkedPositions(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue,Long locationLevelId,Long nominatedPostCandId);
	 public List<Object[]> getPositionDetaislOfEveryApplicationnStatus(Long boardLevelId,List<Long> locationValues,List<Long> deptsIds,List<Long> boardIds,String statusType,String positionType);
	 public List<Object[]> getLocationAndBoardLevelWisePostsData(Long postLevelId,Long casteGrpId,Long casteId,Long ageRangeId,Long positionId,String gender,Long stateId,String searchType);
	 public List<Object[]> getLocationAndBoardLevelWiseCasteCatgryPostsData(Long postLevelId,Long casteGrpId,Long casteId,
			 Long ageRangeId,Long positionId,String gender,Long stateId,String searchType,List<Long> locationIds,String type,String casteType);
	 public List<Long> getNominatedPostFinalIdsByMemberOfFinalReview(Long memberId,List<Long> status);
	 public List<Long> getNominatedPostApplicationIdsByPostIds(List<Long> nominatedPostIdsLsit);
	 public int updateApplicationExpiredByPostIds(List<Long> nominatedPostIdsLsit, Long userId,Date currentDate);
}
