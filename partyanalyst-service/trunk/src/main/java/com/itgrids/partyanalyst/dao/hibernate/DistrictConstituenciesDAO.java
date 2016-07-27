package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;
import com.itgrids.partyanalyst.model.DistrictConstituencies;

public class DistrictConstituenciesDAO extends GenericDaoHibernate<DistrictConstituencies,Long> implements IDistrictConstituenciesDAO{

	public DistrictConstituenciesDAO(){
		super(DistrictConstituencies.class);
	}
	
	public List<Object[]> getConstituenciesOfDistrict(){
		Query query = getSession().createQuery(" select model.district.districtId," +
				" model.district.districtName," +
				" model.constituency.constituencyId," +
				" model.constituency.name" +
				" from DistrictConstituencies model order by model.constituency.name ");
		
		return query.list();
	}
}
