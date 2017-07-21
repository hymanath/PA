package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IConvergenceTypeDAO;
import com.itgrids.model.ConvergenceType;

@Repository
public class ConvergenceTypeDAO extends GenericDaoHibernate<ConvergenceType, Long> implements IConvergenceTypeDAO{

	public ConvergenceTypeDAO() {
		super(ConvergenceType.class);
	}
	
	public List<Object[]> getAllConvergenceTypes(){
		Query query = getSession().createQuery("select distinct model.convergenceTypeId,"
				+ " model.type"
				+ " from ConvergenceType model"); 
		return query.list();
	}

}
