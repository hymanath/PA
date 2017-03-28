package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileNetworkTypeDAO;
import com.itgrids.partyanalyst.model.MobileNetworkType;

public class MobileNetworkTypeDAO extends GenericDaoHibernate<MobileNetworkType, Long> implements IMobileNetworkTypeDAO{

	public MobileNetworkTypeDAO() {
		super(MobileNetworkType.class);
		
	}
}
