package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingMinute;
import com.itgrids.partyanalyst.model.UserAddress;

public interface IPartyMeetingMinuteDAO extends GenericDao<PartyMeetingMinute,Long>{
	public List<Object[]> getPartyMeetingsMinutsDetlsByCadreIds(Long partyMeetingTypeId);
	public List<Object[]> getMinuteDetailsForAMeeting(Long partyMeetingId,String accessType,List<Long> accessValue);
	public Integer updateMeetingPoint(Long minuteId,String minuteText,Long updatedBy,Date updateTime,Long levelId,Long levelValue,String isActionable,Long statusId,UserAddress userAddressId,Long isGovtParty);	
	public Integer deleteMeetingMinutePoint(Long minuteId,Long updatedBy,Date updateTime);
	public List<Object[]> getMinuteDetailsForMeetings(List<Long> partyMeetingIds);
	public List<Long> getMOMHavingMeetings(List<Long> partyMeetingIds);
	public List<Object[]> getPartyMeetingMinuteRetrieveDetails(Long minuteId);
}
