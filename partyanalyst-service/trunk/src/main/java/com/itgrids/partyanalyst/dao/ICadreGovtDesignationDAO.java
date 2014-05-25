package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreGovtDesignation;
import com.itgrids.partyanalyst.model.CadrePartyDesignation;

public interface ICadreGovtDesignationDAO extends GenericDao<CadreGovtDesignation, Long>{
	public List<Object[]> findByCadreId(Long cadreId);
	public void deleteExisting(Long cadreId);
}
