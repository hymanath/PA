package com.itgrids.partyanalyst.dao;

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
}
