package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HouseSession;

public interface IHouseSessionDAO extends GenericDao<HouseSession, Long>{
	
	public List<Object[]> getAllSessions();

}
