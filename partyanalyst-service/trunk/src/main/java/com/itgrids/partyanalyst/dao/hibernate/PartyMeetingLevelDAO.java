package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IPartyMeetingLevelDAO;
import com.itgrids.partyanalyst.model.PartyMeetingLevel;

public class PartyMeetingLevelDAO extends GenericDaoHibernate<PartyMeetingLevel,Long> implements IPartyMeetingLevelDAO{

	public PartyMeetingLevelDAO()
	{
		super(PartyMeetingLevel.class);
	}
}
