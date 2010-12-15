package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationWardDAO;
import com.itgrids.partyanalyst.model.DelimitationWard;

public class DelimitationWardDAO extends GenericDaoHibernate<DelimitationWard, Long> implements IDelimitationWardDAO{
	
	public DelimitationWardDAO()
	{
		super(DelimitationWard.class);
	}

}
