package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserEntitlement;

public interface IUserEntitlementDAO   extends GenericDao<UserEntitlement,Long>{

	public List<String> getAllUserEntitlements(Long userId);
}
