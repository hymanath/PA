package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyDesignationDAO;
import com.itgrids.partyanalyst.model.PartyDesignation;

public class PartyDesignationDAO extends GenericDaoHibernate<PartyDesignation, Long> implements IPartyDesignationDAO{

	public PartyDesignationDAO() {
		super(PartyDesignation.class);
		// TODO Auto-generated constructor stub
	}

}
