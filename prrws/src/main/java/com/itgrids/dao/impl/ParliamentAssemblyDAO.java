package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.model.ParliamentAssembly;

@Repository
public class ParliamentAssemblyDAO extends GenericDaoHibernate<ParliamentAssembly, Long> implements IParliamentAssemblyDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ParliamentAssemblyDAO() {
		super(ParliamentAssembly.class);

	}

}
