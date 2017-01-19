package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingUpdationDetailsDAO;
import com.itgrids.partyanalyst.model.PartyMeetingUpdationDetails;

public class PartyMeetingUpdationDetailsDAO extends GenericDaoHibernate<PartyMeetingUpdationDetails, Long> implements IPartyMeetingUpdationDetailsDAO{

	public PartyMeetingUpdationDetailsDAO() {
		super(PartyMeetingUpdationDetails.class);
	}

}
