package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteTrackingDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMinuteTracking;

public class PartyMeetingMinuteTrackingDAO extends GenericDaoHibernate<PartyMeetingMinuteTracking, Long> implements IPartyMeetingMinuteTrackingDAO 
{

	public PartyMeetingMinuteTrackingDAO() {
		super(PartyMeetingMinuteTracking.class);
	}
	
}
