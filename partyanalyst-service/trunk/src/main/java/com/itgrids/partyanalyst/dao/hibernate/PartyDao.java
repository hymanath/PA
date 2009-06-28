package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.Party;

public class PartyDao extends GenericDaoHibernate<Party, Long> implements IPartyDao{

	public PartyDao() {
		super(Party.class);
	}

}
