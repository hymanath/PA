package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventAttendee;

public interface IEventAttendeeDAO extends GenericDao<EventAttendee, Long>{
	public List<Object[]> getEventAttendeeInfo(String locationType,String inviteeType,Date currentDate);
	public List checkUserExist(Long tdpCadreId,Long eventId,Date date);
	public List<Object[]> getStateWiseEventAttendeeInfo(String inviteeType,Date currentDate,Long stateId);
	public Long getTotlaVisitsCount(Long parentEventId,Date currentDate);
	public List<Object[]> getHourWiseVisitorsCount(Long parentEventId,Date date);
}
