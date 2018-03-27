package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEncHabLocationDAO;
import com.itgrids.model.EncHabLocation;

@Repository
public class EncHabLocationDAO extends GenericDaoHibernate<EncHabLocation, Long> implements IEncHabLocationDAO {

	public EncHabLocationDAO() {
		super(EncHabLocation.class);
	}

}
