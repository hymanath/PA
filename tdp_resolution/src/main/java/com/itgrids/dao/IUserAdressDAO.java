package com.itgrids.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.model.UserAddress;

public interface IUserAdressDAO extends GenericDao<UserAddress,Long>{

	public UserAddress getUserAddressById(Long userAddressId);
	
	public Integer updateUserAddress(Long stateId,Long districtId);
}
