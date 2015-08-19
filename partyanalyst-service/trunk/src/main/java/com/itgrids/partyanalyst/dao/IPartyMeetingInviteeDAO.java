package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingInvitee;

public interface IPartyMeetingInviteeDAO extends GenericDao<PartyMeetingInvitee,Long>{
	
	public List<Object[]> getPartyMeetingsInvitationsDetailsByCadreIds(List<Long> tdpCadreIdsList);
	
	public List<Object[]> getPartyMeetingsInvitationDetlsByCadreIds(List<Long> tdpCadreIdsList,Long partyMeetingTypeId);
	
	public List<String> getPartyMeetingInvittees(Long partyMeetingId);
}
