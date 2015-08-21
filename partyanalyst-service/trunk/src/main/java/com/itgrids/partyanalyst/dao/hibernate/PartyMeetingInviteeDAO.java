package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.model.PartyMeetingInvitee;

public class PartyMeetingInviteeDAO extends GenericDaoHibernate<PartyMeetingInvitee,Long> implements IPartyMeetingInviteeDAO{

	public PartyMeetingInviteeDAO()
	{
		super(PartyMeetingInvitee.class);
	}
	
	public List<Object[]> getPartyMeetingsInvitationsDetailsByCadreIds(List<Long> tdpCadreIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingLevel.partyMeetingLevelId, PMI.partyMeeting.partyMeetingLevel.level," +
				" PMI.partyMeeting.partyMeetingType.partyMeetingTypeId,  PMI.partyMeeting.partyMeetingType.type, count(PMI.tdpCadreId)  from PartyMeetingInvitee PMI  ");
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			queryStr.append(" where PMI.tdpCadreId in (:tdpCadreIdsList) ");
		queryStr.append(" group by PMI.partyMeeting.partyMeetingType.partyMeetingTypeId order by PMI.partyMeeting.partyMeetingLevel.partyMeetingLevelId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	public List<Object[]> getPartyMeetingsInvitationDetlsByCadreIds(List<Long> tdpCadreIdsList,Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		boolean isSetWhere = false;
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingId,PMI.partyMeeting.meetingName, count(distinct PMI.tdpCadreId)  from PartyMeetingInvitee PMI ");
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L){
			queryStr.append(" where PMI.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
			isSetWhere = true;
		}
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
		{
			if(!isSetWhere)
				queryStr.append(" where PMI.tdpCadreId in (:tdpCadreIdsList) ");
			else
				queryStr.append(" and PMI.tdpCadreId in (:tdpCadreIdsList) ");
		}

		queryStr.append(" group by PMI.partyMeeting.partyMeetingId order by PMI.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L)
			query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getPartyMeetingInvittees(Long partyMeetingId)
	{
		Query query = getSession().createQuery("SELECT DISTINCT model.tdpCadre.memberShipNo FROM PartyMeetingInvitee model where model.partyMeeting.partyMeetingId = :partyMeetingId");
		query.setParameter("partyMeetingId",partyMeetingId);
		return query.list();
	}
	
	
	/*
	 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * @since 21-AUG-2015
	 * This DAO Call is to Get Total Invitees Of Meetings
	 * @param List<Long> partyMeetingIds
	 * @return List<Object[]>  of PartyMeetingId, LocationValue, Count of Invitee Attendents
	 */
	public List<Object[]> getPartyMeetingInviteesForMeetings(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId," +
				" model.partyMeeting.locationValue," +
				" count(model.tdpCadre.tdpCadreId)" +
				" from PartyMeetingInvitee model" +
				" where " +
				" model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" group by model.partyMeeting.partyMeetingId " +
				" order by model.partyMeeting.partyMeetingId desc ");
		
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
}
