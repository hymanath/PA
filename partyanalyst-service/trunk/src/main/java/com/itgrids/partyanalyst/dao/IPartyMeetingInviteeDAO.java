package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
import com.itgrids.partyanalyst.model.PartyMeetingInvitee;

public interface IPartyMeetingInviteeDAO extends GenericDao<PartyMeetingInvitee,Long>{
	
public List<Object[]> getPartyMeetingsInvitationsDetailsByCadreIds(List<Long> tdpCadreIdsList,Date toDayDate);
	
	public List<Object[]> getPartyMeetingsInvitationDetlsByCadreIds(List<Long> tdpCadreIdsList,Long partyMeetingTypeId,Date todayDate);
	
	public List<String> getPartyMeetingInvittees(Long partyMeetingId);
	
	public List<Object[]> getPartyMeetingInviteesForMeetings(List<Long> partyMeetingIds);
	
	public List<Object[]> getInviteesForPartyMeetings(List<Long> partyMeetingsList);
	
	public List<Object[]> getPublicRepresentativeInviteesForPartyMeetings(List<Long> partyMeetingsList);
	
	public List<Object[]> getCommitteeMemberInviteesForPartyMeetings(List<Long> partyMeetingsList);
	
	public List<Long> getInvitedCadreIdsByPartyMeetingId(Long partyMeetingId);
	
	
	public List<Object[]> getInvitedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO);
	public List<Object[]> getInvitteeAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseInvitedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO, String status);
	public List<Object[]> getDistrictWiseInvitteeAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO, String status);
	
	public List<Object[]> getInvitedCadreCountForMeetingsByCommitteeWise(PartyMeetingsInputVO inputVO);
	public List<Object[]> getInvitteeAttendedCadreCountForMeetingsByCommitteeWise(PartyMeetingsInputVO inputVO);
	public List<Object[]> getInvitedCadreCountForMeetingsByPublicRepresentativeWise(PartyMeetingsInputVO inputVO);
	public List<Object[]> getInvitteeAttendedCadreCountForMeetingsByPublicRepresentativeWise(PartyMeetingsInputVO inputVO);
	public List<Long> getInvitedMemberCadreId(PartyMeetingsInputVO inputVO);
	public List<Long> getAttendedMemberCadreId(PartyMeetingsInputVO inputVO);
	//santosh
	public List<Object[]> getDistrictWiseInvitteeAttendedCountForPartyMeetingId(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseAttendedCountForPartyMeetingId(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseInvitedCountForPartyMeetingId(PartyMeetingsInputVO inputVO);
	public List<Object[]> getCommitteeWiseInvitedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getCommitteeWiseInvitteeAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getPublicRepresentativeWiseInvitedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getPublicRepresentativeWiseInvitteeAttendedCadreCountForMeetings(PartyMeetingsInputVO inputVO);
	
	public List<Object[]> getDistrictWiseInvitedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseInvitteeAttendedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseAttendedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO);
	public List<Object[]> getInvitedPartyMeetingdtlsForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseAttendedCountForPartyMeetingIdForWithoutSession(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseInvitteeAttendedCountForPartyMeetingIdForWithoutSession(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseAttendedCountForWithoutSessionPartyMeetingId(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseInvitteeAttendedCountForWithoutSessionPartyMeetingId(PartyMeetingsInputVO inputVO);
	public List<Object[]> getWithoutSessionDistrictWiseInvitteeAttendedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO);
	public List<Object[]> getWithoutSessionDistrictWiseAttendedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO);
	public List<Object[]> getWithoutCommitteeWiseInvitteeAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getWithoutSessionPublicRepresentativeWiseInvitteeAttendedCadreCountForMeetings(PartyMeetingsInputVO inputVO);
	public List<Object[]> getObsentReasonList(Long  partyMeetingId);
	public List<Object[]> getPartyMeetingInviteeSummary(List<Long> cadreIds);
	
	
	public List<Object[]> meetingWiseInviteeCadreList(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet);
	public List<PartyMeetingInvitee> getInviteesByPartyMeetingAndCadreId(Long partyMeetingId,Long tdpCadreId);
	public List<Object[]> plannedMeetingWiseInviteeCadreList(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet);
	public List<Object[]> meetingWiseInviteeCadreListForLevelWise(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList);
	public List<Object[]> getCommitteeWiseInvitedCadreCountForMultiLocationMeeting(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList,String isRequired);
	public List<Object[]> getPublicRepresentativeWiseInvitedCadreCountForMultiLocationWiseMeeting(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList,String isRequired);
	public List<Object[]> getInvitteeDetails(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,
			Date fromDate,Date toDate,Long stateId,List<Long> levelIdsList,Long partyMeetngGrpId,Long partyMeetingId,Long locationId);
	
	public List<Object[]> getPartyMeetingInviteeDetaisByPartyMeetingId(Long meetingId);
	
	public int updateAbsenteeRemark(Long cadreId,Long partyMeetingId,String comment);
	public List<Object[]> getPartyMeetingInvitteesMembershipNo(Long partyMeetingId);
	public List<Object[]> getInviteeList(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate);
	public List<Object[]> getInviteeListForMeeting(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate);
	public List<Object[]> getLocationWiseStateMeetingInvitees(List<Long> locationValues,Long locationTypeId,Date fromDate,Date toDate,Long partyMeetingMainTypeid,Long partyMeetingTypeId);
	public List<Object[]> getLocationWiseMeetingInviteeMembers(List<Long> locationValues,Long locationTypeId,Date fromDate,Date toDate,Long partyMeetingMainTypeid,Long partyMeetingTypeId,Long partyMeetingId,Set<Long> inviteeIds);
	public List<Object[]> getTdpCadreIdsByMeetingId(Long partyMeetingId);
}
