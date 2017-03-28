package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserAccessIpAddress;

public interface IUserAccessIpAddressDAO extends GenericDao<UserAccessIpAddress, Long> {
	
	public Long checkForUserAccessIPAddress(Long userId,String ipAddress);
	
	public List<Long> checkDuplicateIpForUser(Long userId,String Ip);

	public List<Object[]> getAllIpAddressByUser(Long userId);

	public Integer deleteUserIpAddressById(Long userAcessIpAddressId);
	
}
