package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteTrackingDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMinuteTracking;

public class PartyMeetingMinuteTrackingDAO extends GenericDaoHibernate<PartyMeetingMinuteTracking, Long> implements IPartyMeetingMinuteTrackingDAO 
{

	public PartyMeetingMinuteTrackingDAO() {
		super(PartyMeetingMinuteTracking.class);
	}
	
	public List<Object[]> getPartyMeetingMomComments(Long partyMeetingMinuteId) {
		StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select date(model.insertedTime),model.comment from " +
		 		         " PartyMeetingMinuteTracking model " +
		 		         " where model.partyMeetingMinuteId=:partyMeetingMinuteId " +
		 		         " order by date(model.insertedTime)");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingMinuteId", partyMeetingMinuteId);
		return query.list();
	}
}
