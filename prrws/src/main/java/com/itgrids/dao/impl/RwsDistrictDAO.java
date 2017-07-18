package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRwsDistrictDAO;
import com.itgrids.model.RwsDistrict;

@Repository
public class RwsDistrictDAO extends GenericDaoHibernate<RwsDistrict, Long>implements IRwsDistrictDAO {

	@Autowired
	SessionFactory sessionFactory;
	public RwsDistrictDAO(){
		super(RwsDistrict.class);
	}
	@Override
	public String getRwsCode(Long districtId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.districtCode from RwsDistrict model, District model1 "+
				" where model.rwsDistrictId=model1.rwsDistrictId and model1.districtId =:districtId  ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("districtId", districtId);
		 return (String) query.uniqueResult();
	}
}
