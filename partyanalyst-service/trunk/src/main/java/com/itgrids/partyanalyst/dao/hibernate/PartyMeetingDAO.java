package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.model.PartyMeeting;


public class PartyMeetingDAO extends GenericDaoHibernate<PartyMeeting,Long> implements IPartyMeetingDAO{

	public PartyMeetingDAO()
	{
		super(PartyMeeting.class);
	}
	
	public List<Object[]> getAllMeetings(Long meetingType,Long locationLevel,Long meetingLocation){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.partyMeetingType.partyMeetingTypeId,model.partyMeetingType.type, " +
				" model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level, " +
				" model.locationValue, date(model.startTime), date(model.endTime) " +
				"from PartyMeeting model " +
				" wher model.partyMeetingType.partyMeetingTypeId=:meetingType " +
				" and model.partyMeetingLevel.partyMeetingLevelId=:locationLevel ");
		
		if(meetingLocation!=null && meetingLocation>0l){
			sb.append(" and model.meetingAddress.userAddressId=:meetingLocation ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("meetingType", meetingType);
		query.setParameter("locationLevel", locationLevel);
		
		if(meetingLocation!=null && meetingLocation>0l){
			query.setParameter("meetingLocation", meetingLocation);
		}
		
		return query.list();
	}
}
