package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingUpdationDetails;

public interface IPartyMeetingUpdationDetailsDAO extends GenericDao<PartyMeetingUpdationDetails, Long>{
	public List<Object[]> getCommentsAvailableByPartyMeetingId(List<Long> partyMeetingIds);
	public List<Object[]> getCommentsDetailsByPartyMeetingId(Long partyMeetingId);

}
