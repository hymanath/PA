package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointDAO;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPoint;

public class PartyMeetingAtrPointDAO extends GenericDaoHibernate<PartyMeetingAtrPoint,Long> implements IPartyMeetingAtrPointDAO{

	public PartyMeetingAtrPointDAO()
	{
		super(PartyMeetingAtrPoint.class);
	}
}
