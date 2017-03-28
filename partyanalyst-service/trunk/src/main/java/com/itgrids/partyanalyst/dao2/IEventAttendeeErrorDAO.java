package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventAttendeeError;

public interface IEventAttendeeErrorDAO extends GenericDao<EventAttendeeError, Long>{
	public List checkEventsyncDataInError(String rfid,String imei,Long eventId,String uniqueKey);
	public Long getPreEntryInvalidCount(Long preEnytryEventId,Date startDate,Date endDate);
}
