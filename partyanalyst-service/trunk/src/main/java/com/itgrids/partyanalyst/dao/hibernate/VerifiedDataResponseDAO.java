package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVerifiedDataResponseDAO;
import com.itgrids.partyanalyst.model.VerifiedDataResponse;

public class VerifiedDataResponseDAO extends GenericDaoHibernate<VerifiedDataResponse,Long> implements IVerifiedDataResponseDAO{
	
	public VerifiedDataResponseDAO()
	{
		super(VerifiedDataResponse.class);
	}
}
