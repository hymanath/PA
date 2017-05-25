package com.itgrids.partyanalyst.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventAttendee;

public interface IEventAttendeeDAO extends GenericDao<EventAttendee, Long>{
	public List<Object[]> getEventAttendeeInfo(String locationType,String inviteeType,Date currentDate);
	public List checkUserExist(Long tdpCadreId,Long eventId,Date date);
	public List<Object[]> getStateWiseEventAttendeeInfo(String inviteeType,Date currentDate,Long stateId);

	public List<Object[]> getHourWiseVisitorsCount(Long parentEventId,Date date,List<Long> subeventIds,List<Long> enrollmentYearIds);
	public List<Object[]> getUnionMembersForEvent(Long eventId,Long compareEventId,Date startDate,Date endDate,List<Long> enrollmentYearIds);
	public List<Object[]> getEventCountsByParentEventId(Long parentEventId,List<Long> subeventIds,Date startDate,Date endDate,List<Long> enrollmentYearIds);
	public Long getUniqueVisitorsAttendedCount(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds,List<Long> enrollmentYearIds);
	public List<Object[]> getDayWiseVisitorsCount(Long parentEventId,List<Long> subeventIds,Date startDate,Date endDate,List<Long> enrollmentYearIds);
	public List<Object[]> getUnionMembersForEventSQL(Long eventId,Long compareEventId,Date startDate,Date endDate);
	public List<Object[]> getStateWiseEventAttendeeCounts(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds,List<Long> enrollmentYearIds);
	
	public List<Object[]> getMembersDetailsBySubEvent(Long eventId,Date startDate,Date endDate,Integer startIndex,Integer maxIndex);
	public Long getTodayTotalVisitors(Date todayDate,Long parentEventId,Long entryId,List<Long> enrollmentIdsList);
	public BigInteger getCurrentVisitors(Date todayDate,Long entryEventId,Long exitEventId,List<Long> enrollmentIdsList);
	public List<Object[]> getCadreVisitInfo(Date todayDate,Long entryEventId,Long exitEventId);
	public List<Object[]> getEventAttendeeInfoDynamicIndiDates(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds,List<Long> enrollmentIdsList);
	public List<Object[]> getEventAttendeesSummary(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds,List<Long> enrollmentIdsList);
	public List<Object[]> getOtherStateDeligatesCount(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds);
	public List<Object[]> getStateCount(Long eventId,Date startDate,Date endDate);
	public List<Object[]> getOtherStateCount(Long eventId,Date startDate,Date endDate);
	public BigInteger getCurrentInviteeVisitors(Date todayDate,Long entryEventId,Long exitEventId,List<Long> enrollmentIdsList);
	public List<Object[]> getEventAttendeeInfoDynamicIndiDatesForInvities(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds);
	public List<Object[]> getEventAttendeesSummaryForInvities(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds);
	public List<Object[]> getEventDetailsOfCadre(Long cadreId);
	public List checkEventsyncData(String rfid,String imei,Long eventId,String uniqueKey);
	public List<Object[]> getAttendedEventsCountforCandidate(Long tdpCadreId);
	
	public List<Object[]> getRequiredStateWiseEventAttendeeInfo(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,Long stateId);
	public List<Object[]> getRequiredEventAttendeeInfo(String locationType,String inviteeType,Date startDate,Date endDate,List<Long> eventIds);
	public List<Object[]> getEventAttendeeInfoByLocation(String queryString,Date startDate,Date endDate,List<Long> eventIds);
	
	public Long getUniqueInviteeVisitorsAttendedcount(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds,List<Long> enrollmentYearIds);
	
	public Long getTodayTotalInviteeVisitors(Date todayDate,Long parentEventId,Long entryEventId,List<Long> enrollmentIdsList);
	public List<Object[]> getHourWiseTotalVisitorsCount1(Long parentEventId,Date date,List<Long> subeventIds,String type);
	public List<Object[]> getHourWiseCurrentVisitorsCount(Date todayDate,Long entryEventId,Long exitEventId,String type,List<Long> enrollmentIdsList);
	
	public List<Object[]> getHourWiseTotalVisitorsCount(Long parentEventId,Date date,List<Long> subeventIds,String type,List<Long> enrollmentIdsLis);
	public Long getTotalAttendedCountOfEvent(Long eventId,List<Long> enrollmentIdsList);
	public List<Object[]>  locationWiseEventAttendeeCountsQuery(String locationType,String inviteeType,Date startDate,Date endDate,List<Long> eventIds,String queryString,List<Long> enrollmentYearIds);
	public List<Object[]> locationWiseEventAttendeeCountsByDateQuery(String locationType,String inviteeType,Date startDate,Date endDate,List<Long> eventIds,String queryString,List<Long> enrollmentYearIds);
	
