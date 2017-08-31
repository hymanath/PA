package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaLocationAddressDAO;
import com.itgrids.partyanalyst.model.KaizalaLocationAddress;

public class KaizalaLocationAddressDAO extends GenericDaoHibernate<KaizalaLocationAddress, Long> implements IKaizalaLocationAddressDAO{
	
	public KaizalaLocationAddressDAO() {
		super(KaizalaLocationAddress.class);
	}

}
