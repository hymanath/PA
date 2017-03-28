package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AddressContact;

public interface IAddressContactDAO  extends GenericDao<AddressContact, Long>{
	public Long getAddressContactId(Long addressId,Long phoneNumberId);
}
