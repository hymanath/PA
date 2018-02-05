package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPost;

public interface INominatedPostDAO extends GenericDao<NominatedPost, Long>{
	public List<Object[]> getTotalAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,Long stateId,String statusType);
	public List<Object[]> getAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,List<Long> statusList,Long stateId);
	public List<Object[]> getNominatedPostsByBoardsAndDepts(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType);
	public List<Object[]> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType,String positionType);
	public List<NominatedPost> getNominatedPostDetailsBySearchCriteria(Long departmentId,Long boardId,List<Long> positionIds,Long boardLevelId,List<Long> searchLevelValue);
	public List<Object[]> getNominatdPostStatusCntByPosition(Long positionId);
	
	public List<Object[]> getBoardLevelWiseDepartments(Long postType,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId,Long positionId);
	public List<Object[]> getLevelWiseDepartmentsBoard(Long departmentId,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId);
	public List<Object[]> getLevelWiseDepartmentsBoardPosition(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId);
	public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue,String statusType, Long searchlevelId,Long searchLevelValue);
	public List<Object[]> getNominatedPostsByBoardsAndDeptsForOpen(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType);
	public List<NominatedPost> getNominatedPostDetailsByNominatedPostMember(Long nominatedPostMemberId);
	public List<Object[]> getOpenedPositionsCountByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status);
	public List<Object[]> getOpenedPositionsCountForBoardsByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status);
	public List<Object[]> getTotalPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId,Long stateId);
	public Long getNoCandiateCntPositionAndLocationWise(Long positionId,Long boardLevelId);
	public List<Object[]> getTotalCorpIdsAndBoardsIdsAndPositionsIds(Long boardLevelId,Long searchlevelId,Long searchLevelValue,Long postStatusId);
	public List<Object[]> getTotalCorpAndBoardsAndPositions(Long boardLevelId, Date startDate,Date endDate,Long stateId);
	public List<Object[]> getBoardLevelsForOpenedPositions();
	public List<Object[]> getStatesForOpenedPositions(Long boardLevelId);
	public List<Object[]> getOpenPositionDistrictsForState(Long stateId,Long boardLevelId);
	public List<Object[]> getOpenPositionConstituenciesForDistrict(Long districtId,Long boardLevelId);
	public List<Object[]> getMandalMuncilIdsForConstituency(Long constituencyId,Long boardLevelId);
	public List<Object[]> getPanchayWardIdsForMandal(Long id,String type,Long constituencyId,Long boardLevelId);
	public List<Long> getTotalDeptsCount(Long levelId);
	public List<Long> getTotalApplicationsDeptsCount(Long levelId);
	public List<Object[]> getTotalApplicationsCorpsIdsCount(Long levelId);
	public List<Object[]> getTotalCorpsIdsCount(Long levelId);
	public List<Object[]> getPositionByLevelId(Long boardLevelId);
	
	public Long getAllPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId,Long stateId);
	public List<Object[]> getPositionsForABoard(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId);
	public List<Long> getNominatedPostIds(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId,List<Long> positionsList);
	public Integer updateGoIssuedStatusInNominatedPost(List<Long> nominatedPostIds,Date date);
	public List<Long> checkPositionAvailableOrNot(Long departmentId,Long boardId,Long positionId,Long boardLevlId,Long searchLevelValue,Long searchLevelId);
	public List<Object[]> getApllicationDepmtBoards(Long departmentId,Long boardLevelId,Long levelValue,Long positionId);
	public Integer updateNominatedPost(Long nominatedPostId,Long nominatedPostCandidateId,Date date,Long userId);
	public List<NominatedPost> getNominatedPostByMemberOfFinalReview(Long memberId);
	public Integer updateNominatedPostsForOpenState(Set<Long> postIds,Long userId,Date date);
	public Long getOpenedPositions(Long memberId);
	
	 public List<Object[]> getStatusWiseNominatedProfileDetils(Long stateId,Long casteStateId,Long positionId,Long boardLevelId,Long casteCategryId,
                                                               Long ageRangeTypeId,Long deptmentId,Long corptionId,
                                                               String genderType,List<Long> postStatusIds,Long locationId,String type);
	 public List<Object[]> getNominatedPostDetails(Long locationLevelId,List<Long> locationValues,Long departmentId,Long boardId,Long positionId);
	 public List<Object[]> getNominatedOpenPostCntBasedOnDeptBoardAndPositionWise(Long LocationLevelId,List<Long> locationValues,Long departmentId,Long boardId);
	 public List<Object[]> getLevelWiseDepartmentsBoardPosition1(List<Long> departmentId,List<Long> boardId,Long boardLevelId,List<Long> searchLevelValue);
	 public List<Long> getTotalApplicationsDeptsCountforAnyBoards(Long levelId);
	 public List<Object[]> getTotalApplicationsCorpsIdsForAnyPostsCount(Long levelId);
	 public int updatePoststoOpenByPostIds(List<Long> nominatedPostIdsLsist, Date currentDate,Long userId);                                                          
	 public List<Long> getNominatedPostIdsForBoardLevelId(Long boardLevelId,Long levelValue,Long departmentId,Long boardId,Long positionId);
	 public List<Long> getNominatedPostIds(Long nominateCandId);
	 public Long getOfNominatedPostCondidates(Long nominateCandId,Long nominatedPostMemberId);
	 public List<Object[]> getTotalPostCandidates(Long departmentId,Long boardId,Long positionId);
	 public List<Object[]> getOpenPostCandidates(Long departmentId,Long boardId,Long positionId);
	 public List<Object[]> getNominatedPostStatusWiseCount(Long locationTypeId,List<Long> locationValuesList, Date fromDateStr,Date toDateStr, String year);
	 public List<Object[]> getPositionWiseMemberCount(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year);
	 public List<Long> getNominatedPostIdByMemberId(Long nominatedPostMemberId);
	 public List<Object[]> getNominatedPostStatusByPositionId(List<Long> positionIds);
	 public List<Long> getMemberIds(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId);
	 public List<Object[]> getMemberStatusDetails(List<Long> nominatedPostCandidateIds);
	 public List<Object[]> getAllNominatedStatusList();
	 public List<Object[]> getLocationWiseNominatedPostAnalysisDetails(List<Long> locationValues, Long boardLevelId,Long searchLevelId,String type,List<Long> statusIds,Date startDate,Date endDate,String year);
	 public List<Object[]> getAllNominatedStatusListLevelWise(List<Long> boardLevelId,	List<Long> levelValues,Long levelId, Date startDate,Date endDate,String year);
	 public List<Object[]> getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(List<Long> locationValues,Long searchLevelId,List<Long> statusIdsList,String type,Date startDate,Date endDate,String year);
	 public List<Object[]> getAreaWiseDashboardCandidatesCountView(List<Long> locationValues,Long searchLevelId,List<Long> statusIds,String type,Date startDate,Date endDate,String year);
	 public List<Object[]> getNominatedPositionWiseCandidates(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year,Long boardLevelId,Long startIndex,Long endIndex);
	 public List<Object[]> getNominatedPostLocationStatusWiseCount(List<Long> locationValuesList,Date startDate,Date endDate,Long locationType,Long boardLevelId);
	 public List<Object[]> getDepartmentWisePostDetails(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year,Long boardLevelId,Long deptId);
	 public List<Object[]> getNominatedPostLocationStatusBasedWiseCount(List<Long> locationValuesList,Date startDate,Date endDate,Long locationType,Long boardLevelId);
	 public List<Object[]> getNominatedPostLocationWiseBoardLevelCount(List<Long> locationValuesList,Date startDate,Date endDate,Long locationType,Long boardLevelId);
	 public List<Object[]> getNominatedPostStateWiseCount(Date startDate,Date endDate);
	 public List<Object[]> getLocationWiseNominatedPostCount(String levelStr,Long levelId,List<Long> levelValues,Date startDate,Date endDate);
	 public List<Object[]> getDepartMentAndBoardWisePositinsStatusCount(List<Long> locationValuesList,Date startDate,Date endDate,Long locationType,Long boardLevelId,Long statusId);
}