package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUserSmsDetails;

public interface IMobileAppUserSmsDetailsDAO extends GenericDao<MobileAppUserSmsDetails, Long>{
	public List<MobileAppUserSmsDetails> getMobileAppUserSmsDetails(Long mobileAppUserId);

}
