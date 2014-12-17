package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVerifiedDataRequestDAO;
import com.itgrids.partyanalyst.model.VerifiedDataRequest;

public class VerifiedDataRequestDAO extends GenericDaoHibernate<VerifiedDataRequest,Long> implements IVerifiedDataRequestDAO{

	public VerifiedDataRequestDAO()
	{
		super(VerifiedDataRequest.class);
	}
}
