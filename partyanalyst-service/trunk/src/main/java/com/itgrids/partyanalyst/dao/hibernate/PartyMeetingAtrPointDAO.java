package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointDAO;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPoint;

public class PartyMeetingAtrPointDAO extends GenericDaoHibernate<PartyMeetingAtrPoint,Long> implements IPartyMeetingAtrPointDAO{

	public PartyMeetingAtrPointDAO()
	{
		super(PartyMeetingAtrPoint.class);
	}
	
	public List<Object[]> getPartyMeetingsATRPointsDetls(Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingId,PMI.partyMeeting.meetingName, count(distinct PMI.partyMeetingAtrPointId)  from PartyMeetingAtrPoint PMI where " +
				"  PMI.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
		queryStr.append(" group by PMI.partyMeeting.partyMeetingId order by PMI.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
		return query.list();
	}
	
	public List<Object[]> getAtrDetailsForAMeeting(Long partyMeetingId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeetingAtrPointId,model.partyMeetingId,model.request,model.actionTaken,model.requestFrom, " +
				" model.locationScopeId,model.locationValue, " +
				" model.raisedBy,model.insertedBy.userId,model.insertedBy.firstName,model.updatedBy.userId,model.updatedBy.firstName," +
				"model.insertedTime,model.updatedTime  " +
				" from PartyMeetingAtrPoint model where " +
				"  model.partyMeeting.partyMeetingId=:partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingId", partyMeetingId);
		return query.list();
	}
	
}
