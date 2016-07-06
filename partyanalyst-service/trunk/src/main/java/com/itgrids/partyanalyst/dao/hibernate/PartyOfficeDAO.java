package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyOfficeDAO;
import com.itgrids.partyanalyst.model.PartyOffice;

public class PartyOfficeDAO extends GenericDaoHibernate<PartyOffice, Long> implements IPartyOfficeDAO {
	public PartyOfficeDAO(){
		super(PartyOffice.class);
	}
	
}
