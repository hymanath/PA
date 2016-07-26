package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;
import com.itgrids.partyanalyst.model.PartyMeetingType;

public class PartyMeetingTypeDAO extends GenericDaoHibernate<PartyMeetingType,Long> implements IPartyMeetingTypeDAO{

	public PartyMeetingTypeDAO()
	{
		super(PartyMeetingType.class);
	}
	
	public List<Object[]> getMeetingTypesBasedOnLocationLevel(Long locationLevel){
		Query query = getSession().createQuery("select model.partyMeetingTypeId,model.type from PartyMeetingType model " +
				" where model.isActive = 'Y' " +
				" and model.partyMeetingLevelId=:locationLevel ");
		query.setParameter("locationLevel", locationLevel);
		
		return query.list();
	}
	public List<Object[]> getMeetingTypesBasedOnLocationLevelNew(List<Long> locationLevels){
		Query query = getSession().createQuery("select model.partyMeetingTypeId,model.type from PartyMeetingType model " +
				" where model.isActive = 'Y' " +
				" and model.partyMeetingLevelId in (:locationLevels) ");
		query.setParameterList("locationLevels", locationLevels);
		
		return query.list();
	}
	
}
