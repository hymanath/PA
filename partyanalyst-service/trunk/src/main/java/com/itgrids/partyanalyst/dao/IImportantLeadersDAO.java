package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ImportantLeaders;

public interface IImportantLeadersDAO extends GenericDao<ImportantLeaders, Long>{

	public List<Object[]> getImportantLeadersInfoByLocation(Long locationId,String searchType);
	public List<Object[]> getImportantLeadersDesignationByCadreList(List<Long> tdpCadreIds);
}
