package com.itgrids.dao.impl;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRwsConstituencyDAO;
import com.itgrids.model.RwsConstituency;

@Repository
public class RwsConstituencyDAO extends GenericDaoHibernate<RwsConstituency, Long> implements
		IRwsConstituencyDAO {

	@Autowired
	SessionFactory sessionFactory;
	public RwsConstituencyDAO() {
		super(RwsConstituency.class);
	}
	
	@Override
	public String getRwsCode(Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.constituencyCode from RwsConstituency model, Constituency model1 "+
				" where model.rwsConstituencyId=model1.rwsConstituencyId and model1.constituencyId =:constituencyId  ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("constituencyId", constituencyId);
		 return (String) query.uniqueResult();
	}
	public List<Object[]> getConstituencyList(Set<String> constituencyIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select RC.constituencyCode , RC.constituencyName from RwsConstituency RC where RC.constituencyCode in (:constituencyIdList) ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("constituencyIdList", constituencyIdList);
		return query.list();
	}
}
