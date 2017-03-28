package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerDAO;
import com.itgrids.partyanalyst.model.TdpCadreVolunteer;



public class TdpCadreVolunteerDAO extends GenericDaoHibernate<TdpCadreVolunteer, Long> implements ITdpCadreVolunteerDAO{

	public TdpCadreVolunteerDAO() {
		super(TdpCadreVolunteer.class);
	}

	public List<Long> checkVolunteerByMobileOrEmail(String mobileNo, String emailId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.tdpCadreVolunteerId from TdpCadreVolunteer model where model.isDeleted = 'N' ");
		
		if(mobileNo.trim().length()>0)
		{
			queryStr.append("  and model.mobileNo like '%"+mobileNo+"%' ");
			
			if(emailId.trim().length() >0)
			{
				queryStr.append("  or model.email like '%"+emailId+"%' ");
			}
			
		}
		else if(emailId.trim().length() >0)
		{
			queryStr.append("  or model.email like '%"+emailId+"%' ");
		}
		
		
		Query query = getSession().createQuery(queryStr.toString());
		
		return query.list();
	}
	
	public Long getDeviceTotalCount(String deviceType,Long constituencyId,String searchType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct model.tdpCadreVolunteerId) from TdpCadreVolunteerConstituency model where model.tdpCadreVolunteer.isDeleted = 'N' ");
		
		if(deviceType != null && deviceType.trim().equalsIgnoreCase("laptop"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.laptop = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("internet"))
		{
			queryStr.append("  and model.tdpCadreVolunteer.internet = 'Y' ");
		}
		else if(deviceType != null && deviceType.trim().equalsIgnoreCase("All"))
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
		
		
		return (Long) query.uniqueResult();
	}
}
