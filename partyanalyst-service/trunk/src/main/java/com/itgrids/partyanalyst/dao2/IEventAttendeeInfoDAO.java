package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventAttendeeInfo;

public interface IEventAttendeeInfoDAO extends GenericDao<EventAttendeeInfo, Long>{
	
	public Integer deleteEventAttendeeInfoRecords(Date fromDate,Date toDate,List<Long> allEventIds);
	public List<Object[]> getDistricts(List<Long> locationValues,Long reportLevel,Date startDate,Date endDate,Set<Long> eventIds) ;
	public Integer updateState(List<Long> locationValues,Long reportLevelId,Long stateId);
	public List<Object[]> getEventDataByReportLevelId(Long reportLevelId,Long stateId,List<Long> totalEventIds,Date startDate,Date endDate);
}
