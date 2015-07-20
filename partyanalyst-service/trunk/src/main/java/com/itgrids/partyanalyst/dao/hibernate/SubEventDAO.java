package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISubEventDAO;
import com.itgrids.partyanalyst.model.SubEvent;

public class SubEventDAO extends GenericDaoHibernate<SubEvent, Long> implements ISubEventDAO{

	public SubEventDAO() {
		super(SubEvent.class);
	}

}
