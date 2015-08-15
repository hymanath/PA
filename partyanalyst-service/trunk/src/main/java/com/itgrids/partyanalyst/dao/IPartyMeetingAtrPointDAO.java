package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPoint;

public interface IPartyMeetingAtrPointDAO extends GenericDao<PartyMeetingAtrPoint,Long>{
	public List<Object[]> getPartyMeetingsATRPointsDetls(Long partyMeetingTypeId);
	public List<Object[]> getAtrDetailsForAMeeting(Long partyMeetingId);
}
