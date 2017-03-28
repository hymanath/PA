package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IMinistryDAO;
import com.itgrids.partyanalyst.model.Ministry;

public class MinistryDAO extends GenericDaoHibernate<Ministry,Long> implements IMinistryDAO{

	public MinistryDAO()
	{
		super(Ministry.class);
	}
}
