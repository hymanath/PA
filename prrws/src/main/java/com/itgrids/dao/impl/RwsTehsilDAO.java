package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRwsTehsilDAO;
import com.itgrids.model.RwsTehsil;

@Repository
public class RwsTehsilDAO extends GenericDaoHibernate<RwsTehsil, Long> implements IRwsTehsilDAO {

	@Autowired
	SessionFactory sessionFactory;
	public RwsTehsilDAO() {
		super(RwsTehsil.class);
	}
	@Override
	public String getRwsCode(Long mandalId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.tehsilCode from RwsTehsil model, Tehsil model1 "+
				" where model.rwsTehsilId=model1.rwsTehsilId and model1.tehsilId =:mandalId  ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("mandalId", mandalId);
		 return (String) query.uniqueResult();
	}

}
