package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DepartmentBoard;

public interface IDepartmentBoardDAO extends GenericDao<DepartmentBoard, Long>{

	public List<Object[]> getDepartmentBoardByDeptId(Long deapartmentId);
}
