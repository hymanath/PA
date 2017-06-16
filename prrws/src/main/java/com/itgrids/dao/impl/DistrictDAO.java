package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDistrictDAO;
import com.itgrids.model.District;

@Repository
public class DistrictDAO extends GenericDaoHibernate<District, Long> implements IDistrictDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public DistrictDAO() {
		super(District.class);
      
		
	}
	@Override
	public List<Object[]> getDistrictIdName(Long stateId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select model.districtId,model.districtName from District model "+
	               " where model.stateId=:stateId");
	    Query query = getSession().createQuery(sb.toString());
	    query.setParameter("stateId",stateId);
	    return query.list();
	}
	
	public List<Long> getDistrictIdDetailsByDistrictIds(String districtIds){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select distinct model.districtId from District model "+
	               " where model.districtId in ("+districtIds+")");
	    Query query = getSession().createQuery(sb.toString());
	    return query.list();
	}
}
