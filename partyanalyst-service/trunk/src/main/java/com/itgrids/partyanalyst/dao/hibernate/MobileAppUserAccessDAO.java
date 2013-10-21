package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileAppUserAccessDAO;
import com.itgrids.partyanalyst.model.MobileAppUserAccess;

public class MobileAppUserAccessDAO extends GenericDaoHibernate<MobileAppUserAccess, Long> implements IMobileAppUserAccessDAO{

	public MobileAppUserAccessDAO() {
		super(MobileAppUserAccess.class);
		
	}
	

}
