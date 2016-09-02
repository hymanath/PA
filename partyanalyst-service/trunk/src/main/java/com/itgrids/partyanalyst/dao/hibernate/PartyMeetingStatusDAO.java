package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.model.PartyMeetingStatus;

public class PartyMeetingStatusDAO extends GenericDaoHibernate<PartyMeetingStatus, Long> implements IPartyMeetingStatusDAO {

	public PartyMeetingStatusDAO() {
		super(PartyMeetingStatus.class);
	}
}
