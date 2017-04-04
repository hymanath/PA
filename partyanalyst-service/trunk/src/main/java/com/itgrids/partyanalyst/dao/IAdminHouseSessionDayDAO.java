package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AdminHouseSessionDay;

public interface IAdminHouseSessionDayDAO extends GenericDao<AdminHouseSessionDay, Long>{
	
	public List<Object[]> getSingleDate(Long adminHuSessionId);
	public Object[] getDates(Long adminHuSessionId);
}
