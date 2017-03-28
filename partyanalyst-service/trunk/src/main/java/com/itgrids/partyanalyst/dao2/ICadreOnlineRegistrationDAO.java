package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreOnlineRegistration;

public interface ICadreOnlineRegistrationDAO extends GenericDao<CadreOnlineRegistration, Long>{

	
	public CadreOnlineRegistration getAllDetailsBasedOnOnlineRegId(Long onlileRegId);
}
