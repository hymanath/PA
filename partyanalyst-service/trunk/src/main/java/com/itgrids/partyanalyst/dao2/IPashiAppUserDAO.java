package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PashiAppUser;

public interface IPashiAppUserDAO extends GenericDao<PashiAppUser, Long>{
	
	public List<PashiAppUser> checkUserPassword(String userName, String password);
}
