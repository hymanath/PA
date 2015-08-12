package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;

public class PartyMeetingMinuteDAO extends GenericDaoHibernate<PartyMeetingMinute,Long> implements IPartyMeetingMinuteDAO{

	public PartyMeetingMinuteDAO()
	{
		super(PartyMeetingMinute.class);
	}
	
	public List<Object[]> getPartyMeetingsMinutsDetlsByCadreIds(Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingId,PMI.partyMeeting.meetingName, count(distinct PMI.partyMeetingMinuteId)  from PartyMeetingMinute PMI where " +
				"  PMI.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
		queryStr.append(" group by PMI.partyMeeting.partyMeetingId order by PMI.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
		return query.list();
	}
}
