package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewDistrictConstituencyDAO;
import com.itgrids.partyanalyst.model.NewDistrictConstituency;

public class NewDistrictConstituencyDAO extends GenericDaoHibernate<NewDistrictConstituency, Long> implements INewDistrictConstituencyDAO {
	public NewDistrictConstituencyDAO() {
		super(NewDistrictConstituency.class);
	}
	
	
   public List<String> getConstiDetailsOfDistrict(Long districtId){		
		Query query = getSession().createQuery("select distinct model.constituency.name from NewDistrictConstituency model where model.district.districtId = :districtId ");
		query.setParameter("districtId",districtId );
		return query.list();
		
	}

	public List<Object[]> getConstituencyListForDistrict(Long districtId)	
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituency.constituencyId ,model.constituency.name from NewDistrictConstituency model where model.district.districtId = :districtId " +
				" order by model.constituency.name asc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("districtId", districtId);
		return query.list();
	}
}
