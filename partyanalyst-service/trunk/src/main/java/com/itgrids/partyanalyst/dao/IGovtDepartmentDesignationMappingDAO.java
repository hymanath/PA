package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationMapping;

public interface IGovtDepartmentDesignationMappingDAO extends GenericDao<GovtDepartmentDesignationMapping, Long>{

	public List<Object[]> getSubDesignationLevelInfo(List<Long> parentDesignationIds);
	public List<Object[]> getSubDesignationsInfo(List<Long> parentDesignationId,Long levelId);
}
