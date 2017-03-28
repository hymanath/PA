package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointHistoryDAO;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPointHistory;

public class PartyMeetingAtrPointHistoryDAO extends GenericDaoHibernate<PartyMeetingAtrPointHistory,Long> implements IPartyMeetingAtrPointHistoryDAO{

	public PartyMeetingAtrPointHistoryDAO()
	{
		super(PartyMeetingAtrPointHistory.class);
	}
}
