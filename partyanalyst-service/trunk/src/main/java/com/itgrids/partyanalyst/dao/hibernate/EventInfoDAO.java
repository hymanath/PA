package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventInfoDAO;
import com.itgrids.partyanalyst.model.EventInfo;

public class EventInfoDAO extends GenericDaoHibernate<EventInfo, Long> implements IEventInfoDAO{

	public EventInfoDAO() {
		super(EventInfo.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getEventInfo(Long reportLevelId)
	{
		Query query = getSession().createQuery("select model.eventInfoId,model.locationValue,model.eventId,(model.date) from EventInfo model where model.reportLevelId = :reportLevelId");
		query.setParameter("reportLevelId", reportLevelId);
		return query.list();
	}
	
	public List<Long> getEventIds(Long reportLevelId,Date currentDate)
	{
		Query query = getSession().createQuery("select distinct model.event.eventId from EventInfo model where model.reportLevelId = :reportLevelId and date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setDate("currentDate", currentDate);
		return query.list();
	}
	
	public Integer deleteEventInfo(Long reportLevelId,List<Long> eventIds)
	{
		Query query = getSession().createQuery("delete from EventInfo model where model.reportLevelId = :reportLevelId and model.event.eventId in(:eventIds) ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameterList("eventIds", eventIds);
		return query.executeUpdate();
	}
	
	public List<Object[]> getEventDataByReportLevelId(Long reportLevelId,Long eventId)
	{
		Query query = getSession().createQuery("select model.eventInfoId,model.locationValue,model.eventId,model.totalInvitees,model.invitees,model.noninvitees from EventInfo model where model.reportLevelId = :reportLevelId" +
				" and model.eventId = :eventId");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("eventId", eventId);
		return query.list();
	}

	
	public List<Object[]> getDistricts(List<Long> locationValues,Long reportLevel) 
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.locationValue,");
		if(reportLevel.longValue() == 4l)
		{
			str.append(" c.district.districtId from EventInfo model ,Constituency c where c.constituencyId = model.locationValue");	
		}
		if(reportLevel.longValue() == 3l)
		{
			str.append(" d.districtId from EventInfo model ,District d where d.districtId = model.locationValue");	
		}
		str.append("  and model.reportLevelId = :reportLevelId" +
				" and model.locationValue in(:locationValues)");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("locationValues", locationValues);
		query.setParameter("reportLevel", reportLevel);
		return query.list();	
	}
}