	public Long stateWiseEventAttendeeCounts(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,Long stateId ,String statesType,List<Long> enrollmentYearIds);
	public List<Object[]> stateWiseEventAttendeeCountsByDates(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,Long stateId ,String statesType,List<Long> enrollmentYearIds);
	public List<Object[]> getDistrictWiseCurrentCadreInCampus(Date todayDate,Long entryEventId,Long exitEventId,String queryStr,List<Long> enrollmentIds);
	public List<Object[]> getConstituencyWiseCurrentCadreInCampus(Date todayDate,Long entryEventId,Long exitEventId,String queryStr,List<Long> enrolmentIds);
	public List<Object[]> getDistrictWiseTotalInvitedAndNonInvitedCount(Long eventId,String queryStr,Date todayDate,List<Long> enrolmentIds);
	public List<Object[]> getConstituencyWiseTotalInvitedAndNonInvitedCount(Long eventId,String queryStr,Date todayDate,List<Long> enrollmentIds);
	public List<Object[]> getOtherStateDistrictWiseCurrentCadreInCampus(Date todayDate,Long entryEventId,Long exitEventId,String queryStr,List<Long> enrollmentIds);
	public List<Object[]> getOtherStateConstituencyWiseCurrentCadreInCampus(Date todayDate,Long entryEventId,Long exitEventId,String queryStr,List<Long> enrollmentIds);
	public List<Object[]> getOtherStatesDistrictWiseTotalInvitedAndNonInvitedCount(Long eventId,String queryStr,Date todayDate,List<Long> enrollmentIds);
	public List<Object[]> getOtherStatesConstituencyWiseTotalInvitedAndNonInvitedCount(Long eventId,String queryStr,Date todayDate,List<Long> enrollmentIds);
	public List<Long> getAttendenceDetails(List<Long> cadreIds,Date date,Long eventId);
	public List<Long> getCadreIdsForAttendees(Long eventId,Date date,Long designationId);
	public List<Object[]> getEventAttendedDetails(Long cadreId,Long eventId);
	public List<Object[]> getEventAttendedInfoForCadre(Long cadreId,Long eventId);
	public List<Long> getCadreIdsForAttendeesForCommitteeLevel(Long eventId,Date date,Long committeeLevelId);
	public List<Long> getCadreIdsForAttendeesForCommitteeRole(Long eventId,Date date,Long committeeRoleId,String committeeLevel);
	public List<Long> getCadreIdsForAttendeesForAffliatedCommitteeRole(Long eventId,Date date,Long committeeRoleId,String committeeLevel);
	public List<Object[]> getAttendenceDetailsForCadre(List<Long> cadreIds,Long eventId);
	
	//caste wise
	public List<Object[]>  casteWiseEventAttendeeCountsQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	public List<Object[]> casteWiseEventAttendeeCountsByDateQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	
	public List<Object[]>  ageWiseEventAttendeeCountsQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	public List<Object[]> ageWiseEventAttendeeCountsByDateQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	
	public List<Object[]>  genderWiseEventAttendeeCountsQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	public List<Object[]> genderWiseEventAttendeeCountsByDateQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	
	public List<Object[]>  casteCategoryWiseEventAttendeeCountsExcludingMinorities(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	public Long  casteCategoryWiseEventAttendeeCountsForMinorities(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	
	public List<Object[]> casteCategoryWiseEventAttendeeCountsByDateExcludingMinorities(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	public List<Object[]> casteCategoryWiseEventAttendeeCountsByDateForMinorities(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,List<Long> enrollmentYrIds);
	public List<Object[]> getLocationWiseAttendeesCount(String locationType, Long locationId, Long eventId , String inviteeType,String searchType,Date startDate, Date endDate,String mandalData);
	public List<Object[]> cadreLocationWiseEventAttendeeCountsByDateQuery(String locationType,String inviteeType,Date startDate,Date endDate,Long eventId,Long locationId,String searchType,String mandalType);
	public Long getUniqueVisitorsAttendedCountForCadre(Long eventId,Date startDate,Date endDate);
	public List<Object[]> getEventIdAndPresentedCadreIdList(Date fromDate, Date toDate);
	
    public List<Object[]> getEventAttendedCountByEvent(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds);
	public List<Object[]> getEventInviteeAttendedCountByEvent(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds);
	public List<Object[]> getEventAttendedCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds);
	public List<Object[]> getEventInviteeAttendedCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds);
	public List<Object[]> getEventAttendedCntByEventAndLocationBasedOnUserType(Long userType,Long stateId,List<Long> eventIds,Long userAccessLevelId,List<Long> userAccessLevelValues,String levelType);
	public List<Object[]> getEventInviteeAttendedCntByEventAndLocationBasedOnUserType(Long userType,Long stateId,List<Long> eventIds,Long userAccessLevelId,List<Long> userAccessLevelValues,String levelType);
	
	public List<Object[]> getLocationWiseEventAttendedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds,String locationType);
	public List<Object[]> getLocationWiseEventInviteeAttendedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds,String locationType);
	public List<Object[]> getEventAttendeeSummary(List<Long> cadreIds,List<Long> eventIds);
	public List<Object[]> getMahanaduEventCadreDetails(List<Long> eventIds,Long tdpCadreId);
	public List<Object[]> getCadreDetailedReportEventAttendee(Long parentEventId,Long cadreId);
	public List<Object[]> getCadreDetailedReportEventAttendeeDetailsDayWise(Long parentEventId,Long cadreId);
	public List<Object[]> getCadreDetailedReportEventAttendeeMaxTime(List<Long> extraEventIdsList);
	public List<Object[]> getCadreDetailedReportEventAttendeeMinTime(List<Long> extraEventIdsList);
	public String getCadreDetailedReportEventAttendeeMaxTime1(List<Long> extraEventIdsList);
	public String  getCadreDetailedReportEventAttendeeMinTime1(List<Long> extraEventIdsList);
	public List<Object[]> getDiasEntryExitCandisTimeDeatails(Long eventId,Date eventDate);
}
