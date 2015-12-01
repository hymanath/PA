package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommunicationMediaTypeInfoDAO;
import com.itgrids.partyanalyst.model.CommunicationMediaTypeInfo;

public class CommunicationMediaTypeInfoDAO extends GenericDaoHibernate<CommunicationMediaTypeInfo,Long> implements ICommunicationMediaTypeInfoDAO{

	public CommunicationMediaTypeInfoDAO()
	{
		super(CommunicationMediaTypeInfo.class);
	}

}
