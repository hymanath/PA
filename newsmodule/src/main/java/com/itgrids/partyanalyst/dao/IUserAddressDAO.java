package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserAddress;

public interface IUserAddressDAO extends GenericDao<UserAddress, Long>  {
	
	public Integer deleteInfluencingPeopleById(Long userAddressId);
	
	public void deleteUserAddressByFileId(Long fileId);
	
	public List<UserAddress> getAllAddress(Long fileId,Long districtId);
	
	public List<Object[]> getfileAddressListByFileId(Long fileId);
}
