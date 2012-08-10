package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyCadreOnlineRegistrationDAO;
import com.itgrids.partyanalyst.model.PartyCadreOnlineRegistration;

public class PartyCadreOnlineRegistrationDAO extends GenericDaoHibernate<PartyCadreOnlineRegistration, Long> implements IPartyCadreOnlineRegistrationDAO{
	
	public PartyCadreOnlineRegistrationDAO()
	{
		super(PartyCadreOnlineRegistration.class);
	}

}
