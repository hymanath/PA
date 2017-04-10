package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRedirectUrlDAO;
import com.itgrids.partyanalyst.model.RedirectUrl;

public class RedirectUrlDAO extends GenericDaoHibernate<RedirectUrl,Long> implements IRedirectUrlDAO{

	public RedirectUrlDAO()
	{
		super(RedirectUrl.class);
	}
}
