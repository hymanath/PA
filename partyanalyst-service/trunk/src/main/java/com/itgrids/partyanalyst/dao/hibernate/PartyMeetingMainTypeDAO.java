package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingMainTypeDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMainType;


public class PartyMeetingMainTypeDAO extends GenericDaoHibernate<PartyMeetingMainType, Long> implements IPartyMeetingMainTypeDAO {
	
	public PartyMeetingMainTypeDAO() {
		super(PartyMeetingMainType.class);
	}

	
	 public List<Object[]> getMeetingMainType(){
			StringBuilder sb = new StringBuilder();
			sb.append(" select model.partyMeetingMainTypeId,model.meetingType from  PartyMeetingMainType model ");
			Query query = getSession().createQuery(sb.toString());
			return query.list();

		}

}
