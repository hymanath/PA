package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUserProfile;

public interface IMobileAppUserProfileDAO extends GenericDao<MobileAppUserProfile, Long>{
	public List<Object[]> getMobileNoByUniquecode(String uniqueCode);
}
