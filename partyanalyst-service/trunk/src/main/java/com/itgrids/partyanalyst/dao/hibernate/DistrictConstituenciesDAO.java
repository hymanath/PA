package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;
import com.itgrids.partyanalyst.model.DistrictConstituencies;

public class DistrictConstituenciesDAO extends GenericDaoHibernate<DistrictConstituencies,Long> implements IDistrictConstituenciesDAO{

	public DistrictConstituenciesDAO(){
		super(DistrictConstituencies.class);
	}
	
	public List<Object[]> getConstituenciesOfDistrict(Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.district.districtId," +
				" model.district.districtName," +
				" model.constituency.constituencyId," +
				" model.constituency.name" +
				" from DistrictConstituencies model ");
		if(districtId != null && districtId.longValue()>0l)
		sb.append(" where model.district.districtId = :districtId" );
		sb.append(" order by model.constituency.name ");
		Query query = getSession().createQuery(sb.toString());
		if(districtId != null && districtId.longValue()>0l)
			query.setParameter("districtId", districtId);
		
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
	public List<Long> getConstituenciesOfDistrictById(Long districtId){
		Query query = getSession().createQuery(" select distinct model.constituencyId from DistrictConstituencies model where model.districtId = :districtId ");
		query.setParameter("districtId", districtId);
		return query.list();
	}
	public List<Object[]> getDistrictByConstituenciesIds(Set<Long> constituenciesIds){
		Query query = getSession().createQuery(" select distinct model.district.districtId," +
				" model.district.districtName,model.constituency.constituencyId " +
				" from DistrictConstituencies model where model.constituency.constituencyId in(:constituencyids)  ");
		query.setParameterList("constituencyids", constituenciesIds);
		return query.list();
	}

	
	public List<Object[]> getAllConstituenciesInADistrict(Long districtId) {
		
		Query query = getSession().createQuery(" select distinct model.constituency.constituencyId," +
				" model.constituency.name " +
				" from DistrictConstituencies model where model.district.districtId =:districtId)  ");
		query.setParameter("districtId", districtId);
		return query.list();
	}
}
