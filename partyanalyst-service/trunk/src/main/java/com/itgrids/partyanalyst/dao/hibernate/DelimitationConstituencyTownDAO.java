package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyTownDAO;
import com.itgrids.partyanalyst.model.DelimitationConstituencyTown;

public class DelimitationConstituencyTownDAO extends GenericDaoHibernate<DelimitationConstituencyTown,Long> implements IDelimitationConstituencyTownDAO{

	public DelimitationConstituencyTownDAO(){
		super(DelimitationConstituencyTown.class);
	}
}
