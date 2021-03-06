package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentWorkLocationRelation;

public interface IGovtDepartmentWorkLocationRelationDAO extends GenericDao<GovtDepartmentWorkLocationRelation,Long>{

	public List<Object[]> getGovtSubLevelInfo(Long levelValue);
	public List<Long> getChildLocations(Long levelValue);
	public List<Object[]> getParentGovtSuperLevelInfo(Long levelValue);
}
