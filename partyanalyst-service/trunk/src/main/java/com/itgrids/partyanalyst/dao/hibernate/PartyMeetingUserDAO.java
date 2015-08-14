package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingUserDAO;
import com.itgrids.partyanalyst.model.PartyMeetingUser;

public class PartyMeetingUserDAO extends GenericDaoHibernate<PartyMeetingUser,Long> implements IPartyMeetingUserDAO{

	public PartyMeetingUserDAO()
	{
		super(PartyMeetingUser.class);
	}
	
	public List<Object[]> getTheMeetingLevelDetails(Long userId){
		Query query = getSession().createQuery("select model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.partyMeetingLevel.level" +
				" from PartyMeetingUser model where model.user.userId=:userId group by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId ");
		query.setParameter("userId", userId);
		return query.list();
	}
}
