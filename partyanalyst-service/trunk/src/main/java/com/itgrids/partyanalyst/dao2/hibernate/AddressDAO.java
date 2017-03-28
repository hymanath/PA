package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAddressDAO;
import com.itgrids.partyanalyst.model.Address;

public class AddressDAO extends GenericDaoHibernate<Address, Long> implements IAddressDAO{

	public AddressDAO() {
		super(Address.class);
	}
	
	public List getAddressDetailsByAddressId(Long addressId){
		
		Query query = getSession().createQuery("select model.address1,model.address2,model.mandal,model.district,model.city,model.state from Address model where model.addressId=?");
		query.setParameter(0,addressId);
		return query.list();
		
	}
	
	
}
