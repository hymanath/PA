package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtUserAddressDAO;
import com.itgrids.partyanalyst.model.GovtUserAddress;

public class GovtUserAddressDAO extends GenericDaoHibernate<GovtUserAddress, Long> implements IGovtUserAddressDAO {

	public GovtUserAddressDAO(){
		super(GovtUserAddress.class);
	}
}
