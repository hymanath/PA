package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommunicationMediaTypeDAO;
import com.itgrids.partyanalyst.model.CommunicationMediaType;

public class CommunicationMediaTypeDAO extends GenericDaoHibernate<CommunicationMediaType,Long> implements ICommunicationMediaTypeDAO{
	public CommunicationMediaTypeDAO()
	{
		super(CommunicationMediaType.class);
	}
}
