package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserAddress;

public interface IUserAddressDAO extends GenericDao<UserAddress, Long>  {
	
	public Integer deleteInfluencingPeopleById(Long userAddressId);
	
}
