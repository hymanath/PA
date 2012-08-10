package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreOnlineRegistrationDAO;
import com.itgrids.partyanalyst.model.CadreOnlineRegistration;

public class CadreOnlineRegistrationDAO extends GenericDaoHibernate<CadreOnlineRegistration, Long> implements ICadreOnlineRegistrationDAO{

	public CadreOnlineRegistrationDAO()
	{
		super(CadreOnlineRegistration.class);
	}
	
}
