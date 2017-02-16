package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentLevel;

public interface IGovtDepartmentLevelDAO extends GenericDao<GovtDepartmentLevel, Long>{

	public List<Object[]> getDepartmentLevels();
	public List<Object[]> getLowerLevelsByLevel(Long levelId);
}
