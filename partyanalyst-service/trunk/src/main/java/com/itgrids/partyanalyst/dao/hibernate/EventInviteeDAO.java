package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.model.EventInvitee;
import com.itgrids.partyanalyst.utils.IConstants;

public class EventInviteeDAO extends GenericDaoHibernate<EventInvitee, Long> implements IEventInviteeDAO{

	public EventInviteeDAO() {
		super(EventInvitee.class);
	}
	
	public List<Object[]> getEventInviteesCountByLocationType(String locationType,Date currentDate)
	{
		
		StringBuilder str = new StringBuilder();
		
		
		str.append("select model.event.eventId,count(model.eventInviteeId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId");
		str.append(" from EventInvitee model where date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId order by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		return query.list();
	}
	
	
	/*public List<Object[]> getEventInviteesCountForState(String locationType)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(model.eventInviteeId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");
		str.append(" from EventInvitee model");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId order by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}*/
	
	public List<Object[]> getEventInviteesCountByLocationTypeAndEvent(String locationType,Date currentDate,Long eventId)
	{
		
		StringBuilder str = new StringBuilder();
		
		
		str.append("select count(model.eventInviteeId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId");
				
		str.append(" from EventInvitee model where date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and model.event.eventId = :eventId");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.tdpCadre.userAddress.constituency.constituencyId order by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		query.setParameter("eventId", eventId);
		return query.list();
	}
	
	public Long getEventInviteesCountByState(Long stateId,Date currentDate,Long eventId)
	{
		
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.eventInviteeId) ");
		str.append(" from EventInvitee model where date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and model.event.eventId = :eventId" +
				" and model.tdpCadre.userAddress.state.stateId = 1 ");
		if(stateId == 1){
			str.append(" and model.tdpCadre.userAddress.district.districtId <= 10 ");
		}
		else if(stateId == 36){
			str.append(" and model.userAddress.district.districtId >= 11 ");
		}
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		query.setParameter("eventId", eventId);
		return (Long)query.uniqueResult();
	}

	public Long checkIsExistDetails(Long id,Long eventId,String memberType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.eventInviteeId from EventInvitee model where model.eventId =:eventId ");
		if(memberType.trim().equalsIgnoreCase("tdpCadre"))
		{
			queryStr.append(" and model.tdpCadreId =:id ");
		}
		else if(memberType.trim().equalsIgnoreCase("publicRepresentative"))
		{
			queryStr.append(" and model.publicRepresentativeId =:id ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("id", id);
		return (Long) query.uniqueResult(); 
	}
	
	public List<Object[]> checkInvitees(List<Long> tdpCadreIds,Long eventId)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.tdpCadreId,model.eventInviteeId from EventInvitee model where model.eventId =:eventId and model.tdpCadreId in(:tdpCadreIds)");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return  query.list(); 	
	}
	
	public List<Object[]> getInvitationCountforCandidate(Long tdpCadreId)
	{
		Query query = getSession().createQuery(" select model.eventId,model.event.name, count(model.tdpCadreId),model.event.eventType.eventTypeId, model.event.eventType.type  from EventInvitee model where model.tdpCadreId =:tdpCadreId and " +
				" model.event.isActive ='true'  group by  model.eventId  ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return  query.list(); 	
	}
	
	public List<String> getTdpCadreMemberShipsIdsByEvent(Long eventId){
		 Query query = getSession().createQuery(" select model.tdpCadre.memberShipNo from EventInvitee model" +
		 " where model.eventId =:eventId ");
		 
		 query.setParameter("eventId", eventId);
		 return query.list();
	}
	
	public List<Object[]> getEventInviteesCountByLocation(String locationType,Set<Long> locationIds,Long eventId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");	
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId");
		}
		str.append(",count(distinct model.tdpCadre.tdpCadreId) ");
			
		str.append(" from EventInvitee model where model.event.eventId = :eventId ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" and model.tdpCadre.userAddress.constituency.district.districtId in (:locationIds)");
			str.append(" group by model.tdpCadre.userAddress.constituency.district.districtId");
		}	
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId in (:locationIds)");
			str.append(" group by model.tdpCadre.userAddress.constituency.constituencyId");
		}	
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId",eventId);
		query.setParameterList("locationIds",locationIds);
		return query.list();
	}
}
