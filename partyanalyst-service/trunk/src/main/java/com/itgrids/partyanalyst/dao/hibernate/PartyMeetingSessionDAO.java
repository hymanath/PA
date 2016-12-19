package com.itgrids.partyanalyst.dao.hibernate;

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
		queryStr.append(" select  distinct model.partyMeetingId, model.partyMeetingSessionId, model.sessionType.type, '' , " +
				" model.startTime, model.endTime,model.lateTime,model.sessionType.startTime,model.sessionType.endTime,model.sessionType.lateTime,model.sessionType.sessionTypeId   from PartyMeetingSession model where model.isDeleted='N' and " +
				" model.partyMeetingId in (:partyMeetingsIds)  order by model.orderNo asc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("partyMeetingsIds", partyMeetingsIds);		
		return query.list();
	}
	
	public List<Object[]> getSessionDetailsForPartiMeetings(Set<Long> partyMeetingsTypeIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  distinct model.partyMeetingId, model.partyMeetingSessionId, model.sessionType.type, '' , " +
				" model.startTime, model.endTime,model.lateTime,model.sessionType.startTime,model.sessionType.endTime,model.sessionType.lateTime," +
				" model.partyMeeting.partyMeetingTypeId, model.partyMeeting.meetingName " +
				"  from PartyMeetingSession model where model.isDeleted='N' and " +
				" model.partyMeeting.partyMeetingTypeId in (:partyMeetingsTypeIds)  order by  model.orderNo asc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("partyMeetingsTypeIds", partyMeetingsTypeIds);		
		return query.list();
	}
	
}
