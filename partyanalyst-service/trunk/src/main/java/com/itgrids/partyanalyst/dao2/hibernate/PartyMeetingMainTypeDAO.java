package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingMainTypeDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMainType;

public class PartyMeetingMainTypeDAO extends GenericDaoHibernate<PartyMeetingMainType, Long> implements IPartyMeetingMainTypeDAO {
	
	public PartyMeetingMainTypeDAO() {
		super(PartyMeetingMainType.class);
	}
}
