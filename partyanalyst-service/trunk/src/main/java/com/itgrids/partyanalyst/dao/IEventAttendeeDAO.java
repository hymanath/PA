package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventAttendee;

public interface IEventAttendeeDAO extends GenericDao<EventAttendee, Long>{
	public List<Object[]> getEventAttendeeInfo(String locationType,String inviteeType,Date currentDate);
	public List checkUserExist(Long tdpCadreId,Long eventId,Date date);
	public List<Object[]> getStateWiseEventAttendeeInfo(String inviteeType,Date currentDate,Long stateId);

	public List<Object[]> getHourWiseVisitorsCount(Long parentEventId,Date date,List<Long> subeventIds);
	public List<Object[]> getUnionMembersForEvent(Long eventId,Long compareEventId,Date startDate,Date endDate);
	public List<Object[]> getEventCountsByParentEventId(Long parentEventId,List<Long> subeventIds,Date startDate,Date endDate);
	public List<Object[]> getTotlaVisitsCount(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds);
	public List<Object[]> getDayWiseVisitorsCount(Long parentEventId,List<Long> subeventIds,Date startDate,Date endDate);
	public List<Object[]> getUnionMembersForEventSQL(Long eventId,Long compareEventId,Date startDate,Date endDate);
	public List<Object[]> getStateWiseEventAttendeeCounts(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds);
	
	public List<Object[]> getMembersDetailsBySubEvent(Long eventId,Date startDate,Date endDate,Integer startIndex,Integer maxIndex);
}
