package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingSessionDAO;
import com.itgrids.partyanalyst.model.PartyMeetingSession;

public class PartyMeetingSessionDAO extends GenericDaoHibernate<PartyMeetingSession, Long> implements IPartyMeetingSessionDAO{

	public PartyMeetingSessionDAO() {
		super(PartyMeetingSession.class);
			
	}

	public List<Object[]> getSessionDetailsForPartyMeetings(Set<Long> partyMeetingsIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  distinct model.partyMeetingId, " +//0
		        " model.partyMeetingSessionId, " +//1
		        " model.sessionType.type, '' , " +//2,3
		        " model.startTime, " +//4
		        " model.endTime," +//5
		        " model.lateTime," +//6
		        " model.sessionType.startTime," +//7
		        " model.sessionType.endTime," +//8
		        " model.sessionType.lateTime, "+//9
		        " model.sessionType.sessionTypeId," +//10
		        " model.attendanceStartTime   " +//11
		        " from PartyMeetingSession model where model.isDeleted='N' and " +
				" model.partyMeetingId in (:partyMeetingsIds)  order by model.orderNo asc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("partyMeetingsIds", partyMeetingsIds);		
		return query.list();
	}
	
	public List<Object[]> getSessionDetailsForPartiMeetings(Set<Long> partyMeetingsTypeIds,List<Long> partyMeetingsIds){
		StringBuilder queryStr = new StringBuilder();
		if(partyMeetingsTypeIds != null && partyMeetingsTypeIds.size()>0){
			queryStr.append(" select  distinct pm.partyMeetingId, model.partyMeetingSessionId, model.sessionType.type, '' , " +
					" model.startTime, model.endTime,model.lateTime,model.sessionType.startTime,model.sessionType.endTime,model.sessionType.lateTime," +
					" pm.partyMeetingTypeId, pm.meetingName " +
					"  from PartyMeetingSession model " +
					" left join model.partyMeeting pm " +
					" where model.isDeleted='N' and " +
					" pm.partyMeetingTypeId in (:partyMeetingsTypeIds)  ");
			if(partyMeetingsIds != null && partyMeetingsIds.size()>0){
				queryStr.append(" and pm.partyMeetingId in (:partyMeetingIds) ");
			}
			queryStr.append("  order by  model.orderNo asc ");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameterList("partyMeetingsTypeIds", partyMeetingsTypeIds);
			if(partyMeetingsIds != null && partyMeetingsIds.size()>0){
				query.setParameterList("partyMeetingIds",partyMeetingsIds);
			}
			return query.list();
		}
		return null;
	}
	
	public List<Object[]> getSessionDetailsForPartiMeetings(Set<Long> partyMeetingsTypeIds,List<Long> partyMeetingsIds,Date startDate,Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		if(partyMeetingsTypeIds != null && partyMeetingsTypeIds.size()>0){
			queryStr.append(" select  distinct pm.partyMeetingId, model.partyMeetingSessionId, model.sessionType.type, '' , " +
					" model.startTime, model.endTime,model.lateTime,model.sessionType.startTime,model.sessionType.endTime,model.sessionType.lateTime," +
					" pm.partyMeetingTypeId, pm.meetingName " +
					"  from PartyMeetingSession model " +
					" left join model.partyMeeting pm " +
					" where model.isDeleted='N' and " +
					" pm.partyMeetingTypeId in (:partyMeetingsTypeIds)  ");
			if(partyMeetingsIds != null && partyMeetingsIds.size()>0){
				queryStr.append(" and pm.partyMeetingId in (:partyMeetingIds) ");
			}
			if(startDate != null && endDate != null)
			{
				queryStr.append(" and date(pm.startDate) between :startDate and :endDate ");
			}
			queryStr.append("  order by  model.orderNo asc ");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameterList("partyMeetingsTypeIds", partyMeetingsTypeIds);
			if(partyMeetingsIds != null && partyMeetingsIds.size()>0){
				query.setParameterList("partyMeetingIds",partyMeetingsIds);
			}
			if(startDate != null && endDate != null)
			{
				query.setDate("startDate",startDate);
				query.setDate("endDate",endDate);
			}
			return query.list();
		}
		return null;
	}
	
	public List<Object[]> getPartyMeetingSession(Long partyMeetingId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  distinct model.partyMeeting.partyMeetingId, " +//0
				" model.sessionType.sessionTypeId," +//1
				" model.sessionType.type " +//2
		        " from PartyMeetingSession model where model.isDeleted='N' and " +
				" model.partyMeeting.partyMeetingId = :partyMeetingId  order by model.orderNo asc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingId", partyMeetingId);		
		return query.list();
	}
	public List<Object[]> getLateTimeList(Set<Long> meetingIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct" +
				" model.partyMeeting.partyMeetingId, " +
				" model.partyMeetingSessionId," +
				" model.lateTime " +
				" from PartyMeetingSession model " +
				" where " +
				" model.isDeleted = 'N' " +
				" and model.partyMeeting.partyMeetingId in (:meetingIds) " +
				" order by model.partyMeeting.partyMeetingId,model.orderNo ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("meetingIds", meetingIds);
		return query.list();  
	}
	
}
