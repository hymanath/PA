package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

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

}
