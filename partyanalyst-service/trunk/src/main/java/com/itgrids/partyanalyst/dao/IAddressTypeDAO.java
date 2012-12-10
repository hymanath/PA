package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AddressType;



public interface IAddressTypeDAO extends GenericDao<AddressType, Long>{
	
	public List getAddressTypeByAddressTypeId(Long addressTypeId);
	public List<Object[]> getAllAddressTypes();
}
