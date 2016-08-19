package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPost;

public interface INominatedPostDAO extends GenericDao<NominatedPost, Long>{
	public List<Object[]> getTotalAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,Long stateId,String statusType);
	public List<Object[]> getAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,List<Long> statusList,Long stateId);
	public List<Object[]> getNominatedPostsByBoardsAndDepts(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType);
	public List<Object[]> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType,String positionType);
	public List<NominatedPost> getNominatedPostDetailsBySearchCriteria(Long departmentId,Long boardId,List<Long> positionIds,Long boardLevelId,List<Long> searchLevelValue);
	public List<Object[]> getNominatdPostStatusCntByPosition(Long positionId);
	
	public List<Object[]> getBoardLevelWiseDepartments(Long postType,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId);
	public List<Object[]> getLevelWiseDepartmentsBoard(Long departmentId,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId);
	public List<Object[]> getLevelWiseDepartmentsBoardPosition(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId);
	public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue,String statusType, Long searchlevelId,Long searchLevelValue);
	public List<Object[]> getNominatedPostsByBoardsAndDeptsForOpen(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType);
	public List<NominatedPost> getNominatedPostDetailsByNominatedPostMember(Long nominatedPostMemberId);
	public List<Object[]> getOpenedPositionsCountByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status);
	public List<Object[]> getOpenedPositionsCountForBoardsByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status);
	public List<Object[]> getTotalPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId,Long stateId);
	public Long getNoCandiateCntPositionAndLocationWise(Long positionId,Long boardLevelId);
	public List<Object[]> getTotalCorpIdsAndBoardsIdsAndPositionsIds(Long boardLevelId,Long searchlevelId,Long searchLevelValue);
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
	public List<Object[]> getApllicationDepmtBoards(Long boardLevelId,Long boardLevel);
	public Integer updateNominatedPost(Long nominatedPostId,Long nominatedPostCandidateId,Date date,Long userId);
}