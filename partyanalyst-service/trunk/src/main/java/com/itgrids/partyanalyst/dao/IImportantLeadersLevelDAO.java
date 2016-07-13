package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ImportantLeadersLevel;

public interface IImportantLeadersLevelDAO extends GenericDao<ImportantLeadersLevel, Long>{

	public List<Object[]> getAllRegionScopesWithOutOrderBy();
}
