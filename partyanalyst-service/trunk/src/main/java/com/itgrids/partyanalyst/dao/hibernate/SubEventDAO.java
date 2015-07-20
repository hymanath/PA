package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.jpa.GenericDaoJpa;

import com.itgrids.partyanalyst.dao.ISubEventDAO;
import com.itgrids.partyanalyst.model.SubEvent;

public class SubEventDAO extends GenericDaoJpa<SubEvent, Long> implements ISubEventDAO{

	public SubEventDAO() {
		super(SubEvent.class);
	}

}
