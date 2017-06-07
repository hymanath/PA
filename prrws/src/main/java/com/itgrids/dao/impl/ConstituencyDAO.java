package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.model.Constituency;

@Repository
public class ConstituencyDAO extends GenericDaoHibernate<Constituency, Long> implements IConstituencyDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ConstituencyDAO() {
		super(Constituency.class);

	}
	public List<Object[]> getConstituencies(Long districtId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select model.constituencyId,model.name from Constituency model "+
	               " where model.districtId=:districtId");
	    Query query = getSession().createQuery(sb.toString());
	    query.setParameter("districtId", districtId);
	    return query.list(); 
	    
	  }

}
