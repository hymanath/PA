package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateCadreOnlineRegistrationDAO;
import com.itgrids.partyanalyst.model.CandidateCadreOnlineRegistration;

public class CandidateCadreOnlineRegistrationDAO extends GenericDaoHibernate<CandidateCadreOnlineRegistration, Long> implements ICandidateCadreOnlineRegistrationDAO{

	public CandidateCadreOnlineRegistrationDAO()
	{
		super(CandidateCadreOnlineRegistration.class);
	}
}
