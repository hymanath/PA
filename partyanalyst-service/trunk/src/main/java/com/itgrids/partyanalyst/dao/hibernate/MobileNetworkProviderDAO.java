package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileNetworkProviderDAO;
import com.itgrids.partyanalyst.model.MobileNetworkProvider;

public class MobileNetworkProviderDAO extends GenericDaoHibernate<MobileNetworkProvider, Long> implements IMobileNetworkProviderDAO{

	public MobileNetworkProviderDAO() {
		super(MobileNetworkProvider.class);
	}
}
