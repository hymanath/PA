package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrResponseTypeDAO;
import com.itgrids.partyanalyst.model.IvrResponseType;

public class IvrResponseTypeDAO extends GenericDaoHibernate<IvrResponseType, Long> implements IIvrResponseTypeDAO{
	
	public IvrResponseTypeDAO()
	{
		super(IvrResponseType.class);
	}
	
	
}
