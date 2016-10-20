package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteStatusDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMinuteStatus;

public class PartyMeetingMinuteStatusDAO extends GenericDaoHibernate<PartyMeetingMinuteStatus, Long> implements IPartyMeetingMinuteStatusDAO
{
	public PartyMeetingMinuteStatusDAO() {
		super(PartyMeetingMinuteStatus.class);
	} 

}
