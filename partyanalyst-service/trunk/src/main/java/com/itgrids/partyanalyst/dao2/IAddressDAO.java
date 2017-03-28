package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Address;

public interface IAddressDAO extends GenericDao<Address, Long>{
	
	public List getAddressDetailsByAddressId(Long addressId);

}
