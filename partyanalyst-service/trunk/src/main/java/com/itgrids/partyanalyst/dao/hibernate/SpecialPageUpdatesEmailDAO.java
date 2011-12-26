package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISpecialPageUpdatesEmailDAO;
import com.itgrids.partyanalyst.model.SpecialPageUpdatesEmail;

public class SpecialPageUpdatesEmailDAO extends GenericDaoHibernate<SpecialPageUpdatesEmail,Long> implements ISpecialPageUpdatesEmailDAO{

	public SpecialPageUpdatesEmailDAO()
	{
	   super(SpecialPageUpdatesEmail.class);
	}
}
