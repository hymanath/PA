package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingUserAccessLevelDAO;
import com.itgrids.partyanalyst.model.PartyMeetingUserAccessLevel;

public class PartyMeetingUserAccessLevelDAO extends GenericDaoHibernate<PartyMeetingUserAccessLevel,Long> implements IPartyMeetingUserAccessLevelDAO{

	public PartyMeetingUserAccessLevelDAO(){
		super(PartyMeetingUserAccessLevel.class);
	}
	
	public List<Object[]> getrAccessLevelsOfUserId(Long userId){
		Query query = getSession().createQuery(" select model.partyMeetingLevel.partyMeetingLevelId," +
				" model.partyMeetingLevel.level" +
				" from PartyMeetingUserAccessLevel model" +
				" where model.user.userId =:userId " +
				" order by model.partyMeetingLevel.partyMeetingLevelId ");
		query.setParameter("userId", userId);
		return query.list();
	}
	
}
