package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPost;

public interface INominatedPostDAO extends GenericDao<NominatedPost, Long>{
	public List<Object[]> getTotalAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,List<Long> statusList,Long stateId);
	public List<Object[]> getAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,List<Long> statusList,Long stateId);
	public List<Object[]> getNominatedPostsByBoardsAndDepts(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType);
	public List<Object[]> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType,String positionType);
	public List<NominatedPost> getnominatedPostDetailsBySearchCriteria(Long deptId,Long boardId,Long positionId,Long levelId,Long searchLevelId,Long searchLevelValue);
	public List<Object[]> getNominatdPostStatusCntByPosition(Long positionId);

}