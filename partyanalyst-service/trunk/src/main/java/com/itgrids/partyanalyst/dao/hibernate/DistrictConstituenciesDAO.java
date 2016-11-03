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
	public List<Object[]> getConstituenciesOfDistrictStateWise(Long stateId){
		Query query = getSession().createQuery(" select model.district.districtId," +
				" model.district.districtName," +
				" model.constituency.constituencyId," +
				" model.constituency.name" +
				" from DistrictConstituencies model where model.district.state.stateId=:stateId order by model.constituency.name ");
		query.setParameter("stateId", stateId);
		return query.list();
	}
}
