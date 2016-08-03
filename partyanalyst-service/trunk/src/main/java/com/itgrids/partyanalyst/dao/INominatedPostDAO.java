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
	
	public List<Object[]> getBoardLevelWiseDepartments(Long postType,Long boardLevelId,Long searchLevelValue);
	public List<Object[]> getLevelWiseDepartmentsBoard(Long departmentId,Long boardLevelId,Long searchLevelValue);
	public List<Object[]> getLevelWiseDepartmentsBoardPosition(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue);
	public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue,String statusType, Long searchlevelId,Long searchLevelValue);
	public List<Object[]> getNominatedPostsByBoardsAndDeptsForOpen(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType);
	public List<NominatedPost> getNominatedPostDetailsByNominatedPostMember(Long nominatedPostMemberId);
	public List<Object[]> getOpenedPositionsCountByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue);
	public List<Object[]> getOpenedPositionsCountForBoardsByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue);
	public List<Object[]> getTotalPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId);
	public Long getNoCandiateCntPositionAndLocationWise(Long positionId,Long boardLevelId);
	public List<Object[]> getTotalCorpAndBoardsAndPositions(Long boardLevelId, Date startDate,Date toDate,Long stateId);
}