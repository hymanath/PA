package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUser;

public interface IMobileAppUserDAO extends GenericDao<MobileAppUser, Long>{

	public List<Object> checkUniqueCode(String uniqueCode);
	
	public List<Object[]> getUserList();
	
	public List<Object> getUserId(String uniqueCode);
}
