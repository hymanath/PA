package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DepartmentBoardPosition;

public interface IDepartmentBoardPositionDAO extends GenericDao<DepartmentBoardPosition, Long>{

	public List<Object[]> getDepartmentBoardPositionsByDeptIdABrdId(Long deapartmentId,Long boardId);
}
