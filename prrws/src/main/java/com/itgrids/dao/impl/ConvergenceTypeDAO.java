package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IConvergenceTypeDAO;
import com.itgrids.model.ConvergenceType;

@Repository
public class ConvergenceTypeDAO extends GenericDaoHibernate<ConvergenceType, Long> implements IConvergenceTypeDAO{

	public ConvergenceTypeDAO() {
		super(ConvergenceType.class);
	}

}
