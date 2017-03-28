package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadrePartyDesignation;

public interface ICadrePartyDesignationDAO extends GenericDao<CadrePartyDesignation, Long>{
	public List<Object[]> getPartyDesignationsByCadreId(Long cadreId);
	public void deleteExisting(Long cadreId);
}
