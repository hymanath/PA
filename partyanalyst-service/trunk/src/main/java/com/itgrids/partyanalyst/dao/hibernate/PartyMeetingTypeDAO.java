package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;
import com.itgrids.partyanalyst.model.PartyMeetingType;

public class PartyMeetingTypeDAO extends GenericDaoHibernate<PartyMeetingType,Long> implements IPartyMeetingTypeDAO{

	public PartyMeetingTypeDAO()
	{
		super(PartyMeetingType.class);
	}
}
