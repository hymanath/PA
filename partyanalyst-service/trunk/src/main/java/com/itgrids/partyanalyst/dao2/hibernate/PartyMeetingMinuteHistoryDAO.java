package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteHistoryDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMinuteHistory;

public class PartyMeetingMinuteHistoryDAO extends GenericDaoHibernate<PartyMeetingMinuteHistory,Long> implements IPartyMeetingMinuteHistoryDAO{

	public PartyMeetingMinuteHistoryDAO()
	{
		super(PartyMeetingMinuteHistory.class);
	}
}
