package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreGhmcDriveUsers;


public interface ICadreGhmcDriveUsersDAO extends GenericDao<CadreGhmcDriveUsers, Long>{
		public List<Long> getGHMCDriveUsers();
}
