package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.UserAcessIpAddress;

public interface IUserAcessIpAddressDAO extends GenericDao<UserAcessIpAddress, Long> {
public List<?> checkForAccess(final RegistrationVO registrationVO , String IpAddress); 

public List<Long> checkDuplicateIpForUser(Long userId,String Ip);

public List<Object[]> getAllIpAddressByUser(Long userId);

public Integer deleteUserIpAddressById(Long userAcessIpAddressId);
	
}
