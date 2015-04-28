package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventInvitee;

public interface IEventInviteeDAO extends GenericDao<EventInvitee, Long>{
	public List<Object[]> getEventInviteesCountByLocationType(String locationType,Date currentDate);
	public List<Object[]> getEventInviteesCountByLocationTypeAndEvent(String locationType,Date currentDate,Long eventId);
	public Long getEventInviteesCountByState(Long stateId,Date currentDate,Long eventId);
}
