package com.itgrids.partyanalyst.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventInvitee;

public interface IEventInviteeDAO extends GenericDao<EventInvitee, Long>{
	public Long checkIsExistDetails(Long id,Long eventId,String memberType);
	public List<Object[]> getEventInviteesCountByLocationType(String locationType,Date currentDate);
	public List<Object[]> getEventInviteesCountByLocationTypeAndEvent(String locationType,Date currentDate,Long eventId);
	public Long getEventInviteesCountByState(Long stateId,Date currentDate,Long eventId);
	public List<Object[]> checkInvitees(List<Long> tdpCadreIds,Long eventId);
	public List<Object[]> getInvitationCountforCandidate(Long tdpCadreId);
	public List<String> getTdpCadreMemberShipsIdsByEvent(Long eventId);
	public List<Object[]> getEventInviteesCountByLocation(String locationType,Set<Long> locationIds,Long eventId);
	
	public List<Object[]> getPublicRepresentiveInvitessForEvent(Long eventId,List<Long> designationIds);
	public List<Object[]> dayWisePublicRepInviteesAttendedForEvent(Date startDate,Date endDate,List<Long> eventIds,List<Long> designationIds);
	public List<Long> getCandidateTdpCadreIds(Long eventId,Long designationId);
	public List<Object[]> getCommitteeLevelInvitessForEvent(Long eventId,List<Long> committeeLevelIds);
	public List<Object[]> getCommitteeRoleInvitessForEvent(Long eventId,List<Long> committeeRoleIds);
	public List<Object[]> dayWiseCommitteeLevelInviteesAttendedForEvent(Date startDate,Date endDate,List<Long> eventIds,List<Long> designationIds);
	public List<Object[]> dayWiseCommitteeRoleInviteesAttendedForEvent(Date startDate,Date endDate,List<Long> eventIds,List<Long> designationIds);
	public List<Object[]> getDistrictAffliatedCommitteeInvitessForEvent(Long eventId,List<Long> committeeRoleIds);
	public List<Object[]> dayWiseDistrictAffliatedCommitteeInviteesAttendedForEvent(Date startDate,Date endDate,List<Long> eventIds,List<Long> designationIds);
	public List<Long> getCandidateTdpCadreIdsForCommitteeLevel(Long eventId,Long committeeLevelId);
	public List<Long> getCandidateTdpCadreIdsForCommitteeRole(Long eventId,Long committeeRoleId,String committeeLevel);
	public List<Long> getCandidateTdpCadreIdsForAffliatedCommitteeRole(Long eventId,Long committeeRoleId,String committeeLevel);
	public List<Object[]> totalPublicRepInviteesAttendedForEvent(List<Long> eventIds,List<Long> designationIds);
	public List<Object[]> totalCommitteeLevelInviteesAttendedForEvent(List<Long> eventIds,List<Long> designationIds);
	public List<Object[]> totalCommitteeRoleInviteesAttendedForEvent(List<Long> eventIds,List<Long> designationIds);
	public List<Object[]> totalDistrictAffliatedCommitteeInviteesAttendedForEvent(List<Long> eventIds,List<Long> designationIds);
	
	public List<Object[]> getEventInviteesCountByCasteIds(Set<Long> casteIds,Long eventId);
	public List<Object[]> getEventInviteesCountByageWiseIds(Set<Long> ageRangeIds,Long eventId);
	public List<Object[]> getEventInviteesCountByGender(Long eventId);
	public List<Object[]> getTotalCadresCountByCasteIds(Set<Long> casteIds);
	public List<Object[]> getEventInviteesCountByCasteCategoryIdsExcludingMinorities(Set<Long> casteCategoryIds,Long eventId);
	public Long getEventInviteesCountForMinorities(Long eventId);
	public List<Object[]> getEventInviteesCountByCadreLocation(String locationType,Long locationId,Long eventId,String searchType,String mandalType);
	public List<Object[]> getInviteedCountByEventsAndLocations(List<Long> eventIds,List<Long> locationValues,Long locationId);
	public List<Object[]> getInviteeAttendeeCountByEventsAndLocations(List<Long> eventIds,List<Long> locationValues,Long locationId);
	public List<Object[]> getTotAttendeeCountByEventsAndLocations(List<Long> eventIds,List<Long> locationValues,Long locationId);
	
	public List<Object[]> getEventInviteedCountByEvent(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds);
	public List<Object[]> getLocationWiseEventInviteedCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds);
	public List<Object[]> getEventInviteeCntByEventAndLocationBasedOnUserType(Long userType,Long stateId,List<Long> eventIds,Long userAccessLevelId,List<Long> userAccessLevelValues,String levelType);
	public List<Object[]> getLocationWiseEventInviteedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds,String locationType);
	public List<Object[]> getEventInviteeDetails(List<Long> cadreIds,List<Long> eventIds);
}
