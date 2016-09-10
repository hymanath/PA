package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

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
	
	public List<Object[]> getInvitedCadreCountForMeetingsByCommitteeWise(PartyMeetingsInputVO inputVO);
	public List<Object[]> getInvitteeAttendedCadreCountForMeetingsByCommitteeWise(PartyMeetingsInputVO inputVO);
	public List<Object[]> getInvitedCadreCountForMeetingsByPublicRepresentativeWise(PartyMeetingsInputVO inputVO);
	public List<Object[]> getInvitteeAttendedCadreCountForMeetingsByPublicRepresentativeWise(PartyMeetingsInputVO inputVO);
}
