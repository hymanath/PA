package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPoint;

public interface IPartyMeetingAtrPointDAO extends GenericDao<PartyMeetingAtrPoint,Long>{
	public List<Object[]> getPartyMeetingsATRPointsDetls(Long partyMeetingTypeId);
	public List<Object[]> getAtrDetailsForAMeeting(Long partyMeetingId);
	public Integer updateMeetingAtrPoint(Long atrId,String request,String actionTaken,String raisedBy,Long updatedBy,Date updatedTime,Long locatoionId);
	public Integer deleteMeetingAtrPoint(Long atrId,Long updatedBy,Date updatedTime);
	public List<Object[]> getAtrPointsOfMeetings(List<Long> partyMeetingIds);
	public List<Long> getAtrHavingMeetings(List<Long> partyMeetingIds);
	public List<Object[]> getAtrDetailsForMeetingsList(List<Long> partyMeetingIdsList);
}
