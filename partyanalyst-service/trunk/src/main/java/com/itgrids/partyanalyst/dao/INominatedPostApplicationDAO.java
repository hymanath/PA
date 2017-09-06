package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostApplication;

public interface INominatedPostApplicationDAO extends GenericDao<NominatedPostApplication, Long>{
	public List<Object[]> getNominatedPostsAppliedApplciationsDtals(Long levelId,Date startDate,Date endDate,Long stateId);
	public List<Object[]> getPendingApplciationStatusDtls(Long boardLevelId,Date startDate,Date endDate);
	public List<Object[]> getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type);
	public List<Object[]> getAppliationsReceievedStatus(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type,Long searchLevelId);
	public List<Object[]> getShortlistedCandidatesStatus(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type,Long searchLevelId);
	public List<Object[]> getCasteWiseApplications(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type,Long searchLevelId);
	public List<Object[]> getAgeRangeWiseApplications(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type,Long searchLevelId);
	public List<Object[]> getCandidateAppliedPostsByCadre(Long tdpCadreId,String searchType,Long nominateCandId,Long statusId);
	public List<Object[]> getNominatedPostsRunningAppliedApplicationsDtals(Long levelId,Date startDate,Date endDate,Long stateId);
	public List<Object[]> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId,Long statusId);
	public List<Object[]> getPositionDetaislOfEveryApplicationStatus(Long boardLevelId,List<Long> levelValues,List<Long> deptsIds,List<Long> boardIds,String statusType,String positionsType );
	
	public List<Object[]> getFinalReviewCandidateCountLocationWise(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId,String status);
	public List<Object[]> getLocationWiseCastePositionCount(Long LocationLevelId);
	public List<Object[]> getLocationWiseCasteGroupPositionCount(Long LocationLevelId);
	public List<Object[]> getApplicationStatusCntByPositionId(Long positionId);
	public List<Object[]> getCandidateCasteList(Long locationLevelId);
	public List<Object[]> getNominatedPostsAppliedApplcitionsDetalsNew(Long levelId,Date startDate,Date endDate,Long stateId,String type);
	public List<Object[]> getApplicationDetailsCntPositionAndLocationWise(Long positionId,Long boardLevelId,Long stateId);
	public List<Object[]> getTotalApplicationCountsByBoard(Long boardLevelId,Long searchLevelId,Long searchLevelValue,Long statusId);
	public List<Object[]> getTotalAppliedCorpIdsAndBoardsIdsAndPositionsIds(Long boardLevelId,Long searchlevelId,Long searchLevelValue);
	public int updateApplicationStatusToFinal(List<Long> applicationIds,Long userId);
	public List<Object[]> getAnyDeptApplicationOverviewCountLocationWise(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
	List<Long> locationValue,Long searchLevelId,String status,String statusType);
	public List<Object[]> getRecord(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId,List<Long> positionsList);
	public Integer updateApplicationStatusForGO(List<Long> nominatedPostApplicationIds,Date date);
	public Object[] getBoardLevel(Long applicationId);
	public List<Object[]> getApplicationDetailsOfCandidate(Set<Long> candidateIds);
	public List<Object[]> getFinalReviewCandidateCountForLocation(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId, Long positionId, String status);
	public List<Object[]> getAppliedPositionsForCandidate(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue,Long locationLevelId,Long nominatedPostCandId);
	public List<Long> getApplicationIds(Long deptId,Long boardId,List<Long> positions,Long levelId,List<Long> searchLevelValues,Long userId,List<Long> statusIds);
	public List<Object[]> getTotalAvaiableApplicationsDetails(Long boardLevelId,Long stateId,Long applicationStatusId);
	public List<Object[]> getFinalReviewCandidateCountForLocationFilter(Long LocationLevelId, List<Long> lctnLevelValueList, List<Long> deptList, List<Long> boardList, List<Long> positionList, Date lowerRange, Date expDate, String status);
	public List<Object[]> getNominatedPostsAppliedApplciationsDetalsNew(Long levelId,Date startDate,Date endDate,Long stateId,String type);
	public List<Object[]> getPositionWiseTotalApplicationsReceived(Long boardLevelId,List<Long> locationValues,List<Long> deptsIds,List<Long> boardIds,String statusType,String positionType);
	public List<Object[]> getPositionWiseTotalApplicationnsReceived(Long boardLevelId,List<Long> locationValues,List<Long> deptsIds,List<Long> boardIds,String statusType,String positionType);
	public int updateApllicationStatusToReject(Long nominatedPostMemberId,Long statusId,Long userId);
	public int updateApllicationStatusToReject(Long memberId,Long userId);
	public List<NominatedPostApplication> getApplicationIdsByMemberId(Long memberId);
	public List<Object[]> getApplicationIdsByCAndidateId(Long candidateId);
	public List<Object[]> getAnyPositionDetailsByLevelId(Long boardLevelId);
	public int updateApplicationExpiredByApplns(List<Long> nominatedPostApplciationIdsLsit, Long userId,Date currentDate);
	public Long nominatedPostAppPostionDetails(Long boardLevelId,Long locationValue,Long deptId,Long boardId,Long positionId,Long postTypeId,Long nominationCandidateId,Long statusId );
	public List<Object[]> getNominatedPostApplicationStatusWiseCount(Long locationTypeId,List<Long> locationValue, Date startDate, Date endDate);
	public List<Object[]> getTotalReceivedApplicationsForLocation(List<Long> boardLevelId,Long levelId,List<Long> levelValues);
}
