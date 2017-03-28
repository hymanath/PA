package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerConstituencyDAO;
import com.itgrids.partyanalyst.model.TdpCadreVolunteerConstituency;


public class TdpCadreVolunteerConstituencyDAO extends GenericDaoHibernate<TdpCadreVolunteerConstituency, Long> implements ITdpCadreVolunteerConstituencyDAO{

	public TdpCadreVolunteerConstituencyDAO() {
		super(TdpCadreVolunteerConstituency.class);
	}
	
	
	public List<Object[]> getVolunteerInfoByLocation(Long constituencyId,String searchType)
	{
		StringBuilder queryStr = new StringBuilder();

		queryStr.append(" select model.tdpCadreVolunteer.tdpCadreVolunteerId ," +
				" model.tdpCadreVolunteer.name," +
				" model.tdpCadreVolunteer.mobileNo," +
				" model.tdpCadreVolunteer.address," +
				" model.tdpCadreVolunteer.laptop, " +
				" model.tdpCadreVolunteer.internet , " +
				
				" model.tdpCadreVolunteer.tablet2G, " +
				" model.tdpCadreVolunteer.tablet3G, " +
				
				" model.tdpCadreVolunteer.ipad2G," +
				" model.tdpCadreVolunteer.ipad3G,  " +
				
				" model.tdpCadreVolunteer.smartPhone3G, " +
				" model.tdpCadreVolunteer.smartPhone2G, " +
				
				" model.tdpCadreVolunteer.assignedConstituencyId " +				

				" from TdpCadreVolunteerConstituency model where model.tdpCadreVolunteer.isDeleted = 'N' ");
		
		if(searchType.equalsIgnoreCase("Available"))
		{
			queryStr.append(" and model.tdpCadreVolunteer.assignedConstituencyId is null ");
			if(constituencyId != null && constituencyId.longValue() != 0L)
			{
				queryStr.append(" and model.constituency.constituencyId = :constituencyId ");
			}
		}
		else if(searchType.equalsIgnoreCase("Assigned"))
		{
			queryStr.append(" and model.tdpCadreVolunteer.assignedConstituencyId is not null ");
			if(constituencyId != null && constituencyId.longValue() != 0L)
			{
				queryStr.append(" and model.tdpCadreVolunteer.assignedConstituency.constituencyId = :constituencyId ");
			}
		}
		else if(searchType.equalsIgnoreCase("All"))
		{			
			if(constituencyId != null && constituencyId.longValue() != 0L)
			{
				queryStr.append(" and model.constituency.constituencyId = :constituencyId ");
			}
		}
		
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(constituencyId != null && constituencyId.longValue() != 0L)
		{
			query.setParameter("constituencyId", constituencyId);
		}
		
		return query.list();
	}
	
	public List<Object[]> getconsituencyListById(List<Long> volunteerIds)
	{
		StringBuilder queryStr = new StringBuilder();

		queryStr.append(" select model.tdpCadreVolunteer.tdpCadreVolunteerId , model.constituency.constituencyId, model.constituency.name " +
				"  from TdpCadreVolunteerConstituency model ");
				
		if(volunteerIds != null && volunteerIds.size()>0)
		{
			queryStr.append(" where model.tdpCadreVolunteer.tdpCadreVolunteerId in(:volunteerIds) ");
		}
		queryStr.append(" order by  model.tdpCadreVolunteer.tdpCadreVolunteerId asc ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(volunteerIds != null && volunteerIds.size()>0)
		{
			query.setParameterList("volunteerIds", volunteerIds);
		}
		
		return query.list();
	}
	public List<Object[]> getDeviceInfo(String deviceType,Long constituencyId,String searchType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.tdpCadreVolunteer.tdpCadreVolunteerId ," +
				" model.tdpCadreVolunteer.name," +
				" model.tdpCadreVolunteer.mobileNo," +
				" model.tdpCadreVolunteer.address," +
				" model.tdpCadreVolunteer.laptop, " +
				" model.tdpCadreVolunteer.internet , " +
				
				" model.tdpCadreVolunteer.tablet2G, " +
				" model.tdpCadreVolunteer.tablet3G, " +
				
				" model.tdpCadreVolunteer.ipad2G," +
				" model.tdpCadreVolunteer.ipad3G,  " +
				
				" model.tdpCadreVolunteer.smartPhone3G, " +
				" model.tdpCadreVolunteer.smartPhone2G, " +
				
				" model.tdpCadreVolunteer.assignedConstituencyId " +				

				" from TdpCadreVolunteerConstituency model where model.tdpCadreVolunteer.isDeleted = 'N' ");
		if(deviceType != null && deviceType.trim().equalsIgnoreCase("laptop"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.laptop = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("internet"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.internet = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("lapinternet"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.internet = 'Y' and model.tdpCadreVolunteer.laptop = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("tablet3G"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.tablet3G = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("tablet2G"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.tablet2G = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("ipad2G"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.ipad2G = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("ipad3G"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.ipad3G = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("smartPhone3G"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.smartPhone3G = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("smartPhone2G"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.smartPhone2G = 'Y' ");
		}
		
		if(searchType.equalsIgnoreCase("Available"))
		{
			queryStr.append(" and model.tdpCadreVolunteer.assignedConstituencyId is null ");
			if(constituencyId != null && constituencyId.longValue() != 0L)
			{
				queryStr.append(" and model.constituency.constituencyId = :constituencyId ");
			}
		}
		else if(searchType.equalsIgnoreCase("Assigned"))
		{
			queryStr.append(" and model.tdpCadreVolunteer.assignedConstituencyId is not null ");
			if(constituencyId != null && constituencyId.longValue() != 0L)
			{
				queryStr.append(" and model.tdpCadreVolunteer.assignedConstituency.constituencyId = :constituencyId ");
			}
		}
		else if(searchType.equalsIgnoreCase("All"))
		{			
			if(constituencyId != null && constituencyId.longValue() != 0L)
			{
				queryStr.append(" and model.constituency.constituencyId = :constituencyId ");
			}
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(constituencyId != null && constituencyId.longValue() != 0L)
		{
			query.setParameter("constituencyId", constituencyId);
		}
		
		
		return query.list();
	}
	
}
