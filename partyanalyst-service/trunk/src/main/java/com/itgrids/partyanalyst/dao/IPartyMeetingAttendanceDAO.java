package com.itgrids.partyanalyst.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

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
	
	public List<Object[]> getMontlyWiseMeetingsDetails(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate,List<String> searchDatesList);
	public BigInteger getLocationWiseTotalMeetingsCount(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate);
	public List<Long> getAttendedCadreIdsByPartyMeetingId(Long partyMeetingId);
	
	public List<Object[]> getAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO);
	public List<Object[]> getDistrictWiseAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO);
	public List<Object[]> getAttendedCadreCountForMeetingsByCommitteeWise(PartyMeetingsInputVO inputVO);
	public List<Object[]> getAttendedCadreCountForMeetingsByByPublicRepresentativeWise(PartyMeetingsInputVO inputVO);
	public List<Long> getAttendedMemberCadreId(PartyMeetingsInputVO inputVO);
	public List<Object[]> getSpecialMeetingsSessionWiseAttendence(List<Long> partyMeetingIdsList);
	public List<Object[]> getCommitteeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getPublicRepresentativeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getNoSesstionSpecialMeetingsSessionWiseAttendence(List<Long> partyMeetingIdsList);
	public List<Object[]> getWithoutSessionCommitteeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
	public List<Object[]> getWithioutPublicRepresentativeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO);
}
