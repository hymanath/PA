package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserAddress;

public interface IUserAddressDAO extends GenericDao<UserAddress, Long>  {
	
	public Integer deleteInfluencingPeopleById(Long userAddressId);
	
	public List<UserAddress> getUserAddressList();
	
	public List<UserAddress> getUserAddressByUserAddressId(Long userAddressId);
	public List<Object[]> getUserAddressDetails(List<Long> candidateIdsList);
}
