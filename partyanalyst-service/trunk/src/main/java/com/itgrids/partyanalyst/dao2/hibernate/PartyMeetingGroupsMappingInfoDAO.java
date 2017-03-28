package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingGroupsMappingInfoDAO;
import com.itgrids.partyanalyst.model.PartyMeetingGroupsMappingInfo;

public class PartyMeetingGroupsMappingInfoDAO extends GenericDaoHibernate<PartyMeetingGroupsMappingInfo, Long> implements IPartyMeetingGroupsMappingInfoDAO{

	public PartyMeetingGroupsMappingInfoDAO() {
		super(PartyMeetingGroupsMappingInfo.class);
		
	}

}
