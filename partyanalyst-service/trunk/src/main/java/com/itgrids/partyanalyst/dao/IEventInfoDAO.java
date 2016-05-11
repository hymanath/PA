package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventInfo;

public interface IEventInfoDAO extends GenericDao<EventInfo, Long>{
	public List<Object[]> getEventInfo(Long reportLevelId);

	public Integer deleteEventInfo(Long reportLevelId,List<Long> eventIds);
	
	public List<Long> getEventIds(Long reportLevelId,Date currentDate);
	public List<Object[]> getDistricts(List<Long> locationValues,Long reportLevel) ;
	public Integer updateState(List<Long> locationValues,Long reportLevelId,Long stateId);
	public List<Object[]> getEventInfoForState(Long reportLevelId,Long stateId);
	public List<Object[]> getEventDataByReportLevelId(Long reportLevelId,List<Long> eventIds,Long stateId,Date startDate,Date endDate);
	public List<Object[]> getEventDataByReportLevelId(Long reportLevelId,Long eventId,Long stateId,List<Long> subeventIds,Date startDate,Date endDate);
	
	public List<Long> getRequiredEventInfoIds(Long reportLevelId,Date startDate,Date endDate,List<Long> eventIds);
	public Integer deleteEventInfoRecords(List<Long> eventInfoIds);
	
}
