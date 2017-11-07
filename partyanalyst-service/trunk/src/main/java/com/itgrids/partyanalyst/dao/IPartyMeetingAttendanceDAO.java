package com.itgrids.partyanalyst.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
import com.itgrids.partyanalyst.model.PartyMeetingAttendance;

public interface IPartyMeetingAttendanceDAO extends GenericDao<PartyMeetingAttendance,Long>{
	public List<Object[]> getPartyMeetingsAttendenceDetailsByCadreId(List<Long> tdpCadreIdsList,Date toDayDate);
	public List<Object[]> getTotalAttendedDetailsForCadreIds(List<Long> tdpCadreIdsList,Long partyMeetingTypeId,Date todayDate);
	public List<Object[]> getAbsentMemberDeails(List<Long> tdpCadreIdsList ,Long partyMeetingTypeId);
	public List<Object[]> getAttendenceForCadre(Long tdpCadreId,Date todayDate);
	
	public List<Object[]> getTotalAttendentsOfMeetings(List<Long> partyMeetingIds);
	public List<Object[]> getInviteesAttendedCountOfMeetings(List<Long> partyMeetingIds);
	
	public List<Object[]> getAttendanceForMeetings(List<Long> partyMeetingsList);
	
	public List<Object[]> getCandidateAttendanceForMeetings(List<Long> partyMeetingsList);
	
	public List<Object[]> getCommitteeMemberAttendanceForMeetings(List<Long> partyMeetingsList);
	
	public List<Long> getConductedMeetings(List<Long> partyMeetingsList);
	public List<Object[]> getSpecialMeetingsSessionWiseAttendence(List<Long> partyMeetingIdsList,Long mainTypeId);
	public List<Object[]> getNoSesstionSpecialMeetingsSessionWiseAttendence(List<Long> partyMeetingIdsList,Long mainTypeId);
	public List<Object[]> getMontlyWiseMeetingsDetails(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate,List<String> searchDatesList);
	public BigInteger getLocationWiseTotalMeetingsCount(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate);
	public List<Long> getAttendedCadreIdsByPartyMeetingId(Long partyMeetingId);
	
	public List<Object[]> getAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO);
	public List<Object[]> getAttendedCadreCountForMeetingsByCommitteeWise(PartyMeetingsInputVO inputVO);
	public List<Object[]> getAttendedCadreCountForMeetingsByByPublicRepresentativeWise(PartyMeetingsInputVO inputVO);
	public List<Long> getAttendedMemberCadreId(PartyMeetingsInputVO inputVO);
	public List<Object[]> getCommitteeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getPublicRepresentativeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getWithoutSessionCommitteeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getWithioutPublicRepresentativeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getPartyMeetingAttendanceSummary(List<Long> cadreIds);  
	public List<Object[]> getAttendedCadresMeetingWise(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet);
	public List<Object[]> getAttendedCadresInMeeting(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet);
	public List<Object[]> getAttendedCadresMeetingWiseForLevel(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList);
	public List<Object[]> getAttendedCadresOfCommitteeMeetingWiseForLevel(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList,String isRequired);
	public List<Object[]> getAttendedCadresOfPublicRepresentativeMeetingWiseForLevel(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList,String isRequired);
	public Long getConductedCount(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,
			Date fromDate,Date toDate,Long stateId,Long partyMeetingLevelId,Long partyMeetngGrpId);
	public List<Object[]> getAttendeeDetails(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,
			Date fromDate,Date toDate,Long stateId,Long partyMeetingLevelId,Long partyMeetngGrpId);
	public List<Object[]> getPartyLevelIdWiseMeetingAttendanceDetails(Long partyMeetnMainTypId,Long  userAccessLevelId,Set<Long> userAccessLevelValues, 
			Date fromDateStr,Date toDateStr, Long stateId, List<Long> levelIdsList ,Long  partyMeetngGrpId,Long sessionTypId,Long partyMeetingId,Long locationId);
	public List<Object[]> getMeetingsBasedConductedCountForDiffLevels(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList);
	
	public List<String> getPartyMeetingInviteesDetailsAttendence(Long partyMeetingId,Long sessionId);
	public List<Object[]> getAttendedList(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate);
	public List<Object[]> getAttendedListForMeeting(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate);
	public List<Object[]> getLocationWiseStateMeetingAttendees(List<Long> locationValues,Long locationTypeId,Date fromDate,Date toDate,Long partyMeetingMainTypeid,Long partyMeetingTypeId,Long partyMeetingId,Set<Long> inviteeIds,Long sessionTypeId);
	public List<Object[]> getNonInviteeAttendanceCountByMeetingId(List<Long> partyMeetingId);
}
