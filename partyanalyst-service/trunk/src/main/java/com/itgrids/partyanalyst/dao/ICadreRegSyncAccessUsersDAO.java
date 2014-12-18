package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegSyncAccessUsers;

public interface ICadreRegSyncAccessUsersDAO extends GenericDao<CadreRegSyncAccessUsers, Long>{
	public Long checkHasAccess(Long userId);
}
