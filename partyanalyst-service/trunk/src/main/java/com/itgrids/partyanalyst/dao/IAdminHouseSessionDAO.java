package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AdminHouseSession;

public interface IAdminHouseSessionDAO extends GenericDao<AdminHouseSession, Long>{

	public List<Object[]> getAllSessions(Long termId,String sessionYr);
	public List<Object[]> getAllSessionYears(Long termId);
	public Object[] getDates(Long termId,String sessionYear,Long sessionId);
}
