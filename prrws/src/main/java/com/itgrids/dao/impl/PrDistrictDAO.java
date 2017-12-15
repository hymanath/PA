package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPrDistrictDAO;
import com.itgrids.model.PrDistrict;

@Repository
public class PrDistrictDAO extends GenericDaoHibernate<PrDistrict, Long> implements IPrDistrictDAO {
	public PrDistrictDAO(){
		super(PrDistrict.class);
	}
	
	public List<Object[]> getAllDistrictsFrState(){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.districtCode,"
	    		+ " model.districtName "
	    		+ " from PrDistrict model order by model.districtName asc");
	    
	    Query query = getSession().createQuery(sb.toString());
	   
	    return query.list();
	}
}
