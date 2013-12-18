package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;
import com.itgrids.partyanalyst.model.MobileAppPinging;

public class MobileAppPingingDAO extends GenericDaoHibernate<MobileAppPinging, Long> implements IMobileAppPingingDAO{

	public MobileAppPingingDAO() {
		super(MobileAppPinging.class);
		
	}

}
