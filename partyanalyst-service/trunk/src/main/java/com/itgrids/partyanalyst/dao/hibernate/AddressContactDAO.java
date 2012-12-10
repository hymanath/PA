package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.hibernate.Query;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAddressContactDAO;
import com.itgrids.partyanalyst.model.AddressContact;

public class AddressContactDAO extends GenericDaoHibernate<AddressContact, Long> implements IAddressContactDAO {

	public AddressContactDAO() {
		super(AddressContact.class);
		
	}

public Long getAddressContactId(Long addressId,Long phoneNumberId)
{
Query query = getSession().createQuery("select model.addressContactId from AddressContact model where model.address.addressId= ? and model.phoneNumber.phoneNumberId= ? ");
	query.setParameter(0,addressId);
	query.setParameter(1,phoneNumberId);
	return (Long) query.uniqueResult();
}

}


