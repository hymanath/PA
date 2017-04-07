package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentWorkLocation;

public interface IGovtDepartmentWorkLocationDAO extends GenericDao<GovtDepartmentWorkLocation, Long> {

	public List<Object[]> getLevelWiseInfo(Long deptId,Set<Long> levelIds);
	
}
