package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyPresidentsDAO;
import com.itgrids.partyanalyst.model.PartyPresidents;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyPresidentsDAO extends GenericDaoHibernate<PartyPresidents, Long> implements IPartyPresidentsDAO{

	public PartyPresidentsDAO() {
		super(PartyPresidents.class);
	}

	public List<Long> getMobileNumebrsBylocation(Long constituencyId, Long locatonId, String searchType)
	{
		StringBuilder queryStr =new StringBuilder();
		queryStr.append("select model.mobileNo from PartyPresidents model where model.constituency.constituencyId = :constituencyId ");
		if(searchType != null && searchType.equalsIgnoreCase(IConstants.TEHSIL))
		{
			queryStr.append(" and model.tehsil.tehsilId =:locatonId and model.regionScopes.regionScopesId = 5 and model.tehsil.tehsilId != 0");
		}
		else if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" and model.regionScopes.regionScopesId = 4  ");
		}
		queryStr.append(" and model.mobileNo is not null and model.mobileNo !='' ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("constituencyId", constituencyId);
		if(searchType != null && searchType.equalsIgnoreCase(IConstants.TEHSIL))
		{
			query.setParameter("locatonId", locatonId);
		}
		
		return query.list();
	}
}
