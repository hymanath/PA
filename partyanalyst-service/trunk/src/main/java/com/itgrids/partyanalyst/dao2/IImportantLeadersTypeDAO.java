package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ImportantLeadersType;

public interface IImportantLeadersTypeDAO extends GenericDao<ImportantLeadersType, Long>{

	public List<Object[]> getAllImportantLeadersTypes();
	public Long getLocationScopeIdForTypeId(Long impLeadTypeId);
	public Integer getMaxOrderNo();
	public Long checkTypeExists(String type);
}
