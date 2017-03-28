package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingGroupDAO;
import com.itgrids.partyanalyst.model.PartyMeetingGroup;

public class PartyMeetingGroupDAO extends GenericDaoHibernate<PartyMeetingGroup, Long> implements IPartyMeetingGroupDAO{

	public PartyMeetingGroupDAO() {
		super(PartyMeetingGroup.class);
		
	}

}
