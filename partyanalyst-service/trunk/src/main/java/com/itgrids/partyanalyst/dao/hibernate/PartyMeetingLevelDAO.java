package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingLevelDAO;
import com.itgrids.partyanalyst.model.PartyMeetingLevel;

public class PartyMeetingLevelDAO extends GenericDaoHibernate<PartyMeetingLevel,Long> implements IPartyMeetingLevelDAO{

	public PartyMeetingLevelDAO(){
		super(PartyMeetingLevel.class);
	}
	
	public List<Object[]> getPartyMeetingLevels(){
		Query query = getSession().createQuery(" select model.partyMeetingLevelId, model.level" +
				" from PartyMeetingLevel model");
		return query.list();
	}
	public Long  getMeetingLevelOfCreatedLocationId(Long accessLevelId)
	{
		Query query = getSession().createQuery("select distinct model.regionScopesId from PartyMeetingLevel model " +
				" where model.partyMeetingLevelId =:accessLevelId ");
		query.setParameter("accessLevelId", accessLevelId);
		return (Long)query.uniqueResult();
		
	}
}
