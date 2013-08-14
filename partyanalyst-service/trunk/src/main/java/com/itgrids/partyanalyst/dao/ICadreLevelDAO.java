package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreLevel;


public interface ICadreLevelDAO extends GenericDao<CadreLevel, Long> {

	public List<CadreLevel> findByCadreLevel(String cadreLevel);
	
	public List<CadreLevel> getCadreLevelList();
	
}
