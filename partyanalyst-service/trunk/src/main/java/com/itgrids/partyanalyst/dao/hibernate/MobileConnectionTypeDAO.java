package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileConnectionTypeDAO;
import com.itgrids.partyanalyst.model.MobileConnectionType;

public class MobileConnectionTypeDAO extends GenericDaoHibernate<MobileConnectionType, Long> implements IMobileConnectionTypeDAO{

	public MobileConnectionTypeDAO() {
		super(MobileConnectionType.class);
		
	}
}
