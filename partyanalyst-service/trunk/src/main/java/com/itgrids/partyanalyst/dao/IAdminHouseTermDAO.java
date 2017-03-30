package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AdminHouseTerm;

public interface IAdminHouseTermDAO extends GenericDao<AdminHouseTerm, Long>{
	
	public List<Object[]> getAllElectionYears();

}
