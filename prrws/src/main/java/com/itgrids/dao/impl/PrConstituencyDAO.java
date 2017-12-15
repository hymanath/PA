package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPrConstituencyDAO;
import com.itgrids.model.PrConstituency;

@Repository
public class PrConstituencyDAO extends GenericDaoHibernate<PrConstituency, Long> implements IPrConstituencyDAO {
	public PrConstituencyDAO(){
		super(PrConstituency.class);
	}
	
	public List<Object[]> getAllConstutiensFrDistrict(String districtId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.constituencyCode,"
	    		+ " model.constituencyName,"
	    		+ " model.prDistrict.districtCode"
	    		+ " from PrConstituency model");
	    if(districtId != null) 
	    	sb.append(" where model.prDistrict.districtCode = :districtId order by model.constituencyName asc");
	    
	    Query query = getSession().createQuery(sb.toString());
	    if(districtId != null)
	    	query.setParameter("districtId", districtId);
	   
	    return query.list();
	}
}
