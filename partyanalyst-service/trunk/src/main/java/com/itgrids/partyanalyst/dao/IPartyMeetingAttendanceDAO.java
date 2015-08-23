package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingAttendance;

public interface IPartyMeetingAttendanceDAO extends GenericDao<PartyMeetingAttendance,Long>{
	public List<Object[]> getPartyMeetingsAttendenceDetailsByCadreId(List<Long> tdpCadreIdsList,Date toDayDate);
	public List<Object[]> getTotalAttendedDetailsForCadreIds(List<Long> tdpCadreIdsList,Long partyMeetingTypeId);
	public List<Object[]> getAbsentMemberDeails(Long partyMeetingTypeId);
	public List<Object[]> getAttendenceForCadre(Long tdpCadreId,Date todayDate);
	
	public List<Object[]> getTotalAttendentsOfMeetings(List<Long> partyMeetingIds);
	public List<Object[]> getInviteesAttendedCountOfMeetings(List<Long> partyMeetingIds);
}
