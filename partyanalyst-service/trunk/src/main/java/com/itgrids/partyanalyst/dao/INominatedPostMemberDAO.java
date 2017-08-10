package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostMember;

public interface INominatedPostMemberDAO extends GenericDao<NominatedPostMember, Long>{

	public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue,String statusType,Long searchlevelId,Long searchLevelValue);
	public Long getNominatedPostMemberId(Long levelId,Long levelValue,Long deptId,Long boardId,Long positionId);
	public List<Object[]> getPositionList();
	public List<Object[]> getLocationLevelList();
	public List<Object[]> getDepartmentList(Long boardLevelId);
	public List<Object[]> getBoardsList(List<Long> deptId,Long boardLevelId);
	public List<Object[]> getTotalBoardsAndCorpIdsByMembrIdsList (List<Long> memberIdsList);
	public List<Object[]> getLocationByDepartment(Long levelId,Long departmentId,Long boardId,Long positionId);
	public List<Object[]> getNominatedPostPositionDtls(Long departmentId,Long boardId,Long positionId);
}
