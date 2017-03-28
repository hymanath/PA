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
		Query query = getSession().createQuery("select model.eventInfoId,model.locationValue,model.eventId,date(model.date) from EventInfo model where model.reportLevelId = :reportLevelId");
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
	
	public List<Long> getRequiredEventInfoIds(Long reportLevelId,Date startDate,Date endDate,List<Long> eventIds)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.eventInfoId " +
				"   from   EventInfo model " +
				"   where  model.reportLevelId = :reportLevelId and " +
				"          model.date between :startDate and :endDate");
		
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("reportLevelId",reportLevelId );
		query.setDate("startDate",startDate );
		query.setDate("endDate",endDate );
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds",eventIds );
		}
		return query.list();
	}
	
	public Integer deleteEventInfo(Long reportLevelId,List<Long> eventIds)
	{
		Query query = getSession().createQuery("delete from EventInfo model where model.reportLevelId = :reportLevelId and model.event.eventId in(:eventIds) ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameterList("eventIds", eventIds);
		return query.executeUpdate();
	}
	
	public Integer deleteEventInfoRecords(List<Long> eventInfoIds)
	{
		Query query = getSession().createQuery("delete from EventInfo model where  model.eventInfoId in(:eventInfoIds) ");
		query.setParameterList("eventInfoIds", eventInfoIds);
		return query.executeUpdate();
	}
	
	public List<Object[]> getEventDataByReportLevelId(Long reportLevelId,Long eventId,Long stateId,List<Long> subeventIds,Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.locationValue,sum(model.invitees),sum(model.noninvitees) from EventInfo model ");
		
		if(reportLevelId == 4)
			str.append(",Constituency model2 " );
		
		str.append(" where model.reportLevelId = :reportLevelId " +
				" and model.event.parentEventId = :eventId and model.event.eventId in(:subeventIds) and model.event.isVisible = 'Y' ");
		
		if(stateId == 1l && reportLevelId == 3)
		str.append(" and model.locationValue between 11 and 23 ");
		else if(stateId == 36l && reportLevelId == 3)
			str.append(" and model.locationValue between 1 and 10 ");
		
		if(stateId == 1l && reportLevelId == 4)
			str.append(" and model.locationValue = model2.constituencyId and model2.district.districtId between 11 and 23 ");
		else if(stateId == 36l && reportLevelId == 4)
			str.append(" and model.locationValue = model2.constituencyId and model2.district.districtId between 1 and 10 ");
		
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.date) = :startDate group by model.locationValue"); 
			else
			str.append(" and date(model.date) >= :startDate and date(model.date) <= :endDate group by model.locationValue"); 
		}
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("reportLevelId", reportLevelId);
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			query.setDate("startDate", startDate);
			else
				{
					query.setDate("startDate", startDate);
					query.setDate("endDate", endDate);	
				}
		}
		query.setParameter("eventId", eventId);
		query.setParameterList("subeventIds", subeventIds);
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
		str.append("  and model.reportLevelId = :reportLevel" +
				" and model.locationValue in(:locationValues)");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("locationValues", locationValues);
		query.setParameter("reportLevel", reportLevel);
		return query.list();	
	}
	
	public Integer updateState(List<Long> locationValues,Long reportLevelId,Long stateId)
	{
		Query query = getSession().createQuery("update EventInfo model  set model.stateId =:stateId where model.reportLevelId = :reportLevelId and model.locationValue in(:locationValues) ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameterList("locationValues", locationValues);
		query.setParameter("stateId", stateId);
		return query.executeUpdate();
	}
	
	public List<Object[]> getEventInfoForState(Long reportLevelId,Long stateId)
	{
		Query query = getSession().createQuery("select model.eventInfoId,model.stateId,model.eventId,date(model.date) from EventInfo model where model.reportLevelId = :reportLevelId" +
				" and model.stateId = :stateId");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameter("stateId", stateId);
		
		return query.list();
	}
	
	public List<Object[]> getEventDataByReportLevelId(Long reportLevelId,List<Long> eventIds,Long stateId,Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.eventId");
		str.append(",model.invitees,model.noninvitees");
		str.append(" from EventInfo model where model.reportLevelId = :reportLevelId " +
				" and model.eventId in(:eventIds)");
		
		if(stateId > 0)
		str.append(" and  model.stateId = :stateId");	
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.date) = :startDate group by model.locationValue,model.event.eventId,date(model.date)"); 
			else
			str.append(" and date(model.date) >= :startDate and date(model.date) <= :endDate group by model.locationValue,model.eventId,date(model.date) "); 
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("reportLevelId", reportLevelId);
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			query.setDate("startDate", startDate);
			else
			{
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);	
			}
		}
		query.setParameterList("eventIds", eventIds);
		if(stateId > 0)
		query.setParameter("stateId", stateId);
		return query.list();
	}
}
