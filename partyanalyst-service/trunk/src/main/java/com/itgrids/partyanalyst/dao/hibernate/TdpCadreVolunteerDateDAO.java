package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerDateDAO;
import com.itgrids.partyanalyst.model.TdpCadreVolunteerDate;



public class TdpCadreVolunteerDateDAO extends GenericDaoHibernate<TdpCadreVolunteerDate, Long> implements ITdpCadreVolunteerDateDAO{

	public TdpCadreVolunteerDateDAO() {
		super(TdpCadreVolunteerDate.class);
	}

	public List<Object[]> getAvailableDatesForVolunteers(List<Long> valenteersIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.tdpCadreVolunteer.tdpCadreVolunteerId, date(model.date) from TdpCadreVolunteerDate model ");
		
		if(valenteersIdsList != null && valenteersIdsList.size()>0)
		{
			queryStr.append(" where model.tdpCadreVolunteer.tdpCadreVolunteerId in (:valenteersIdsList)");
				
		}
		
		queryStr.append(" order by model.tdpCadreVolunteer.tdpCadreVolunteerId asc");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(valenteersIdsList != null && valenteersIdsList.size()>0)
		{
			query.setParameterList("valenteersIdsList", valenteersIdsList);
		}
		
		return query.list();
	}
}
