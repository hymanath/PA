package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreLeadershipLevel;

public interface ICadreLeadershipLevelDAO extends GenericDao<CadreLeadershipLevel, Long>{
	
	public List<Object[]> getAllLeaderShipLevels();
}
