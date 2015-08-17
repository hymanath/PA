package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingMinute;

public interface IPartyMeetingMinuteDAO extends GenericDao<PartyMeetingMinute,Long>{
	public List<Object[]> getPartyMeetingsMinutsDetlsByCadreIds(Long partyMeetingTypeId);
	public List<Object[]> getMinuteDetailsForAMeeting(Long partyMeetingId);
	public Integer updateMeetingPoint(Long minuteId,String minuteText,Long updatedBy,Date updateTime);	
	public Integer deleteMeetingMinutePoint(Long minuteId,Long updatedBy,Date updateTime);
}
