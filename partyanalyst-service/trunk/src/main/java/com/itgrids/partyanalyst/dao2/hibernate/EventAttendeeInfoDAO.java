package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventAttendeeInfoDAO;
import com.itgrids.partyanalyst.model.EventAttendeeInfo;

public class EventAttendeeInfoDAO extends GenericDaoHibernate<EventAttendeeInfo, Long> implements IEventAttendeeInfoDAO{

	public EventAttendeeInfoDAO() {
		super(EventAttendeeInfo.class);
	}
	
	public Integer deleteEventAttendeeInfoRecords(Date fromDate,Date toDate,List<Long> allEventIds)
	{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" delete from EventAttendeeInfo model " +
				  " where  model.fromDate =:fromDate and model.toDate =:toDate ");
        
		if(allEventIds !=null && allEventIds.size() > 0){
			sb.append(" and model.eventId in  (:eventIds) ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(allEventIds !=null && allEventIds.size() > 0){
			query.setParameterList("eventIds", allEventIds);
		}
		query.setDate("fromDate",fromDate );
		query.setDate("toDate",toDate );
		
		return query.executeUpdate();
	}
	
	public List<Object[]> getDistricts(List<Long> locationValues,Long reportLevel,Date startDate,Date endDate,Set<Long> eventIds) 
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.locationValue,");
		if(reportLevel.longValue() == 4l)
		{
			str.append(" c.district.districtId from EventAttendeeInfo model ,Constituency c where c.constituencyId = model.locationValue");	
		}
		if(reportLevel.longValue() == 3l)
		{
			str.append(" d.districtId from EventAttendeeInfo model ,District d where d.districtId = model.locationValue");	
		}
		str.append("  and model.reportLevelId = :reportLevel" +
				   " and model.locationValue in(:locationValues) " +
				   " and model.eventId in (:eventIds) " +
				   " and model.fromDate = :startDate and model.toDate =:endDate ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("locationValues", locationValues);
		query.setParameter("reportLevel", reportLevel);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.setParameterList("eventIds", eventIds);
		return query.list();	
	}
	public Integer updateState(List<Long> locationValues,Long reportLevelId,Long stateId)
	{
		Query query = getSession().createQuery("update EventAttendeeInfo model  set model.stateId =:stateId where model.reportLevelId = :reportLevelId and model.locationValue in(:locationValues) ");
		query.setParameter("reportLevelId", reportLevelId);
		query.setParameterList("locationValues", locationValues);
		query.setParameter("stateId", stateId);
		return query.executeUpdate();
	}
	
	public List<Object[]> getEventDataByReportLevelId(Long reportLevelId,Long stateId,List<Long> totalEventIds,Date startDate,Date endDate)
	{
		StringBuilder sbS = new StringBuilder();
		StringBuilder sbM = new StringBuilder();
		
		
		sbS.append(" select model.locationValue,sum(model.invitees),sum(model.noninvitees) ");
		sbM.append(" from   EventAttendeeInfo model ");
		
		if(reportLevelId == 4){
			sbM.append(",Constituency model2 where model.locationValue = model2.constituencyId " );
			sbS.append(" ,model2.name");
		}	
		if(reportLevelId == 3){
			sbM.append(" ,District model2 where model.locationValue = model2.districtId" );
			sbS.append(" ,model2.districtName");
		}
		sbS.append(" ,model.insertedTime ");
		
		sbM.append(" and  model.reportLevelId = :reportLevelId " +
				   " and  model.eventId in(:totalEventIds)  ");
		
		if(stateId == 1l && reportLevelId == 3){
			sbM.append(" and model.locationValue between 11 and 23 ");
		}else if(stateId == 36l && reportLevelId == 3){
			sbM.append(" and model.locationValue between 1 and 10 ");
		}
		
		if(stateId == 1l && reportLevelId == 4){
			sbM.append(" and model2.district.districtId between 11 and 23 ");
		}
		else if(stateId == 36l && reportLevelId == 4){
			sbM.append(" and  model2.district.districtId between 1 and 10 ");
		}	
		
		sbM.append(" and model.fromDate = :startDate and model.toDate =:endDate ");
		sbM.append(" group by model.locationValue");
		
		StringBuilder sq = new StringBuilder();
		
		sq.append(sbS.toString()).append(sbM.toString());
		
		Query query = getSession().createQuery(sq.toString());
		query.setParameter("reportLevelId", reportLevelId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		query.setParameterList("totalEventIds", totalEventIds);
		return query.list();
	}
}

