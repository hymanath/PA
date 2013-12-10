package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFlagDAO;
import com.itgrids.partyanalyst.model.Flag;

public class FlagDAO extends GenericDaoHibernate<Flag,Long> implements IFlagDAO
{

	public FlagDAO() {
		super(Flag.class);
		
	}

}
