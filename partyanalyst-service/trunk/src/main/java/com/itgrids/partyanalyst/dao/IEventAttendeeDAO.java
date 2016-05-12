package com.itgrids.partyanalyst.dao;

import java.math.BigInteger;
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
	public Long getTodayTotalVisitors(Date todayDate,Long parentEventId);
	public BigInteger getCurrentVisitors(Date todayDate,Long entryEventId,Long exitEventId);
	public List<Object[]> getCadreVisitInfo(Date todayDate,Long entryEventId,Long exitEventId);
	public List<Object[]> getEventAttendeeInfoDynamicIndiDates(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds);
	public List<Object[]> getEventAttendeesSummary(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds);
	public List<Object[]> getOtherStateDeligatesCount(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds);
	public List<Object[]> getStateCount(Long eventId,Date startDate,Date endDate);
	public List<Object[]> getOtherStateCount(Long eventId,Date startDate,Date endDate);
	public BigInteger getCurrentInviteeVisitors(Date todayDate,Long entryEventId,Long exitEventId);
	public List<Object[]> getEventAttendeeInfoDynamicIndiDatesForInvities(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds);
	public List<Object[]> getEventAttendeesSummaryForInvities(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds);
	public List<Object[]> getEventDetailsOfCadre(Long cadreId);
	public List checkEventsyncData(String rfid,String imei,Long eventId,String uniqueKey);
	public List<Object[]> getAttendedEventsCountforCandidate(Long tdpCadreId);
	
	public List<Object[]> getRequiredStateWiseEventAttendeeInfo(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,Long stateId);
	public List<Object[]> getRequiredEventAttendeeInfo(String locationType,String inviteeType,Date startDate,Date endDate,List<Long> eventIds);
	public List<Object[]> getEventAttendeeInfoByLocation(String queryString,Date startDate,Date endDate,List<Long> eventIds);
}
