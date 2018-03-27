package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEncHabtationDAO;
import com.itgrids.model.EncHabtation;

@Repository
public class EncHabtationDAO extends GenericDaoHibernate<EncHabtation, Long> implements IEncHabtationDAO {

	public EncHabtationDAO() {
		super(EncHabtation.class);
	}

	

}
