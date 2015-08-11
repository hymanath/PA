package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.model.PartyMeetingAttendance;

public class PartyMeetingAttendanceDAO extends GenericDaoHibernate<PartyMeetingAttendance,Long> implements IPartyMeetingAttendanceDAO{

	public PartyMeetingAttendanceDAO()
	{
		super(PartyMeetingAttendance.class);
	}
	
	public List<Object[]> getPartyMeetingsAttendenceDetailsByCadreId(Long searchTypeId,Long tdpCadreId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select * from PartyMeetingAttendance PMA where PMA.attendance.tdpCadreId =:tdpCadreId ");
		if(searchTypeId != null && searchTypeId.longValue()>0L)
			queryStr.append(" and PMA.partyMeeting.partyMeetingLevelId=:searchTypeId ");
		queryStr.append(" group by PMA.partyMeeting.");
		
		Query query=getSession().createQuery(queryStr.toString());
		query.setParameter("tdpCadreId", tdpCadreId);
		if(searchTypeId != null && searchTypeId.longValue()>0L)
			query.setParameter("searchTypeId", searchTypeId);
		return query.list();
	}
}
